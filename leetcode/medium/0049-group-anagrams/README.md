# Q3. Group Anagrams

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array of strings `strs`, group the anagrams together. You can return the answer in  **any order**.

 

 **Example 1:** 

 **Input:**  strs = ["eat","tea","tan","ate","nat","bat"]

 **Output:**  [["bat"],["nat","tan"],["ate","eat","tea"]]

 **Explanation:** 

- There is no string in strs that can be rearranged to form "bat".
- The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
- The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

 **Example 2:** 

 **Input:**  strs = [""]

 **Output:**  [[""]]

 **Example 3:** 

 **Input:**  strs = ["a"]

 **Output:**  [["a"]]

 

 **Constraints:** 

- 1 <= strs.length <= 104
- 0 <= strs[i].length <= 100
- strs[i] consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 19 ms (beats 16.30%)  
**Memory:** 50.5 MB (beats 12.48%)  
**Submitted:** 2026-08-27T09:22:33.041Z  

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            int[] count = new int[26];

            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            String key = Arrays.toString(count);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/group-anagrams/)