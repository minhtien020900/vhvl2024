import serial 
import time 
ser = serial.Serial("COM2", 9600, timeout=1) 
# COM2 nối vớiCOM1 qua cặp ảo print("Bắt đầu test...")
while True: 
    
    msg = "Hello tu Python\r\n" 
    ser.write(msg.encode("utf-8")) 
    print(f"Đã gửi: {msg.strip()}") 
    # nghỉ chút để thiết bị kịp trả lời 
    if ser.in_waiting > 0: 
        data = ser.readline().decode("utf-8", errors="ignore").strip() 
        if data: print(f"Nhận được: {data}") 
    time.sleep(1.5) # tổng cộng khoảng 2 giây / vòng