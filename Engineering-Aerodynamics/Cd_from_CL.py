import csv


# this program calculates Cd based on CL and angle of attack

Cd0 =  -0.00058

pi = 3.14159
span = 24   # wing span
S = 120    #planform area
AR = (pow(span,2))/S         #aspect ratio
e = 0.9    #efficiency factor

f_in = input("Please input file name of text file: ")

file = f_in + ".txt"

with open(file) as f:
    #for x in range(1):
     #   next(f)
    
    #listrow = [x.strip().split('\t') for x in f]
    listCol = list(zip(*(line.strip().split() for line in f)))

angle = [float(i) for i in listCol[0]]
Cl = [float(j) for j in listCol[1]]

#print(angle)
#print(Cl)


Cd = [(Cd0 + ((j**2) / (pi*AR*e))) for j in Cl]

for x in range(0, len(Cd)):
    print(angle[x],Cl[x],Cd[x])







