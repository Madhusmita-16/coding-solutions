# Java List

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given a phone book that consists of people's names and their phone number. After that you will be given some person's name as query. For each query, print the phone number of that person.

**Input Format**

The first line will have an integer $n$ denoting the number of entries in the phone book. Each entry consists of two lines: a name and the corresponding phone number. <br>

After these, there will be some queries. Each query will contain a person's name. Read the queries until end-of-file.

*Constraints:*<br>
A person's name consists of only lower-case English letters and it may be in the format 'first-name last-name' or in the format 'first-name'. Each phone number has exactly 8 digits without any leading zeros.<br>

$1 \le n \le 100000$<br>
$1 \le Query \le 100000$<br>

**Output Format**

For each case, print "Not found" if the person has no entry in the phone book. Otherwise, print the person's name and phone number. See sample output for the exact format.

To make the problem easier, we provided a portion of the code in the editor. You can either complete that code or write completely on your own.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T08:41:35.346Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {

            String operation = sc.next();

            if (operation.equals("Insert")) {
                int index = sc.nextInt();
                int value = sc.nextInt();

                list.add(index, value);

            } else if (operation.equals("Delete")) {
                int index = sc.nextInt();

                list.remove(index);
            }
        }

        for (int value : list) {
            System.out.print(value + " ");
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/phone-book/problem)