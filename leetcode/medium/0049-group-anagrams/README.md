# Group Anagrams

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
**Runtime:** 18 ms (beats 19.89%)  
**Memory:** 51.3 MB (beats 5.88%)  
**Submitted:** 2026-08-15T14:03:37.602Z  

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

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/group-anagrams/)