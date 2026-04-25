import serial, time

ser = serial.Serial("COM2", 9600, timeout=1)

while True:
    msg = "Hello from PC\r\n"
    ser.write(msg.encode())
    print("PC gửi:", msg.strip())

    if ser.in_waiting > 0:
        data = ser.readline().decode(errors="ignore").strip()
        if data:
            print("PC nhận:", data)

    time.sleep(1)
