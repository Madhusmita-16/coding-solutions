# Total Cost to Hire K Workers

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **0-indexed**  integer array `costs` where `costs[i]` is the cost of hiring the `ith` worker.

You are also given two integers `k` and `candidates`. We want to hire exactly `k` workers according to the following rules:

- You will run k sessions and hire exactly one worker in each session.
- In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index. For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2]. In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
- If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
- A worker can only be chosen once.

Return  *the total cost to hire exactly* `k` *workers.* 

 

 **Example 1:** 

```
Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
Output: 11
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
- In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
- In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
The total hiring cost is 11.

```

 **Example 2:** 

```
Input: costs = [1,2,4,1], k = 3, candidates = 3
Output: 4
Explanation: We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
- In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
- In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
The total hiring cost is 4.

```

 

 **Constraints:** 

- 1 <= costs.length <= 105
- 1 <= costs[i] <= 105
- 1 <= k, candidates <= costs.length

## Solution

**Language:** Java  
**Runtime:** 107 ms (beats 5.35%)  
**Memory:** 84.1 MB (beats 9.85%)  
**Submitted:** 2026-08-27T10:51:32.677Z  

```java
import java.util.*;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;

        // Each element: [cost, index]
        PriorityQueue<int[]> left =
            new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

        PriorityQueue<int[]> right =
            new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

        int l = 0;
        int r = n - 1;

        // Add first candidates
        while (l <= r && left.size() < candidates) {
            left.offer(new int[]{costs[l], l});
            l++;
        }

        // Add last candidates without overlapping
        while (l <= r && right.size() < candidates) {
            right.offer(new int[]{costs[r], r});
            r--;
        }

        long total = 0;

        for (int i = 0; i < k; i++) {

            // Choose the cheaper worker
            if (right.isEmpty() ||
                (!left.isEmpty() &&
                 (left.peek()[0] < right.peek()[0] ||
                  (left.peek()[0] == right.peek()[0] &&
                   left.peek()[1] < right.peek()[1])))) {

                int[] worker = left.poll();
                total += worker[0];

                // Add next worker from the middle
                if (l <= r) {
                    left.offer(new int[]{costs[l], l});
                    l++;
                }

            } else {

                int[] worker = right.poll();
                total += worker[0];

                // Add next worker from the middle
                if (l <= r) {
                    right.offer(new int[]{costs[r], r});
                    r--;
                }
            }
        }

        return total;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/total-cost-to-hire-k-workers/)