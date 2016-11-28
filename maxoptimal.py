#!/usr/bin/python2
import itertools


rnum = int(raw_input("Number of reducers:"))
snum = int(raw_input("Number of servers:"))
optimal = 0
opt_ass = list()
# Assuming rnum = snum.

matrix = [[0 for x in range(snum)] for y in range(rnum)]

for x in range(rnum):
    for y in range(snum):
        print "Please enter the edge weight for reducer", x, "on server", y, ": "
        ew = int(raw_input())
        matrix[x][y] = ew


for x in range(rnum):
    sum_x = sum(matrix[x])
    for y in range(snum):
        etc_xy = sum_x - matrix[x][y]
        matrix[x][y] = etc_xy

print matrix


perms = list(itertools.permutations(range(snum)))
print "Total number of permutations: ", len(perms)
for perm in perms:
    max_ct = 0
    assignment = list(perm)
    for i in range(rnum):
        ass_ri = assignment[i]
        etc = matrix[i][ass_ri]
        if etc >= max_ct:
            max_ct = etc

    if max_ct <= optimal or optimal == 0:
        optimal = max_ct
        opt_ass = assignment
        print "Current BEST!!!: ", optimal, "with ", opt_ass

print "OPTIMAL!!!: ", optimal, "with ", opt_ass
