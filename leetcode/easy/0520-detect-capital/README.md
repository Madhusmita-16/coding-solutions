# Q1. Detect Capital

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

We define the usage of capitals in a word to be right when one of the following cases holds:

- All letters in this word are capitals, like "USA".
- All letters in this word are not capitals, like "leetcode".
- Only the first letter in this word is capital, like "Google".

Given a string `word`, return `true` if the usage of capitals in it is right.

 

 **Example 1:** 

```
Input: word = "USA"
Output: true

```

 **Example 2:** 

```
Input: word = "FlaG"
Output: false

```

 

 **Constraints:** 

- 1 <= word.length <= 100
- word consists of lowercase and uppercase English letters.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 89.42%)  
**Memory:** 42.9 MB (beats 95.54%)  
**Submitted:** 2026-08-14T11:41:07.558Z  

```java
class Solution {
    public boolean detectCapitalUse(String word) {

        int upper = 0;

        for (char ch : word.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upper++;
            }
        }

        // All uppercase
        if (upper == word.length()) {
            return true;
        }

        // All lowercase
        if (upper == 0) {
            return true;
        }

        // Only first character uppercase
        return upper == 1 && Character.isUpperCase(word.charAt(0));
    }
}
 

```

---

[View on LeetCode](https://leetcode.com/problems/detect-capital/)