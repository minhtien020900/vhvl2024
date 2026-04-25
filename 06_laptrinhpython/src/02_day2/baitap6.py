n = -1
lst = []
while n <=0:
    n = int(input("Nhap so luong phan tu: "))
    
for i in range(n):
    x = int(input('Nhap gia tri: '))
    
    lst.append(x)

rs = []
for x in lst:
    is_prime = True
    
    for i in range(2,x):
        if x % i == 0:
            is_prime = False
            break
    if is_prime == True:
        rs.append(x)
    
        
    
    
print(f'Danh sach so ngyen to: {rs}')

