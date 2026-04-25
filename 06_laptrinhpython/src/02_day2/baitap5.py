n = -1
lst = []
while n <=0:
    n = int(input("Nhap so luong phan tu: "))
    
for i in range(n):
    x = int(input('Nhap gia tri: '))
    
    lst.append(x)

sum = 0
lst_so_le = []
lst_so_chan= []

for x in lst:
    if x %2 == 0:
        lst_so_chan.append(x)
    else:
        lst_so_le.append(x)
    
    
print(f'Danh sach cac so chan: {lst_so_chan}')
print(f'Danh sach cac so le: {lst_so_le}')

