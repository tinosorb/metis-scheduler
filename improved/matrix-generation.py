import random
rows = int(raw_input("Number of rows:"))
columns = int(raw_input("Number of columns:"))
thefile = open('matrix.txt', 'w')

matrix = [[random.randrange(10, 100000, 2) for x in range(columns)] for y in range(rows)]

#print matrix

#for line in matrix:
#	for item in line:
		#linestring = ''.join(str(e) for e in line)
		#thefile.write(linestring)
#		print>>thefile, item

for line in matrix:
	print>>thefile, "{"+str(line).strip('[]')+"},"
	#print>>thefile, "\n"