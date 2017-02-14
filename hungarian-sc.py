from munkres import Munkres, print_matrix
import time
import numpy as np 


file = open('matrix.txt', 'w')

n = int(raw_input("Enter the size N:"))

matrix = np.random.random_integers(1,10000, (n,n))

print matrix
for item in matrix:
	print >> file, list(item),","

#file.write(matrix)

file.close()

start = time.time()
m = Munkres()
indexes = m.compute(matrix)

#print_matrix(matrix, msg='Lowest cost through this matrix:')

end = time.time()

print "Time:", (end - start)
#print 'total cost: %d' % total
