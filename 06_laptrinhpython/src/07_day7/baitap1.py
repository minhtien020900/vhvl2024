import numpy as np

# c1 = np.zeros((2,4))
# print(c1)

X = np.array([
                [1, 2, 3, 4],
                [4, 5, 6, 2],
                [7, 8, 9, 1],
                [2, 5, 1, 5]])

# print(X[0: 3, [0,2]])
# print(X[0, 1])
x = np.array([1,2,3])

y = np.arange(-200,200,1)
# print(y.tolist())
# print(np.argmax(y))
print(np.argmin(y))

print(x)
np.median(X, axis=0)
l = np.random.randint(1, 10, size=(2, 3))
print(np.shape(x ))
# print(l)