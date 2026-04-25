from flask import Flask, request, jsonify
import os
import threading
import soundfile as sf
import random
import serial
import time

app = Flask(__name__)
UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# Kết nối COM tới Proteus
ser = serial.Serial("COM2", 9600, timeout=0.1)

# Thread control
play_thread = None
play_thread_event = threading.Event()
play_thread_stop = threading.Event()  # Event để dừng hẳn thread

@app.route("/")
def index():
    return """
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Audio Controller</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body { padding: 20px; background: #f8f9fa; }
    .container { max-width: 600px; background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1);}
    h1 { margin-bottom: 30px; }
    .btn { margin-right: 10px; margin-bottom: 10px; }
    audio { width: 100%; margin-top: 15px; }
  </style>
</head>
<body>
  <div class="container">
    <h1 class="text-center">Điều khiển Audio</h1>

    <div class="d-flex justify-content-between mb-3">
      <button class="btn btn-success" onclick="sendStart()">Start</button>
      <button class="btn btn-danger" onclick="sendStop()">Stop</button>
    </div>

    <div class="mb-3">
      <label for="audio_file" class="form-label">Chọn file audio:</label>
      <input class="form-control" type="file" id="audio_file" accept=".wav,.flac,.mp3">
    </div>

    <div class="d-flex justify-content-between mb-3">
      <button class="btn btn-primary" onclick="uploadFile()">Upload</button>
      <button class="btn btn-info text-white" onclick="playFile()">Play</button>
    </div>

    <audio id="audio" controls></audio>
  </div>

  <script>
    const audio = document.getElementById('audio');

    audio.addEventListener('pause', () => {
        fetch('/pause', {method:'POST'}).then(res=>res.json()).then(d=>console.log(d))
    });

    audio.addEventListener('play', () => {
        fetch('/resume', {method:'POST'}).then(res=>res.json()).then(d=>console.log(d))
    });

    window.addEventListener('beforeunload', () => {
        fetch('/stop_thread', {method:'POST'});
    });

    function sendStart() {
        fetch('/start', {method:'POST'}).then(res=>res.json()).then(d=>console.log(d))
    }

    function sendStop() {
        fetch('/stop', {method:'POST'}).then(res=>res.json()).then(d=>console.log(d))
    }

    function uploadFile() {
        const fileInput = document.getElementById('audio_file')
        if(fileInput.files.length === 0) return alert("Chọn file trước!")
        const formData = new FormData()
        formData.append('audio_file', fileInput.files[0])
        fetch('/upload', {method:'POST', body:formData})
            .then(res=>res.json())
            .then(d=>alert(JSON.stringify(d)))
    }

    function playFile() {
        const fileInput = document.getElementById('audio_file')
        if(fileInput.files.length === 0) return alert("Chọn file trước!")
        const file = fileInput.files[0]

        audio.src = URL.createObjectURL(file)
        audio.play()

        const formData = new FormData()
        formData.append('audio_file', file)
        fetch('/play', {method:'POST', body:formData}).then(res=>res.json()).then(d=>console.log(d))
    }
  </script>
</body>
</html>
    """

@app.route("/start", methods=["POST"])
def start_system():
    ser.write(b"START|INIT\r\n")
    return jsonify({"status": "Đã gửi tín hiệu START"})

@app.route("/stop", methods=["POST"])
def stop_system():
    ser.write(b"STOP\r\n")
    stop_play_thread()
    return jsonify({"status": "Đã gửi tín hiệu STOP và dừng thread VOL"})

@app.route("/stop_thread", methods=["POST"])
def stop_thread_route():
    stop_play_thread()
    return jsonify({"status": "Thread VOL đã bị dừng"})

@app.route("/upload", methods=["POST"])
def upload_file():
    file = request.files.get("audio_file")
    if not file:
        return jsonify({"error": "Chưa chọn file"}), 400

    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    audio_data, samplerate = sf.read(file_path)
    duration = len(audio_data) / samplerate
    minutes, seconds = divmod(int(duration), 60)

    ser.write(f"UPLOAD|{file.filename}|{minutes:02d}:{seconds:02d}\r\n".encode())
    return jsonify({"status": "File đã upload", "filename": file.filename, "duration": f"{minutes:02d}:{seconds:02d}"})

def play_and_send(file_path):
    filename = os.path.basename(file_path)
    audio_data, samplerate = sf.read(file_path)
    duration = len(audio_data) / samplerate
    minutes, seconds = divmod(int(duration), 60)

    ser.write(f"PLAY|{filename}|{minutes:02d}:{seconds:02d}\r\n".encode())

    while not play_thread_stop.is_set():
        play_thread_event.wait()
        bass = random.randint(0, 255)
        mid  = random.randint(0, 255)
        tre  = random.randint(0, 255)
        ser.write(f"VOL|{bass}|{mid}|{tre}\r\n".encode())
        time.sleep(0.03)

@app.route("/play", methods=["POST"])
def play_file():
    file = request.files.get("audio_file")
    if not file:
        return jsonify({"error": "Chưa chọn file"}), 400

    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    global play_thread, play_thread_event, play_thread_stop
    if play_thread is None or not play_thread.is_alive():
        play_thread_event.set()
        play_thread_stop.clear()
        play_thread = threading.Thread(target=play_and_send, args=(file_path,), daemon=True)
        play_thread.start()
    else:
        play_thread_event.set()

    return jsonify({"status": "Đang phát và gửi dummy dữ liệu COM"})

@app.route("/pause", methods=["POST"])
def pause_audio():
    global play_thread_event
    play_thread_event.clear()
    return jsonify({"status": "Đã pause VOL"})

@app.route("/resume", methods=["POST"])
def resume_audio():
    global play_thread_event
    play_thread_event.set()
    return jsonify({"status": "Đã resume VOL"})

def stop_play_thread():
    global play_thread_event, play_thread_stop
    play_thread_event.clear()
    play_thread_stop.set()

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=False)
