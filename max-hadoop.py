#!/usr/bin/python2

import itertools

print "Start process of computing max(X) given a placement, input is the total cost for assignments"

rnum = int(raw_input("Number of reducers:"))
snum = int(raw_input("Number of servers:"))
optimal = 0
opt_ass = list()
total = 0
# Assuming rnum = snum.

matrix = [[0 for x in range(snum)] for y in range(rnum)]

matrix = [[600162, 600119, 600225, 599926, 599957, 599984, 600301, 600236, 599812, 599843, 600272, 599712, 599858, 599652, 599952, 599989],
          [600009, 600212, 600224, 599876, 599982, 600252, 599992, 600138, 600045, 599477, 600115, 599751, 599928, 600166, 599990, 599843],
          [600171, 599883, 599940, 600274, 600060, 599803, 599726, 600084, 599833, 599839, 600216, 599840, 600099, 600095, 599984, 600153],
          [599758, 600138, 600000, 600244, 600085, 600072, 599773, 599612, 600308, 599979, 599952, 600017, 599829, 600306, 599971, 599956],
          [599953, 599946, 600037, 600003, 600022, 599976, 599849, 599905, 600123, 599947, 600185, 599882, 600223, 599859, 600054, 600036],
          [599833, 599971, 600031, 600237, 599720, 599878, 600047, 600510, 600392, 599643, 599887, 600125, 599794, 599592, 600109, 600231],
          [599784, 600191, 600084, 600229, 600138, 600113, 599976, 599955, 599746, 600097, 599993, 599617, 600194, 600473, 599551, 599859],
          [599944, 600171, 599867, 599715, 599980, 600061, 600434, 599872, 600480, 599790, 599825, 599740, 600161, 599646, 600163, 600151],
          [599634, 600287, 599938, 600005, 600126, 600014, 599881, 600039, 599680, 599955, 600036, 599913, 600314, 600342, 599808, 600028],
          [599614, 599889, 599686, 599909, 599990, 599841, 599953, 600185, 600260, 600129, 599792, 600078, 600069, 600005, 600360, 600240],
          [599972, 599938, 600058, 600059, 599792, 599869, 600026, 599713, 599868, 599978, 600151, 600182, 600220, 600248, 599964, 599962],
          [600522, 599877, 600142, 599955, 600162, 599909, 599975, 599902, 600094, 600091, 600189, 599852, 600029, 599670, 599776, 599855],
          [599816, 599862, 599926, 600129, 599709, 599974, 599855, 600075, 599975, 600225, 600098, 600159, 600176, 600131, 600061, 599829],
          [600171, 599890, 599779, 600092, 600123, 600063, 600194, 600132, 599531, 599864, 599865, 600069, 599886, 600098, 600181, 600062],
          [599862, 599864, 600100, 599927, 600227, 600235, 599940, 600037, 599603, 599944, 600286, 599780, 599941, 600067, 599893, 600294],
          [599990, 599862, 600088, 599924, 600091, 599773, 600252, 600314, 600064, 599801, 599696, 600079, 599927, 599980, 600197, 599962]]

#for x in range(rnum):
#    for y in range(snum):
#        print "Please enter the edge weight for reducer", x, "on server", y, ": "
#        ew = int(raw_input())
#        matrix[x][y] = ew


for x in range(rnum):
    print "\n"
    for y in range(snum):
        print(matrix[x][y]),
print "\n"

#perms = list(itertools.permutations(range(snum)))
#print "Total number of permutations: ", len(perms)
#for perm in perms:
#for server 0  1  2  3  4  5  6  7  8   9  10  11  12  13  14  15
#          16 14  7 12 15  2  6 10 13   9  11  3   5   1   4   8
perm = [8,5,6,9,7,13,10,12,15,4,3,2,1,14,11,16]
assignment = list(perm)
max = 0

for i in range(rnum):
    ass_ri = assignment[i]
    #    sum_ri = sum((matrix[i])) - matrix[i][ass_ri]
    print "Reducer ", i, "onto ", ass_ri, " with cost: ", matrix[i][ass_ri-1]
    if matrix[i][ass_ri-1] > max:
        max = matrix[i][ass_ri-1]
    print "Current MAX!!!: ", max
#    total = 0

print "max with assignment: ", max, "with ", perm














