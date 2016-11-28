#!/usr/bin/python2
import itertools


rnum = int(raw_input("Number of reducers:"))
snum = int(raw_input("Number of servers:"))
optimal = 0
opt_ass = list()
total = 0
# Assuming rnum = snum.

matrix = [[0 for x in range(snum)] for y in range(rnum)]

for x in range(rnum):
    for y in range(snum):
        print "Please enter the edge weight for reducer", x, "on server", y, ": "
        ew = int(raw_input())
        matrix[x][y] = ew


for x in range(rnum):
    for y in range(snum):
        sum_x = sum(matrix[x])
        etc_xy = sum_x - matrix[x][y]
        matrix[x][y] = etc_xy

print matrix

perms = list(itertools.permutations(range(snum)))
print "Total number of permutations: ", len(perms)
for perm in perms:
    assignment = list(perm)
    for i in range(rnum):
        ass_ri = assignment[i]
        sum_ri = sum((matrix[i])) - matrix[i][ass_ri]
        total += sum_ri
    print perm, total
    if total <= optimal or optimal == 0:
        optimal = total
        opt_ass = assignment
        print "Current BEST!!!: ", optimal, "with ", opt_ass
    total = 0