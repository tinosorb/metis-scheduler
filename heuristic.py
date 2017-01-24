#!/usr/bin/python2
import itertools
import sys
from random import shuffle


rnum = int(raw_input("Number of reducers:"))
snum = int(raw_input("Number of servers:"))
nround = int(raw_input("Number of rounds:"))
best_round = sys.maxint
best = sys.maxint
best_ass = [999 for x in range(rnum)]
current_ass = [999 for x in range(rnum)]
used = [False for x in range(snum)]
# Assuming rnum = snum.

matrix = [[0 for x in range(snum)] for y in range(rnum)]

matrix = [[600162, 0, 600225, 599926, 599957, 599984, 600301, 600236, 599812, 599843, 600272, 599712, 599858, 599652, 599952, 599989],
[600009, 0, 600224, 599876, 599982, 600252, 599992, 600138, 600045, 599477, 600115, 599751, 599928, 600166, 599990, 599843],
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


# perms = list(itertools.permutations(range(snum)))

# Min-min walks
for i in range(nround):
    x = [[i] for i in range(rnum)]
    shuffle(x)
    order = list(itertools.chain.from_iterable(x))
    print order
    total = 0

    for i in range(len(order)):
        reducer_i = order[i]
        best = sys.maxint
        for server in range(snum):
            if  not used[server]:
                temp = matrix[reducer_i][server]
                if temp < best:
                    current_ass[reducer_i] = server
                    best = temp
            #else:
            #    print "Server taken! Skipped."

        used[current_ass[reducer_i]] = True
        total += matrix[reducer_i][current_ass[reducer_i]]

        print reducer_i, current_ass[reducer_i], "with cost ", best
    print current_ass, total

    if total < best_round:
        best_round = total
        best_ass = current_ass

    used = [False for x in range(snum)]
    current_ass = [999 for x in range(rnum)]

print "Overall best from min-min: ", best_round, best_ass



# max-min walks
taskRemoved = 0
isRemoved = [False for x in range(rnum)]
best_overall = sys.maxint
best_round = 0
best_reducer = sys.maxint
best_reducer_ass = [999 for x in range(rnum)]
current_ass = [999 for x in range(rnum)]
used = [False for x in range(snum)]


while taskRemoved != rnum:
    for reducer in range(rnum):
        best_reducer = sys.maxint
        if isRemoved[reducer]:
            continue
        for server in range (snum):
            if used[server]:
                continue
            if matrix[reducer][server] < best_reducer:
                best_reducer = matrix[reducer][server]
                best_reducer_ass = server

        print matrix[reducer], best_reducer_ass, best_reducer














#for x in range(rnum):
#    sum_x = sum(matrix[x])
#    for y in range(snum):
#        etc_xy = sum_x - matrix[x][y]
#        matrix[x][y] = etc_xy

#print matrix

#print "Total number of permutations: ", len(perms)
#for perm in perms:
#    max_ct = 0
#    assignment = list(perm)
#    for i in range(rnum):
#        ass_ri = assignment[i]
#        etc = matrix[i][ass_ri]
#        if etc >= max_ct:
#            max_ct = etc

#    if max_ct <= best or best == 0:
#        best = max_ct
#        best_ass = assignment
#        print "Current BEST!!!: ", best, "with ", best_ass

#print "OPTIMAL!!!: ", best, "with ", best_ass
