while "true":
    n = int(input("Nhap so tu nhien n: "))
    if n >= 0:
        break;

lst = []
lst_bp = []

for i in range(n):
    lst.append(i)
    if(i < n):
        lst_bp.append(i**2)


print(f'list cac so tu nhien tu 0 -> n: {lst}')
print(f'list bình phương các số tự nhiên nhỏ hơn n: {lst_bp}')

    