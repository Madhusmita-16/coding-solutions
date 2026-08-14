# Q3. Word Break

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a string `s` and a dictionary of strings `wordDict`, return `true` if `s` can be segmented into a space-separated sequence of one or more dictionary words.

 **Note**  that the same word in the dictionary may be reused multiple times in the segmentation.

 

 **Example 1:** 

```
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

```

 **Example 2:** 

```
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

```

 **Example 3:** 

```
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

```

 

 **Constraints:** 

- 1 <= s.length <= 300
- 1 <= wordDict.length <= 1000
- 1 <= wordDict[i].length <= 20
- s and wordDict[i] consist of only lowercase English letters.
- All the strings of wordDict are unique.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-08-14T17:55:54.565Z  

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();

        // dp[i] = true if s[0...i-1] can be segmented
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (String word : wordDict) {

                int len = word.length();

                if (len <= i && dp[i - len]
                        && s.substring(i - len, i).equals(word)) {

                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/word-break/)