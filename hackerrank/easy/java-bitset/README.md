# Java BitSet

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
**Submitted:** 2026-08-08T11:03:34.214Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BitSet[] sets = new BitSet[3];
        sets[1] = new BitSet(n);
        sets[2] = new BitSet(n);

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken());

            if (op.equals("AND") || op.equals("OR") || op.equals("XOR")) {
                int b = Integer.parseInt(st.nextToken());
                if (op.equals("AND")) {
                    sets[a].and(sets[b]);
                } else if (op.equals("OR")) {
                    sets[a].or(sets[b]);
                } else {
                    sets[a].xor(sets[b]);
                }
            } else {
                int index = Integer.parseInt(st.nextToken());
                if (op.equals("FLIP")) {
                    sets[a].flip(index);
                } else {
                    sets[a].set(index);
                }
            }

            out.append(sets[1].cardinality()).append(" ").append(sets[2].cardinality()).append("\n");
        }

        System.out.print(out);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-bitset/problem)