def get_start_by_electronic_used(dien_tieu_thu:float):
    if dien_tieu_thu < 2:
        return 5
    elif dien_tieu_thu >= 2 or dien_tieu_thu < 4:
        return 4
    elif dien_tieu_thu >= 4 or dien_tieu_thu < 6:
        return 3
    elif dien_tieu_thu >= 6 or dien_tieu_thu < 10:
        return 2
    else:
        return 1

def input_and_print_start():
    dien_tieu_thu = -1
    while dien_tieu_thu <0:
        dien_tieu_thu = float(input("Nhap dien tieu thu cua thiet bi: "))

    start = get_start_by_electronic_used(dien_tieu_thu)
    if start < 3:
        print("Tiet kiem dien")
    else:
        print("Khong tiet kiem dien")

input_and_print_start()
