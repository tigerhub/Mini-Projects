import csv
import numpy as np
import matplotlib.pyplot as plt

chord = 6   #inches
thickness_multiplier = 1.2

f_in = raw_input("Please input file name of dat file: ")

file = f_in + ".DAT"

with open(file) as f:
    for x in range(1):
        next(f)
    
    #listrow = [x.strip().split('\t') for x in f]
    listCol = list(zip(*(line.strip().split() for line in f)))

x2 = [float(i) for i in listCol[0]]
y2 = [float(j) for j in listCol[1]]

x = [thickness_multiplier*chord*float(i) for i in listCol[0]]
y = [thickness_multiplier*chord*float(j) for j in listCol[1]]

#plt.plot(x, y)
plt.scatter(x, y)
plt.title('airfoil with chord graph')
plt.xlabel('x')
plt.ylabel('y ')
plt.show()

plt.scatter(x2, y2)
plt.title('airfoil graph')
plt.xlabel('x')
plt.ylabel('y ')
plt.show()

#test debug:
#print(x)





