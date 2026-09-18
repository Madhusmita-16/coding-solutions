# Map Reduce Advanced - Count number of friends

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

**Mappers and Reducers**

[Here's](http://www.slideshare.net/rantav/introduction-to-map-reduce) a quick but comprehensive introduction to the idea of splitting tasks into a MapReduce model. 
The four important functions involved are:
<pre>
Map (the mapper function)  
EmitIntermediate(the intermediate key,value pairs emitted by the mapper functions)  
Reduce (the reducer function)  
Emit (the final output, after summarization from the Reduce functions)
</pre>
We provide you with a single system, single thread version of a basic MapReduce implementation.

**Task**

Joins are

The input is a number of lines with pairs of name of friends, in the form:<br/>
<pre>
[Friend1] [Friend2]
</pre>
The required output is to print the number of friends of each person, in the format shown. The code for the MapReduce class, parts related to IO etc. has already been provided. However, the mapper and reducer functions are incomplete. Your task is to fill up the mapper and reducer functions appropriately, such that the program works, and outputs the list of number of friends of each person , in lexicographical order.

Also, this program outputs certain information to the error stream. This information has been logged to help beginners gain a better understanding of the the intermediate steps in a map-reduce process.

**Languages Supported**

Currently, we provide the base code in Python.

**Input Format**

A list of single space separated pairs of friend names. We have already written the input handling code to read in this data.

**Output Format**

Again, the output handling part has already been provided in the template code. The Key contains [Person name] and the value contains the number of friends, sorted in lexicographical order. The entities in this list, will naturally be confined to only those people provided in the input data.

## Solution

**Language:** Python  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-18T16:12:43.467Z  

```py
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
            print("{\"key\":\"" + item[0] + "\",\"value\":\"" + str(item[1]) + "\"}")


mapReducer = MapReduce()


def mapper(record):
    # Remove newline and split the two names
    record = record.strip()
    if not record:
        return

    friend1, friend2 = record.split()

    # Each person gets one friend
    mapReducer.emitIntermediate(friend1, 1)
    mapReducer.emitIntermediate(friend2, 1)


def reducer(key, list_of_values):
    # Count the number of friends
    count = sum(list_of_values)

    # Emit (person, number of friends)
    mapReducer.emit((key, count))


if __name__ == '__main__':
    inputData = []

    for line in sys.stdin:
        inputData.append(line)

    mapReducer.execute(inputData, mapper, reducer)

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/map-reduce-advanced-count-number-of-friends/problem)