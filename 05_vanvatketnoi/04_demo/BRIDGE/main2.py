import serial, time
import sounddevice as sd
import numpy as np

ser = serial.Serial("COM2", 9600) 

def audio_callback(indata, frames, time_info, status):
    # lấy giá trị RMS (cường độ tín hiệu)
    volume_norm = np.linalg.norm(indata) * 100
    value = int(min(volume_norm, 255))  # giới hạn 0-255
    ser.write(f"{value}\n".encode())
    print("Sent:", value)

# thu âm từ micro
with sd.InputStream(callback=audio_callback, channels=1, samplerate=44100):
    print("Streaming audio... Press Ctrl+C to stop")
    while True:
        time.sleep(0.1)
