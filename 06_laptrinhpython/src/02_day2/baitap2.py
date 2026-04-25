n = -1
lst = []
while n <=0:
    n = int(input("Nhap so luong phan tu: "))
    
for i in range(n):
    x = float(input('Nhap gia tri: '))
    
    lst.append(x)

sum = 0
for x in lst:
    sum += x
print(f'Trung binh cong: {sum/len(lst)}')

