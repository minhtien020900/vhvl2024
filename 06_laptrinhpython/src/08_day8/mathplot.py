from matplotlib import pyplot as plt 
import matplotlib.pyplot as plt
import numpy  as np

theta = np.arange(0, 4*np.pi ,0.1)
r = 0.5*theta
x = r*np.cos(theta)
y = r*np.sin(theta)
plt.plot(x,y)
plt.show()