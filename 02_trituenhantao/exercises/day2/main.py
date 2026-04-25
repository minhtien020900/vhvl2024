a = 5
b = 7

if type(a) is int:
    c = a+b
elif type(a) is str:
    c = a + str(b)
else:
    c = 'Ko tinh duoc'

c1 = a + b if type(a) is int else a + str(b) if type(a) is str else 'Khong tinh dc'
print(c1)