# Java Sort

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Java's [BitSet](https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html) class implements a vector of bit values (i.e.: $false$ ($0$) or $true$ ($1$)) that grows as needed, allowing us to easily manipulate bits while optimizing space (when compared to other collections). Any element having a bit value of $1$ is called a *set bit*.

Given $2$ BitSets, $B_1$ and $B_2$, of size $N$ where all bits in both BitSets are initialized to $0$, perform a series of $M$ operations. After each operation, print the number of *set bits* in the respective BitSets as two space-separated integers on a new line.

**Input Format**

The first line contains $2$ space-separated integers, $N$ (the length of both BitSets $B_1$ and $B_2$) and $M$ (the number of operations to perform), respectively. 	
The $M$ subsequent lines each contain an operation in one of the following forms:

* [AND](https://en.wikipedia.org/wiki/Logical_conjunction) $ \ \text{<set> <set>}$
* [OR](https://en.wikipedia.org/wiki/Logical_disjunction) $ \ \text{<set> <set>}$
* [XOR](https://en.wikipedia.org/wiki/Exclusive_or) $ \ \text{<set> <set>}$
* [FLIP](https://en.wikipedia.org/wiki/Bitwise_operation#NOT)$ \ \text{<set> <index>}$
* [SET](https://docs.oracle.com/javase/7/docs/api/java/util/BitSet.html#set(int)) $ \ \text{<set> <index>}$

In the list above, $\text{<set>}$ is the integer $1$ or $2$, where $1$ denotes $B_1$ and $2$ denotes $B_2$. 	
$\text{<index>}$ is an integer denoting a bit's index in the BitSet corresponding to $\text{<set>}$. 

For the binary operations $AND$, $OR$, and $XOR$, operands are read from left to right and the BitSet resulting from the operation replaces the contents of the *first operand*. For example:

    AND 2 1

$B_2$ is the left operand, and $B_1$ is the right operand. This operation should assign the result of $B_2 \land B_1$ to $B_2$. 

**Constraints**

- $1 \le N \le 1000$
- $1 \le M \le 10000$

**Output Format**

After each operation, print the respective number of *set bits* in BitSet $B_1$ and BitSet $B_2$ as $2$ space-separated integers on a new line.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T19:29:15.100Z  

```java
import java.util.*;

class Student {
    private int id;
    private String fname;
    private double cgpa;

    public Student(int id, String fname, double cgpa) {
        super();
        this.id = id;
        this.fname = fname;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public double getCgpa() {
        return cgpa;
    }
}

// Comparator for sorting students
class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {

        // 1. CGPA: decreasing order
        if (s1.getCgpa() != s2.getCgpa()) {
            return Double.compare(s2.getCgpa(), s1.getCgpa());
        }

        // 2. First name: alphabetical order
        if (!s1.getFname().equals(s2.getFname())) {
            return s1.getFname().compareTo(s2.getFname());
        }

        // 3. ID: increasing order
        return Integer.compare(s1.getId(), s2.getId());
    }
}

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<Student>();

        while (testCases > 0) {

            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        // Sort using comparator
        Collections.sort(studentList, new StudentComparator());

        // Print names
        for (Student st : studentList) {
            System.out.println(st.getFname());
        }

        in.close();
    }
}



```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-bitset/problem)