# euler022

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 22 from projecteuler.net

You are given around five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.

For example, when the list in sample is sorted into alphabetical order, `PAMELA`, which is worth, is the name in the list. So, `PAMELA` would obtain a score of.

You are given queries, each query is a name, you have to print the score.

 **Input Format** 

The first line contains an integer, i.e., number of names.
Next lines will contain a Name.
Followed by integer followed by lines each having a word.

 **Constraints** 

-
- length of each word will be less than
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
5
ALEX
LUIS
JAMES
BRIAN
PAMELA
1
PAMELA

```

 **Sample Output** 

```
240

```

 **Explanation** 

Explained in statement.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:03:31.236Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of names
        int n = sc.nextInt();

        String[] names = new String[n];

        // Read names
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
        }

        // Sort names alphabetically
        Arrays.sort(names);

        // Store name -> score
        HashMap<String, Long> scores = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = names[i];

            long alphabeticalValue = 0;

            // Calculate alphabetical value
            for (char c : name.toCharArray()) {
                alphabeticalValue += c - 'A' + 1;
            }

            // Position is i + 1 because indexing starts from 0
            long score = alphabeticalValue * (i + 1L);

            scores.put(name, score);
        }

        // Number of queries
        int q = sc.nextInt();

        // Answer each query
        for (int i = 0; i < q; i++) {
            String query = sc.next();
            System.out.println(scores.get(query));
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler022/problem)