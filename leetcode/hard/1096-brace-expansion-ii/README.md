# Brace Expansion II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Under the grammar given below, strings can represent a set of lowercase words. Let `R(expr)` denote the set of words the expression represents.

The grammar can best be understood through simple examples:

- Single letters represent a singleton set containing that word. R("a") = {"a"} R("w") = {"w"}
- When we take a comma-delimited list of two or more expressions, we take the union of possibilities. R("{a,b,c}") = {"a","b","c"} R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
- When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression. R("{a,b}{c,d}") = {"ac","ad","bc","bd"} R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}

Formally, the three rules for our grammar:

- For every lowercase letter x, we have R(x) = {x}.
- For expressions e1, e2,..., ek with k >= 2, we have R({e1, e2,...}) = R(e1) ∪ R(e2) ∪...
- For expressions e1 and e2, we have R(e1 + e2) = {a + b for (a, b) in R(e1) × R(e2)}, where + denotes concatenation, and × denotes the cartesian product.

Given an expression representing a set of words under the given grammar, return  *the sorted list of words that the expression represents*.

 

 **Example 1:** 

```
Input: expression = "{a,b}{c,{d,e}}"
Output: ["ac","ad","ae","bc","bd","be"]

```

 **Example 2:** 

```
Input: expression = "{{a,z},a{b,c},{ab,z}}"
Output: ["a","ab","ac","z"]
Explanation: Each distinct word is written only once in the final answer.

```

 

 **Constraints:** 

- 1 <= expression.length <= 60
- expression[i] consists of '{', '}', ','or lowercase English letters.
- The given expression represents a set of words based on the grammar given in the description.

## Solution

**Language:** Java  
**Runtime:** 11 ms (beats 47.20%)  
**Memory:** 47.1 MB (beats 64.00%)  
**Submitted:** 2026-09-25T09:33:51.172Z  

```java
import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length());

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parse(String s, int start, int end) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = start;

        while (i < end) {
            char ch = s.charAt(i);

            // Union
            if (ch == ',') {
                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;
            }

            // Nested expression
            else if (ch == '{') {
                int j = findClosingBrace(s, i);

                Set<String> inside = parse(s, i + 1, j);

                current = multiply(current, inside);

                i = j + 1;
            }

            // Letter
            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = multiply(current, letter);

                i++;
            }
        }

        result.addAll(current);

        return result;
    }

    private int findClosingBrace(String s, int open) {
        int balance = 0;

        for (int i = open; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;

                if (balance == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/brace-expansion-ii/)