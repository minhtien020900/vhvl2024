n = -1
lst = []
while n <=0:
    n = int(input("Nhap so luong phan tu: "))
    
for i in range(n):
    x = -1
    while x <= 0:
        x = int(input("Nhap gia tri"))
    
    lst.append(x)

rs = []
for x in lst:
    sum_uoc = 0
    
    for i in range (1,x):
        if x % i ==0:
            sum_uoc+= x
    
    if sum_uoc > x:
        rs.append(x)
        
        
# 1, 4, 2 ,1
#2,1
#
# 
print(f'So phong phu: {rs}')


