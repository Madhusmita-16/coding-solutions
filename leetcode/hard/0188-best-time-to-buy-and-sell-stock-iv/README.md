# Best Time to Buy and Sell Stock IV

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `prices` where `prices[i]` is the price of a given stock on the `ith` day, and an integer `k`.

Find the maximum profit you can achieve. You may complete at most `k` transactions: i.e. you may buy at most `k` times and sell at most `k` times.

 **Note:**  You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

 **Example 1:** 

```
Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

```

 **Example 2:** 

```
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

```

 

 **Constraints:** 

- 1 <= k <= 100
- 1 <= prices.length <= 1000
- 0 <= prices[i] <= 1000

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.83%)  
**Memory:** 43.3 MB (beats 82.98%)  
**Submitted:** 2026-08-15T06:32:24.402Z  

```java
class Solution {
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        if (n == 0 || k == 0) {
            return 0;
        }

        // If k is large enough, this becomes unlimited transactions.
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // Initially, buying costs money.
        for (int t = 1; t <= k; t++) {
            buy[t] = -prices[0];
        }

        for (int price : prices) {

            for (int t = 1; t <= k; t++) {

                // Buy using the profit after t-1 completed transactions
                buy[t] = Math.max(buy[t], sell[t - 1] - price);

                // Sell and complete transaction t
                sell[t] = Math.max(sell[t], buy[t] + price);
            }
        }

        return sell[k];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/)