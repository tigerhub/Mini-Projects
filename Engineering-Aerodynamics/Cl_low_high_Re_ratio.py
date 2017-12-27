
# visc file
with open("s1223_visc2.pol") as f:
    for x in range(12):
        next(f)
    #listrow = [x.strip().split('\t') for x in f]
    listCol = list(zip(*(line.strip().split() for line in f)))


AOA_visc = listCol[0]
CL_visc = listCol[1]


# invisc file
with open("s1223_invisc2.pol") as f:
    for x in range(12):
        next(f)
    #listrow = [x.strip().split('\t') for x in f]
    listCol_invisc = list(zip(*(line.strip().split() for line in f)))


AOA_invisc = listCol_invisc[0]
CL_invisc = listCol_invisc[1]


# create matrix of the values for visc and invisc:
matrix_visc = [[AOA_visc[i], CL_visc[i]] for i in range(len(AOA_visc))]
matrix_invisc = [[AOA_invisc[i], CL_invisc[i]] for i in range(len(AOA_invisc))]

#intersection between the lists angle of attack for invisc and visc (in case the AOA are different!)
intersect1 = [item for item in AOA_visc if item in AOA_invisc]

# gives Cl for invisc that intersect with values of visc
final_Cl_invisc = [x[1] for x in matrix_invisc if x[0] in intersect1]
#print(final_Cl_invisc)
final_Cl_visc = [x[1] for x in matrix_visc if x[0] in intersect1]

# now we can get ratio of CL for low and high reynold's numbers:
ratio_CL = [ (float(final_Cl_visc[i])/float(final_Cl_invisc[i])) for i in range(len(intersect1))]
#print(ratio_CL)

# if we want to graph, then we would do ratio_CL as y, and intersect1 as x, then do plot(x,y) using the matlabl.plt plot library

'''
for x in range(len(intersect1)):
    print(intersect1[x] , ratio_CL[x])
'''

#matrix of ratios of CL per angle of attack
matrix_all = [[float(intersect1[i]), ratio_CL[i]] for i in range(len(intersect1))]
#print(matrix_all)

#apply ratios to CL values obtained from tornado file
with open("S1223_tornado_CL.txt") as f:
    #listrow = [x.strip().split('\t') for x in f]
    listCol_torn = list(zip(*(line.strip().split() for line in f)))

angleTorn = [float(i) for i in listCol_torn[0]]
Cl_torn = [float(i) for i in listCol_torn[1]]

#print(angleTorn)
#print(Cl_torn)


#get Cl ratio of intersect angles from tornado
torn_matrix = [item[1] for item in matrix_all if item[0] in angleTorn]
#print(torn_matrix)

# get overall CL 
cl_overall = [torn_matrix[i]*Cl_torn[i] for i in range(len(Cl_torn))]

#print(cl_overall)


#print out Cl and angle
for x in range(len(angleTorn)):
    print(angleTorn[x] , cl_overall[x])

