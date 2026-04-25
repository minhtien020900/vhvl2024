# app.py
from flask import Flask, request, jsonify
import os
import threading
import sounddevice as sd
import soundfile as sf
import numpy as np
import serial

app = Flask(__name__)
UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

# Kết nối COM tới Proteus (sửa lại COM cho đúng máy bạn)
ser = serial.Serial("COM2", 9600, timeout=0.1)

# .then(d=>alert(d.status))
@app.route("/")
def index():
    return """
    <h1>Điều khiển Audio</h1>
    <button onclick="sendStart()">Start</button>
    <br><br>
    <input type="file" id="audio_file" accept=".wav,.flac,.mp3">
    <button onclick="uploadFile()">Upload</button>
    <button onclick="playFile()">Play</button>
    <audio id="audio" controls></audio>

    <script>
    function sendStart() {
        fetch('/start', {method:'POST'})
            .then(res=>res.json())
            
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
        const audio = document.getElementById('audio')
        if(fileInput.files.length === 0) return alert("Chọn file trước!")
        const file = fileInput.files[0]

        audio.src = URL.createObjectURL(file)
        audio.play()

        const formData = new FormData()
        formData.append('audio_file', file)
        fetch('/play', {method:'POST', body:formData})
            .then(res=>res.json())
            .then(d=>console.log(d))
    }
    </script>
    """


@app.route("/start", methods=["POST"])
def start_system():
    ser.write(b"START|INIT\r\n")
    return jsonify({"status": "Đã gửi tín hiệu START"})


@app.route("/upload", methods=["POST"])
def upload_file():
    file = request.files.get("audio_file")
    if not file:
        return jsonify({"error": "Chưa chọn file"}), 400

    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    # Lấy thời lượng
    audio_data, samplerate = sf.read(file_path)
    duration = len(audio_data) / samplerate
    minutes, seconds = divmod(int(duration), 60)

    ser.write(f"UPLOAD|{file.filename}|{minutes:02d}:{seconds:02d}\r\n".encode())
    return jsonify({"status": "File đã upload",
                    "filename": file.filename,
                    "duration": f"{minutes:02d}:{seconds:02d}"})


# def play_and_send(file_path):
#     audio_data, samplerate = sf.read(file_path)
#     if audio_data.ndim > 1:
#         audio_data = audio_data[:, 0]

#     block_size = 1024
#     total_blocks = len(audio_data) // block_size
#     for i in range(total_blocks):
#         block = audio_data[i*block_size:(i+1)*block_size]
#         value = int(min(np.linalg.norm(block) * 100, 255))

#         # Gửi dữ liệu volume
#         ser.write(f"{value}\r\n".encode())

#         # Gửi trạng thái PLAY + thời gian phát
#         elapsed = (i*block_size) / samplerate
#         m, s = divmod(int(elapsed), 60)
#         filename = os.path.basename(file_path)
#         ser.write(f"PLAY|{filename}|{m:02d}:{s:02d}\r\n".encode())

#         sd.play(block, samplerate)
#         sd.wait()
def play_and_send(file_path):
    audio_data, samplerate = sf.read(file_path)
    if audio_data.ndim > 1:
        audio_data = audio_data[:, 0]

    block_size = 1024
    total_blocks = len(audio_data) // block_size

    last_second = -1  # để chỉ gửi khi giây thay đổi
    for i in range(total_blocks):
        block = audio_data[i*block_size:(i+1)*block_size]
        value = int(min(np.linalg.norm(block) * 100, 255))

        # Tính thời gian phát
        elapsed = (i*block_size) / samplerate
        m, s = divmod(int(elapsed), 60)

        # Chỉ gửi khi giây thay đổi
        if int(elapsed) != last_second:
            last_second = int(elapsed)
            filename = os.path.basename(file_path)

            # Gửi trạng thái PLAY (tên file + thời gian)
            ser.write(f"PLAY|{filename}|{m:02d}:{s:02d}\r\n".encode())

        # Chỉ gửi volume để hiển thị mức led
        ser.write(f"VOL|{value}\r\n".encode())

        sd.play(block, samplerate)
        sd.wait()


@app.route("/play", methods=["POST"])
def play_file():
    file = request.files.get("audio_file")
    if not file:
        return jsonify({"error": "Chưa chọn file"}), 400

    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    threading.Thread(target=play_and_send, args=(file_path,)).start()
    return jsonify({"status": "Đang phát và gửi dữ liệu COM"})


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=False)
