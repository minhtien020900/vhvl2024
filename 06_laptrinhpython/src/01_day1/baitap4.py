while "true":
    diem_thuc_hanh = int(input("Nhap diem thuc hanh (tu 0 den 10): ")) 
    diem_lt = int(input("Nhap diem ly thuyet (tu 0 den 10): ")) 
    
    diem_hople= range(0,11)
    
    if diem_lt  in diem_hople and diem_thuc_hanh  in diem_hople:
        diem_tb = float ((diem_thuc_hanh + diem_lt) / 2)
        break
    else:
        print("Diem nhap khong hop le, vui long nhap lai....")
print(f"Bai tap 4: Diem TB la {diem_tb}")

