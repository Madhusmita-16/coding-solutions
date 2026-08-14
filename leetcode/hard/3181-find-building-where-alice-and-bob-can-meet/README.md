# Q2. Find Building Where Alice and Bob Can Meet

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **0-indexed**  array `heights` of positive integers, where `heights[i]` represents the height of the `ith` building.

If a person is in building `i`, they can move to any other building `j` if and only if `i < j` and `heights[i] < heights[j]`.

You are also given another array `queries` where `queries[i] = [ai, bi]`. On the `ith` query, Alice is in building `ai` while Bob is in building `bi`.

Return  *an array*  `ans`  *where*  `ans[i]`  *is  **the index of the leftmost building**  where Alice and Bob can meet on the*  `ith`  *query*.  *If Alice and Bob cannot move to a common building on query*  `i`,  *set*  `ans[i]`  *to*  `-1`.

 

 **Example 1:** 

```
Input: heights = [6,4,8,5,2,7], queries = [[0,1],[0,3],[2,4],[3,4],[2,2]]
Output: [2,5,-1,5,2]
Explanation: In the first query, Alice and Bob can move to building 2 since heights[0] < heights[2] and heights[1] < heights[2]. 
In the second query, Alice and Bob can move to building 5 since heights[0] < heights[5] and heights[3] < heights[5]. 
In the third query, Alice cannot meet Bob since Alice cannot move to any other building.
In the fourth query, Alice and Bob can move to building 5 since heights[3] < heights[5] and heights[4] < heights[5].
In the fifth query, Alice and Bob are already in the same building.  
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

```

 **Example 2:** 

```
Input: heights = [5,3,8,2,6,1,4,6], queries = [[0,7],[3,5],[5,2],[3,0],[1,6]]
Output: [7,6,-1,4,6]
Explanation: In the first query, Alice can directly move to Bob's building since heights[0] < heights[7].
In the second query, Alice and Bob can move to building 6 since heights[3] < heights[6] and heights[5] < heights[6].
In the third query, Alice cannot meet Bob since Bob cannot move to any other building.
In the fourth query, Alice and Bob can move to building 4 since heights[3] < heights[4] and heights[0] < heights[4].
In the fifth query, Alice can directly move to Bob's building since heights[1] < heights[6].
For ans[i] != -1, It can be shown that ans[i] is the leftmost building where Alice and Bob can meet.
For ans[i] == -1, It can be shown that there is no building where Alice and Bob can meet.

```

 

 **Constraints:** 

- 1 <= heights.length <= 5 * 104
- 1 <= heights[i] <= 109
- 1 <= queries.length <= 5 * 104
- queries[i] = [ai, bi]
- 0 <= ai, bi <= heights.length - 1

## Solution

**Language:** Java  
**Runtime:** 48 ms (beats 61.88%)  
**Memory:** 187.2 MB (beats 39.53%)  
**Submitted:** 2026-08-14T17:38:01.450Z  

```java
import java.util.*;

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int q = queries.length;
        int[] ans = new int[q];
        Arrays.fill(ans, -1);

        // Queries grouped by the larger index
        List<int[]>[] waiting = new ArrayList[heights.length];

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            // Same building
            if (a == b) {
                ans[i] = a;
                continue;
            }

            // Normalize: a < b
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Alice can directly move to Bob's building
            if (heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                if (waiting[b] == null) {
                    waiting[b] = new ArrayList<>();
                }

                // Store: [height needed, query index]
                waiting[b].add(new int[]{heights[a], i});
            }
        }

        /*
         * Monotonic stack of buildings to the right.
         * We maintain buildings in decreasing height order.
         */
        List<Integer> stack = new ArrayList<>();

        for (int i = heights.length - 1; i >= 0; i--) {

            // Process queries whose right endpoint is i
            if (waiting[i] != null) {
                for (int[] query : waiting[i]) {
                    int requiredHeight = query[0];
                    int queryIndex = query[1];

                    // Find leftmost building with height > requiredHeight
                    int left = 0;
                    int right = stack.size() - 1;
                    int result = -1;

                    while (left <= right) {
                        int mid = left + (right - left) / 2;

                        if (heights[stack.get(mid)] > requiredHeight) {
                            result = stack.get(mid);
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }

                    ans[queryIndex] = result;
                }
            }

            /*
             * Add current building to monotonic stack.
             * Remove buildings that can never be the leftmost answer.
             */
            while (!stack.isEmpty()
                    && heights[stack.get(stack.size() - 1)] <= heights[i]) {
                stack.remove(stack.size() - 1);
            }

            stack.add(i);
        }

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet/)