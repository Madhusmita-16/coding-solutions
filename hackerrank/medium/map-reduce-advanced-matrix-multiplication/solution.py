import sys
from collections import OrderedDict


class MapReduce:
    def __init__(self):
        self.intermediate = OrderedDict()
        self.result = []

    def emitIntermediate(self, key, value):
        self.intermediate.setdefault(key, [])
        self.intermediate[key].append(value)

    def emit(self, value):
        self.result[value[0]][value[1]] = value[2]

    def execute(self, matrix1, matrix2, mapper, reducer):
        n = len(matrix1)
        m = len(matrix2[0])

        for i in xrange(0, n):
            self.result.append([0] * m)

        mapper(matrix1, matrix2)

        for key in self.intermediate:
            reducer(key, self.intermediate[key])

        for i in xrange(0, n):
            row = ""
            for j in xrange(0, m):
                row += str(self.result[i][j]) + " "
            print(row)


mapReducer = None


def mapper(matrix1, matrix2):
    # matrix1: n x k
    # matrix2: k x m
    #
    # For every output cell (i, j), emit:
    # (i, j) -> matrix1[i][k] * matrix2[k][j]

    n = len(matrix1)
    k = len(matrix2)
    m = len(matrix2[0])

    for i in xrange(n):
        for j in xrange(m):
            for x in xrange(k):
                value = matrix1[i][x] * matrix2[x][j]
                mapReducer.emitIntermediate((i, j), value)


def reducer(key, list_of_values):
    # Sum all partial products for this cell

    total = sum(list_of_values)

    mapReducer.emit((key[0], key[1], total))


if __name__ == '__main__':
    testcases = int(raw_input())

    for _ in xrange(testcases):
        mapReducer = MapReduce()

        dimensions = sys.stdin.readline().strip().split(" ")
        row = int(dimensions[0])
        column = int(dimensions[1])

        matrix1 = []

        for i in range(row):
            read_row = sys.stdin.readline().strip()
            matrix1.append([])
            row_elems = read_row.split()

            for j in range(len(row_elems)):
                matrix1[i].append(int(row_elems[j]))

        dimensions = sys.stdin.readline().strip().split(" ")
        row = int(dimensions[0])
        column = int(dimensions[1])

        matrix2 = []

        for i in range(row):
            read_row = sys.stdin.readline().strip()
            matrix2.append([])
            row_elems = read_row.split()

            for j in range(len(row_elems)):
                matrix2[i].append(int(row_elems[j]))

        mapReducer.execute(matrix1, matrix2, mapper, reducer)
