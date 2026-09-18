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
        self.result.append(value)

    def execute(self, data, mapper, reducer):
        for record in data:
            mapper(record)

        for key in self.intermediate:
            reducer(key, self.intermediate[key])

        self.result.sort()

        for item in self.result:
            print item


mapReducer = MapReduce()


def mapper(record):
    record = record.strip()

    if not record:
        return

    fields = record.split(',')

    if fields[0] == 'Employee':
        # Employee,Name,SSN
        name = fields[1]
        ssn = fields[2]

        mapReducer.emitIntermediate(ssn, ('Employee', name))

    elif fields[0] == 'Department':
        # Department,SSN,Department_Name
        ssn = fields[1]
        department = fields[2]

        mapReducer.emitIntermediate(ssn, ('Department', department))


def reducer(key, list_of_values):
    employees = []
    departments = []

    for value in list_of_values:
        if value[0] == 'Employee':
            employees.append(value[1])
        else:
            departments.append(value[1])

    # Join Employee and Department records having the same SSN
    for employee in employees:
        for department in departments:
            mapReducer.emit((key, employee, department))


if __name__ == '__main__':
    inputData = []

    for line in sys.stdin:
        inputData.append(line)

    mapReducer.execute(inputData, mapper, reducer)
