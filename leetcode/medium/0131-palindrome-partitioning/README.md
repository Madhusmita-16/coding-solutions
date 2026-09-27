# Palindrome Partitioning

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s`, partition `s` such that every substring of the partition is a  **palindrome**. Return  *all possible palindrome partitioning of* `s`.

 

 **Example 1:** 

```
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

```

 **Example 2:** 

```
Input: s = "a"
Output: [["a"]]

```

 

 **Constraints:** 

- 1 <= s.length <= 16
- s contains only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 15.92%)  
**Memory:** 65.2 MB (beats 74.76%)  
**Submitted:** 2026-09-27T17:34:06.055Z  

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start,
                            List<String> current,
                            List<List<String>> result) {

        // Entire string has been partitioned
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible substring
        for (int end = start; end < s.length(); end++) {

            if (isPalindrome(s, start, end)) {
                current.add(s.substring(start, end + 1));

                backtrack(s, end + 1, current, result);

                // Backtrack
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/palindrome-partitioning/)