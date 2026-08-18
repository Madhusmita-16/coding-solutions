# Stone Game II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Alice and Bob continue their games with piles of stones. There are a number of piles  **arranged in a row**, and each pile has a positive integer number of stones `piles[i]`. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take  **all the stones**  in the  **first**  `X` remaining piles, where `1 <= X <= 2M`. Then, we set `M = max(M, X)`. Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

 **Example 1:** 

 **Input:**  piles = [2,7,9,4,4]

 **Output:**  10

 **Explanation:** 

- If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
- If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.

So we return 10 since it's larger.

 **Example 2:** 

 **Input:**  piles = [1,2,3,4,5,100]

 **Output:**  104

 

 **Constraints:** 

- 1 <= piles.length <= 100
- 1 <= piles[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 45.08%)  
**Memory:** 44 MB (beats 82.75%)  
**Submitted:** 2026-08-18T14:17:21.120Z  

```java
class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // suffix[i] = sum of piles from i to n-1
        int[] suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        // dp[i][m] = maximum stones current player can get
        // starting at i with M = m
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {

                // Can take all remaining piles
                if (i + 2 * m >= n) {
                    dp[i][m] = suffix[i];
                    continue;
                }

                int best = 0;

                for (int x = 1; x <= 2 * m; x++) {
                    int opponent = dp[i + x][Math.max(m, x)];

                    int current = suffix[i] - opponent;

                    best = Math.max(best, current);
                }

                dp[i][m] = best;
            }
        }

        return dp[0][1];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/stone-game-ii/)