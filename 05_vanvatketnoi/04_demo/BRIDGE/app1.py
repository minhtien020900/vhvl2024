# app.py
from flask import Flask, request, jsonify
import os
import threading
import sounddevice as sd
import soundfile as sf
import numpy as np
import serial
import time

app = Flask(__name__)
UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
ser = serial.Serial("COM2", 9600, timeout=0.1)



def play_and_send(file_path):
    audio_data, samplerate = sf.read(file_path)
    if audio_data.ndim > 1:
        audio_data = audio_data[:,0]


    block_size = 1024
    for i in range(0, len(audio_data), block_size):
        block = audio_data[i:i+block_size]
        value = int(min(np.linalg.norm(block)*100, 255))
        ser.write(f"{value}\r\n".encode())
        if ser.in_waiting:
            rx = ser.readline().decode(errors="ignore").strip()
            if rx:
                print("Nhận:", rx)
        sd.play(block, samplerate)
        sd.wait()

@app.route("/")
def index():
    return '''
    <h1>Chọn file audio và Play</h1>
    <input type="file" id="audio_file" accept=".wav,.flac,.mp3">
    <button onclick="playAudio()">Play</button>
    <audio id="audio" controls></audio>

    <script>
    function playAudio() {
        const fileInput = document.getElementById('audio_file')
        const audio = document.getElementById('audio')
        if(fileInput.files.length === 0) return alert("Chọn file trước!")
        const file = fileInput.files[0]

        // Hiển thị audio trên web
        audio.src = URL.createObjectURL(file)
        audio.play()

        // Gửi file lên server để xử lý COM
        const formData = new FormData()
        formData.append('audio_file', file)
        fetch('/play', {method:'POST', body:formData})
            .then(res => res.json())
            .then(data => console.log(data))
    }
    </script>
    '''

@app.route("/play", methods=["POST"])
def play_file():
    file = request.files.get("audio_file")
    if not file:
        return jsonify({"error":"Chưa chọn file"}), 400
    file_path = os.path.join(UPLOAD_FOLDER, file.filename)
    file.save(file_path)

    threading.Thread(target=play_and_send, args=(file_path,)).start()
    return jsonify({"status":"Đang phát và gửi dữ liệu COM"})

if __name__ == "__main__":
    app.run(debug=True)
