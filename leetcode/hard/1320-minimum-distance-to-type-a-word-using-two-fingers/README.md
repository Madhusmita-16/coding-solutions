# Minimum Distance to Type a Word Using Two Fingers

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You have a keyboard layout as shown above in the  **X-Y**  plane, where each English uppercase letter is located at some coordinate.

- For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).

Given the string `word`, return  *the minimum total  **distance**  to type such string using only two fingers*.

The  **distance**  between coordinates `(x1, y1)` and `(x2, y2)` is `|x1 - x2| + |y1 - y2|`.

 **Note**  that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.

 

 **Example 1:** 

```
Input: word = "CAKE"
Output: 3
Explanation: Using two fingers, one optimal way to type "CAKE" is: 
Finger 1 on letter 'C' -> cost = 0 
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
Finger 2 on letter 'K' -> cost = 0 
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
Total distance = 3

```

 **Example 2:** 

```
Input: word = "HAPPY"
Output: 6
Explanation: Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6

```

 

 **Constraints:** 

- 2 <= word.length <= 300
- word consists of uppercase English letters.

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 86.90%)  
**Memory:** 43.8 MB (beats 73.45%)  
**Submitted:** 2026-08-21T07:16:43.221Z  

```java
class Solution {
    public int minimumDistance(String word) {
        int n = word.length();

        // dp[other] = minimum cost after typing the current character,
        // where one finger is on the current character and the other
        // finger is at position 'other'.
        int[][] dist = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = distance(i, j);
            }
        }

        int INF = 1_000_000_000;

        int[] dp = new int[27];
        for (int i = 0; i < 27; i++) {
            dp[i] = INF;
        }

        // 26 means the second finger is still unused/free.
        dp[26] = 0;

        int prev = word.charAt(0) - 'A';

        for (int i = 1; i < n; i++) {
            int cur = word.charAt(i) - 'A';

            int[] next = new int[27];
            for (int j = 0; j < 27; j++) {
                next[j] = INF;
            }

            for (int other = 0; other < 27; other++) {
                if (dp[other] == INF) {
                    continue;
                }

                // Use the finger currently on 'prev'.
                int cost = dp[other] + distance(prev, cur);
                next[other] = Math.min(next[other], cost);

                // Use the other finger.
                if (other == 26) {
                    // Its initial position is free.
                    next[prev] = Math.min(next[prev], dp[other]);
                } else {
                    cost = dp[other] + distance(other, cur);
                    next[prev] = Math.min(next[prev], cost);
                }
            }

            dp = next;
            prev = cur;
        }

        int answer = INF;

        for (int cost : dp) {
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    private int distance(int a, int b) {
        if (a == 26 || b == 26) {
            return 0;
        }

        int x1 = a / 6;
        int y1 = a % 6;

        int x2 = b / 6;
        int y2 = b % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-distance-to-type-a-word-using-two-fingers/)