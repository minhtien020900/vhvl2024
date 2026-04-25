import serial, time
import numpy as np
import soundfile as sf
from scipy.signal import butter, lfilter

# ==== CẤU HÌNH COM ====
COM_PORT = "COM2"
BAUD_RATE = 9600
ser = serial.Serial(COM_PORT, BAUD_RATE, timeout=0.1)

# ==== Hàm filter bandpass ====
def butter_bandpass(lowcut, highcut, fs, order=4):
    b, a = butter(order, [lowcut / (fs / 2), highcut / (fs / 2)], btype='band')
    return b, a

def bandpass_filter(data, lowcut, highcut, fs):
    b, a = butter_bandpass(lowcut, highcut, fs)
    return lfilter(b, a, data)

# ==== Hàm gửi audio ====
def send_audio_3bands(file_path):
    audio_data, samplerate = sf.read(file_path)
    
    # Nếu stereo, lấy kênh trái
    if audio_data.ndim > 1:
        audio_data = audio_data[:, 0]

    block_size = 1024
    # Biến nhớ giá trị cũ để smoothing
    prev_bass, prev_mid, prev_tre = 0, 0, 0
    alpha = 0.3  # hệ số làm mượt

    for i in range(0, len(audio_data), block_size):
        block = audio_data[i:i+block_size]

        if len(block) == 0:
            continue

        # tách 3 dải
        bass_block = bandpass_filter(block, 20, 250, samplerate)
        mid_block  = bandpass_filter(block, 251, 4000, samplerate)
        tre_block  = bandpass_filter(block, 4001, 12000, samplerate)

        # RMS cho từng dải (scale vừa phải)
        bass_raw = np.sqrt(np.mean(bass_block**2)) * 500
        mid_raw  = np.sqrt(np.mean(mid_block**2)) * 500
        tre_raw  = np.sqrt(np.mean(tre_block**2)) * 500

        # làm mượt (EMA)
        bass_val = int(np.clip(prev_bass*(1-alpha) + bass_raw*alpha, 0, 255))
        mid_val  = int(np.clip(prev_mid *(1-alpha) + mid_raw *alpha, 0, 255))
        tre_val  = int(np.clip(prev_tre *(1-alpha) + tre_raw *alpha, 0, 255))

        prev_bass, prev_mid, prev_tre = bass_val, mid_val, tre_val

        # gửi CSV
        csv_line = f"{bass_val},{mid_val},{tre_val}\r\n"
        ser.write(csv_line.encode())
        print("Sent:", csv_line.strip())

        # đọc phản hồi nếu có
        if ser.in_waiting:
            rx_data = ser.readline().decode(errors="ignore").strip()
            if rx_data:
                print("Nhận được:", rx_data)

        # delay theo block thực tế
        block_time = len(block) / samplerate
        time.sleep(0.05)

if __name__ == "__main__":
    send_audio_3bands("music.wav")
