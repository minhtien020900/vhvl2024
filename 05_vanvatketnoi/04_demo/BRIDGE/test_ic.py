import serial
import time

# Mở cổng COM (sửa COM3 thành cổng bạn dùng trong Proteus)
ser = serial.Serial("COM2", 9600, timeout=1)

# 8 case test
test_cases = [
    (255, 0, 0),     # Bass full
    (0, 255, 0),     # Mid full
    (0, 0, 255),     # Treble full
    (255, 255, 0),   # Bass + Mid full
    (255, 0, 255),   # Bass + Treble full
    (0, 255, 255),   # Mid + Treble full
    (255, 255, 255), # All full
    (0, 0, 0),       # All off
]

while True:
    for case in test_cases:
        bass, mid, treb = case
        msg = f"{bass},{mid},{treb}\r\n"
        ser.write(msg.encode())
        print(f"[SEND] {msg.strip()}")
        time.sleep(5)  
