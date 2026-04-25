n = int(input('Nhập vào số lượng phần tử: '))
ds = []
for i in range(n):
    so = int(input("Nhap vao so thu " +str(i+1)+': '))
    ds.append(so)
    
print("So lon nhat la: "+ str(max(ds)))
