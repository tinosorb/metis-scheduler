rnum = int(raw_input("Number of reducers:"))
snum = int(raw_input("Number of reducers:"))

matrix = [[16171305,16171305,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
[10483469,10483470,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
[0,15023837,15023837,0,0,0,0,0,0,0,1,0,0,0,0,0],
[0,0,15022823,15022823,0,0,0,0,0,0,0,0,0,0,0,0],
[0,0,0,15000000,15044988,0,0,1,0,0,0,0,0,0,0,0],
[1,0,0,0,0,15038679,15000000,0,0,0,0,0,0,0,0,0],
[0,0,0,0,0,0,15036596,15000000,0,0,0,0,0,0,0,1],
[1,0,0,0,0,0,0,15041449,15000000,0,0,0,0,0,0,0],
[1,0,0,0,0,0,0,15039143,15000000,0,0,0,0,0,0,0],
[0,0,0,0,0,0,0,0,15019588,15019589,0,0,0,0,0,1],
[0,0,0,0,1,0,0,0,0,15035977,15000000,0,0,0,0,0],
[0,0,0,0,0,1,0,0,0,0,14028917,16004832,0,0,0,0],
[0,0,0,0,0,0,0,0,0,1,0,15016353,15016353,0,0,0],
[1,0,0,0,0,0,0,0,0,0,0,0,15031344,15000000,0,0],
[1,0,0,0,0,0,0,0,0,0,0,0,2808,15006246,15022331,0],
[0,0,0,0,0,0,0,0,0,0,0,0,0,0,15502794,14127532]]

result = [[0 for x in range(snum)] for y in range(rnum)]

sum = 0

for i in range(rnum):
	for j in range(snum):
		sum += matrix[i][j]
	for k in range(snum):
		result[i][k] = sum - matrix[i][k]
	sum = 0

print matrix
print result

