while "true":
    n = int(input("Nhap so luong phan tu: "))
    if n > 0:
        break;
lst = []

for i in range(n):
    x = int(input(f'Nhap gia tri thu {i}: '))
    
    lst.append(x)

max=lst[0]
min=lst[0]

for x in lst:
    if min >= x:
        min = x
    if max <= x:
        max =x 

print(f'Min: {min}')
print(f'Max: {max}')

    