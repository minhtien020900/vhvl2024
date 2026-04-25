import numpy as np

test = np.zeros((3,4))
test1 = np.ones((3,4))
test2 = np.arange(0,5,1)

a = np.random.rand(3,2)
np.random.seed(2)
b= np.random.rand(3,4)

# print(b)

x = np.array([[1,2,3,4],
              [4,5,6,2],
              [7,8,9,1],
              [2,5,1,5],
              
              ])
# print(x[[0,2],:])

B = np.array(np.arange(12))
print(B.shape)

B1=B.reshape(2,6)

a = np.random.randint(1,21,2352)
b = np.random.randint(-20,-1,2352)
C = a.reshape(28,28,3)
D = b.reshape(28,28,3)

print(a.tolist())
# B2=B.reshape(2,6)
# B2=B.reshape(2,6)