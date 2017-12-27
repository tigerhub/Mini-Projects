import math

# dat file of airfoil
file = "exp1.DAT"
with open(file) as f:
    for x in range(1):
        next(f)
    #listrow = [x.strip().split('\t') for x in f]
    listCol = list(zip(*(line.strip().split() for line in f)))

# try with tab delimited:
if len(listCol) < 1:
    with open(file) as f:
        for x in range(1):
            next(f)
        listCol = list(zip(*(line.strip().split('\t') for line in f)))
#x = listCol[0] 
#z = listCol[1]


rootchord = 6
dihedral = 3    # degrees
tipcord = 0  # set to zero to create root airfoil of mainwing
span = -12    # span in inches    (make positive for right tip; negative for left tip)
thickness_multiplier = 1.2

x = []  #chord
z = []  #height
y = []  #spanwise cordinate

#scale by rootchord:
x = [rootchord*float(j) for j in listCol[0]]
z = [thickness_multiplier*rootchord*float(j) for j in listCol[1]]


if tipcord==0:
    y = [0 for j in listCol[0]]
else:
    x = [tipcord*float(j) for j in listCol[0]]
    
    ycord = span*(math.cos(math.radians(dihedral)))
    y = [ ycord for j in listCol[0]]


    z = [thickness_multiplier*tipcord*float(j) for j in listCol[1]]
    zcord = (abs(span))*(math.sin(math.radians(dihedral)))
    z = [(j+zcord) for j in z]

for i in range(len(x)):
    print(x[i],y[i],z[i])




