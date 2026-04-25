while "true":
    n = int(input("Nhap so nguyen duong N: "))
    
    if n < 0:
        print("N phai la so nguyen duong. Vui long nhap lai ...")
    else:
        break

step = 1
tong_cac_so_hang_den_n = 0

sn1 =0
sn2 =0
sn3= 0
sn4 =1/2

while step <= n:
    tong_cac_so_hang_den_n += step
    sn1+=1/tong_cac_so_hang_den_n
    sn2+=1/(step * (step + 1))   
    sn3 += step/(step + 1)
    sn4 +=((2*step)+1)/((2*step)+2)
    step +=1

print(f"S(n1) = {sn1}\n")
print(f"S(n2) = {sn2}\n")
print(f"S(n3) = {sn3}\n")
print(f"S(n4) = {sn4}\n")