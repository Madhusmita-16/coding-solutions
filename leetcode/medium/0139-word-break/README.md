# Word Break

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
**Runtime:** 1 ms (beats 99.35%)  
**Memory:** 42.9 MB (beats 93.13%)  
**Submitted:** 2026-08-15T06:41:18.201Z  

```java
import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();

        Set<String> dict = new HashSet<>(wordDict);

        // dp[i] = true if s[0...i-1] can be segmented
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int len = 1; len <= 20 && len <= i; len++) {

                if (dp[i - len] &&
                    dict.contains(s.substring(i - len, i))) {

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