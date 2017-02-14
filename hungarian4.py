from munkres import Munkres, print_matrix

matrix = [[4,3,4,5],[3,7,6,8],[5,4,3,6],[6,7,5,6]]

m = Munkres()
indexes = m.compute(matrix)
print_matrix(matrix, msg='Lowest cost through this matrix:')

total = 0

for row, column in indexes:
    value = matrix[row][column]
    total += value
    print '(%d, %d) -> %d' % (row, column, value)
print 'total cost: %d' % total