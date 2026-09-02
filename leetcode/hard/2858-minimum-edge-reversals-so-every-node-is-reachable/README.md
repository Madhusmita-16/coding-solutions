# Q3. Minimum Edge Reversals So Every Node Is Reachable

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

There is a  **simple directed graph**  with `n` nodes labeled from `0` to `n - 1`. The graph would form a  **tree**  if its edges were bi-directional.

You are given an integer `n` and a  **2D**  integer array `edges`, where `edges[i] = [ui, vi]` represents a  **directed edge**  going from node `ui` to node `vi`.

An  **edge reversal**  changes the direction of an edge, i.e., a directed edge going from node `ui` to node `vi` becomes a directed edge going from node `vi` to node `ui`.

For every node `i` in the range `[0, n - 1]`, your task is to  **independently**  calculate the  **minimum**  number of  **edge reversals**  required so it is possible to reach any other node starting from node `i` through a  **sequence**  of  **directed edges**.

Return  *an integer array* `answer` *, where* `answer[i]` *is the*   ***minimum**  number of  **edge reversals**  required so it is possible to reach any other node starting from node  *`i`*  through a  **sequence**  of  **directed edges**.*

 

 **Example 1:** 

```
Input: n = 4, edges = [[2,0],[2,1],[1,3]]
Output: [1,1,0,2]
Explanation: The image above shows the graph formed by the edges.
For node 0: after reversing the edge [2,0], it is possible to reach any other node starting from node 0.
So, answer[0] = 1.
For node 1: after reversing the edge [2,1], it is possible to reach any other node starting from node 1.
So, answer[1] = 1.
For node 2: it is already possible to reach any other node starting from node 2.
So, answer[2] = 0.
For node 3: after reversing the edges [1,3] and [2,1], it is possible to reach any other node starting from node 3.
So, answer[3] = 2.

```

 **Example 2:** 

```
Input: n = 3, edges = [[1,2],[2,0]]
Output: [2,0,1]
Explanation: The image above shows the graph formed by the edges.
For node 0: after reversing the edges [2,0] and [1,2], it is possible to reach any other node starting from node 0.
So, answer[0] = 2.
For node 1: it is already possible to reach any other node starting from node 1.
So, answer[1] = 0.
For node 2: after reversing the edge [1, 2], it is possible to reach any other node starting from node 2.
So, answer[2] = 1.

```

 

 **Constraints:** 

- 2 <= n <= 105
- edges.length == n - 1
- edges[i].length == 2
- 0 <= ui == edges[i][0] < n
- 0 <= vi == edges[i][1] < n
- ui != vi
- The input is generated such that if the edges were bi-directional, the graph would be a tree.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 43.1 MB  
**Submitted:** 2026-09-02T05:53:33.701Z  

```java
import java.util.*;

class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Original direction: u -> v
            graph[u].add(new int[]{v, 0});

            // Reverse direction: v -> u
            graph[v].add(new int[]{u, 1});
        }

        int[] answer = new int[n];

        // Find answer[0]
        dfs(0, -1, graph, answer);

        // Calculate answers for all other nodes
        dfsReroot(0, -1, graph, answer);

        return answer;
    }

    private void dfs(int node, int parent,
                     List<int[]>[] graph, int[] answer) {

        for (int[] edge : graph[node]) {

            int next = edge[0];
            int cost = edge[1];

            if (next == parent) {
                continue;
            }

            answer[0] += cost;

            dfs(next, node, graph, answer);
        }
    }

    private void dfsReroot(int node, int parent,
                           List<int[]>[] graph, int[] answer) {

        for (int[] edge : graph[node]) {

            int next = edge[0];
            int cost = edge[1];

            if (next == parent) {
                continue;
            }

            /*
             * Moving the starting point from node -> next
             *
             * cost == 0:
             *   Original edge is node -> next.
             *   For next, this edge must be reversed.
             *   Therefore +1.
             *
             * cost == 1:
             *   Original edge is next -> node.
             *   For next, this edge is already correct.
             *   Therefore -1.
             */
            answer[next] = answer[node] + (cost == 0 ? 1 : -1);

            dfsReroot(next, node, graph, answer);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-edge-reversals-so-every-node-is-reachable/)