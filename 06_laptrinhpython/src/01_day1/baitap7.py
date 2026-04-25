day_arr = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday']
while "true":
    input_day = int(input("Nhap thu trong tuan (tu 1 den 7): ")) 
    
    day_valid= range(1,len(day_arr)+ 1)
    
    if input_day in day_valid:
        print(f"Thu trong tuan bang Tieng Anh la: {day_arr[input_day-1]}")
        break
    else:
        print("Gia tri khong hop le. Vui long nhap lai ...")