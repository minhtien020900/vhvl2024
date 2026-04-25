def check_prime(x):
    for i in range(2,x):
        print(i)
        if x % i == 0:
            return False
    return True

m = int(input("Nhap gia tri: "))
for i in range(2,1):
    print(i)
# if check_prime(m):
#     print("la SNT")
# else:
#     print("KO LA SNT")