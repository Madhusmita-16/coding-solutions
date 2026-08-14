# Q2. Longest Word in Dictionary

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array of strings `words` representing an English Dictionary, return  *the longest word in*  `words`  *that can be built one character at a time by other words in*  `words`.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

Note that the word should be built from left to right with each additional character being added to the end of a previous word. 

 

 **Example 1:** 

```
Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".

```

 **Example 2:** 

```
Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".

```

 

 **Constraints:** 

- 1 <= words.length <= 1000
- 1 <= words[i].length <= 30
- words[i] consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 49.60%)  
**Memory:** 46.4 MB (beats 86.00%)  
**Submitted:** 2026-08-14T17:44:20.193Z  

```java
import java.util.*;

class Solution {
    public String longestWord(String[] words) {

        // Sort lexicographically so that when two words
        // have the same length, the smaller one comes first.
        Arrays.sort(words);

        Set<String> built = new HashSet<>();
        String answer = "";

        for (String word : words) {

            // A word can be built only if its prefix
            // without the last character already exists.
            if (word.length() == 1 || built.contains(word.substring(0, word.length() - 1))) {

                built.add(word);

                if (word.length() > answer.length()) {
                    answer = word;
                }
            }
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-word-in-dictionary/)