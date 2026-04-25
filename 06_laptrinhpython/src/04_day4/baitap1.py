def check_tam_giac(a,b,c):
    if a < 0 or b < 0 or c < 0:
        return False
    
    if (a + b > c) and (a +c > b) and( b+c > a) :
        return True
    
a = float(input("Nhap a = "));
b = float(input("Nhap b = "));
c = float(input("Nhap c = "));

if check_tam_giac(a,b,c):
    print("LA TAM GIAC")
else:
    print("KHONG PHAI LA TAM GIAC")