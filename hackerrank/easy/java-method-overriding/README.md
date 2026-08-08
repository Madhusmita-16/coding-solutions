# Java BitSet

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

When a subclass inherits from a superclass, it also inherits its methods; however, it can also *override* the superclass methods (as well as declare and implement new ones). Consider the following *Sports* class:

```java
class Sports{
    String getName(){
        return "Generic Sports";
    }
    void getNumberOfTeamMembers(){
        System.out.println( "Each team has n players in " + getName() );
    }
}
```

Next, we create a *Soccer* class that inherits from the *Sports* class. We can override the *getName* method and return a different, subclass-specific string:

```java
class Soccer extends Sports{
    @Override
    String getName(){
        return "Soccer Class";
    }
}
```

**Note:** When overriding a method, you should precede it with the `@Override` annotation. The parameter(s) and return type of an overridden method must be exactly the same as those of the method inherited from the supertype. 

----

**Task**	
Complete the code in your editor by writing an overridden *getNumberOfTeamMembers* method that prints the same statement as the superclass' *getNumberOfTeamMembers* method, except that it replaces $n$ with $11$ (the number of players on a Soccer team). 

**Input Format**

 

**Constraints**

 

**Output Format**

When executed, your completed code should print the following:

    Generic Sports
    Each team has n players in Generic Sports
    Soccer Class
    Each team has 11 players in Soccer Class

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:03:45.521Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/java-method-overriding/problem)