# Q2. Add Edges to Make Degrees of All Nodes Even

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

There is an  **undirected**  graph consisting of `n` nodes numbered from `1` to `n`. You are given the integer `n` and a  **2D**  array `edges` where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi`. The graph can be disconnected.

You can add  **at most**  two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.

Return `true` *if it is possible to make the degree of each node in the graph even, otherwise return* `false` *.* 

The degree of a node is the number of edges connected to it.

 

 **Example 1:** 

```
Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
Output: true
Explanation: The above diagram shows a valid way of adding an edge.
Every node in the resulting graph is connected to an even number of edges.

```

 **Example 2:** 

```
Input: n = 4, edges = [[1,2],[3,4]]
Output: true
Explanation: The above diagram shows a valid way of adding two edges.
```

 **Example 3:** 

```
Input: n = 4, edges = [[1,2],[1,3],[1,4]]
Output: false
Explanation: It is not possible to obtain a valid graph with adding at most 2 edges.
```

 

 **Constraints:** 

- 3 <= n <= 105
- 2 <= edges.length <= 105
- edges[i].length == 2
- 1 <= ai, bi <= n
- ai != bi
- There are no repeated edges.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-08-14T16:53:27.134Z  

```java
import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        int[] degree = new int[n + 1];
        boolean[][] graph = new boolean[n + 1][n + 1];

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            degree[u]++;
            degree[v]++;

            graph[u][v] = true;
            graph[v][u] = true;
        }

        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                odd.add(i);
            }
        }

        // No odd-degree vertices
        if (odd.size() == 0) {
            return true;
        }

        // More than 4 odd vertices cannot be fixed with at most 2 edges
        if (odd.size() > 4) {
            return false;
        }

        // Two odd vertices
        if (odd.size() == 2) {
            int a = odd.get(0);
            int b = odd.get(1);

            // Case 1: Directly connect a and b
            if (!graph[a][b]) {
                return true;
            }

            // Case 2: a-x-b using two new edges
            for (int x = 1; x <= n; x++) {
                if (x != a && x != b
                        && !graph[a][x]
                        && !graph[b][x]) {
                    return true;
                }
            }

            return false;
        }

        // Four odd vertices
        if (odd.size() == 4) {
            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            // Pair 1
            if (!graph[a][b] && !graph[c][d]) {
                return true;
            }

            // Pair 2
            if (!graph[a][c] && !graph[b][d]) {
                return true;
            }

            // Pair 3
            if (!graph[a][d] && !graph[b][c]) {
                return true;
            }

            return false;
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/add-edges-to-make-degrees-of-all-nodes-even/)