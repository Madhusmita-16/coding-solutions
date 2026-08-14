# Remove Methods From Project

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are maintaining a project that has `n` methods numbered from `0` to `n - 1`.

You are given two integers `n` and `k`, and a 2D integer array `invocations`, where `invocations[i] = [ai, bi]` indicates that method `ai` invokes method `bi`.

There is a known bug in method `k`. Method `k`, along with any method invoked by it, either  **directly**  or  **indirectly**, are considered  **suspicious**  and we aim to remove them.

A group of methods can only be removed if no method  **outside**  the group invokes any methods  **within**  it.

Return an array containing all the remaining methods after removing all the  **suspicious**  methods. You may return the answer in  *any order*. If it is not possible to remove  **all**  the suspicious methods,  **none**  should be removed.

 

 **Example 1:** 

 **Input:**  n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]

 **Output:**  [0,1,2,3]

 **Explanation:** 

Method 2 and method 1 are suspicious, but they are directly invoked by methods 3 and 0, which are not suspicious. We return all elements without removing anything.

 **Example 2:** 

 **Input:**  n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]

 **Output:**  [3,4]

 **Explanation:** 

Methods 0, 1, and 2 are suspicious and they are not directly invoked by any other method. We can remove them.

 **Example 3:** 

 **Input:**  n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]

 **Output:**  []

 **Explanation:** 

All methods are suspicious. We can remove them.

 

 **Constraints:** 

- 1 <= n <= 105
- 0 <= k <= n - 1
- 0 <= invocations.length <= 2 * 105
- invocations[i] == [ai, bi]
- 0 <= ai, bi <= n - 1
- ai != bi
- invocations[i] != invocations[j]

## Solution

**Language:** Java  
**Runtime:** 49 ms (beats 87.38%)  
**Memory:** 281.1 MB (beats 79.42%)  
**Submitted:** 2026-08-14T18:27:31.324Z  

```java
import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build invocation graph
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        suspicious[k] = true;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    queue.offer(v);
                }
            }
        }

        // If any non-suspicious method invokes a suspicious method,
        // the suspicious group cannot be removed.
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                // Cannot remove suspicious methods
                List<Integer> result = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    result.add(i);
                }

                return result;
            }
        }

        // All suspicious methods can be safely removed
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/remove-methods-from-project/)