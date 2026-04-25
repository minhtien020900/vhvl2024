def kiem_tra_hieu_nang_tieu_thu_dien(dien_tieu_thu:float):
    if dien_tieu_thu < 10:
        print("La thiet bi tiet kiem dien")
    else:
        print("Khong phai la thiet bi tiet kiem dien")


dien_tieu_thu = -1
while dien_tieu_thu <0:
    dien_tieu_thu = float(input("Nhap dien tieu thu cua thiet bi: "))

kiem_tra_hieu_nang_tieu_thu_dien(dien_tieu_thu)     