k = -1
lst = []
while k <=0:
    k = int(input("Nhap so K: "))

rs = []
rs2= []
for i in range(1,k+1):
    print(i)
    current = i;
    
    while current != 1:
        if current % 2 == 0:
            current = i/2
            rs2.append(current)
        else:
            current = 3*i+1
            rs2.append(current)
        print(current)
print(rs2)   

         
    
                
    
            
            
        

