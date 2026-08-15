# Critical Connections in a Network

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

There are `n` servers numbered from `0` to `n - 1` connected by undirected server-to-server `connections` forming a network where `connections[i] = [ai, bi]` represents a connection between servers `ai` and `bi`. Any server can reach other servers directly or indirectly through the network.

A  *critical connection*  is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

 **Example 1:** 

```
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.

```

 **Example 2:** 

```
Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

```

 

 **Constraints:** 

- 2 <= n <= 105
- n - 1 <= connections.length <= 105
- 0 <= ai, bi <= n - 1
- ai != bi
- There are no repeated connections.

## Solution

**Language:** Java  
**Runtime:** 94 ms (beats 50.52%)  
**Memory:** 236.4 MB (beats 45.79%)  
**Submitted:** 2026-08-15T06:15:52.489Z  

```java
import java.util.*;

class Solution {

    private int time;
    private List<List<Integer>> graph;
    private List<List<Integer>> result;
    private int[] discovery;
    private int[] low;

    public List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> connections) {

        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n];
        low = new int[n];
        time = 0;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build undirected graph
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Start DFS
        dfs(0, -1);

        return result;
    }

    private void dfs(int node, int parent) {

        discovery[node] = low[node] = ++time;

        for (int next : graph.get(node)) {

            // Ignore the edge back to parent
            if (next == parent) {
                continue;
            }

            // Unvisited node
            if (discovery[next] == 0) {

                dfs(next, node);

                // Update lowest reachable discovery time
                low[node] = Math.min(low[node], low[next]);

                // Bridge condition
                if (low[next] > discovery[node]) {
                    result.add(Arrays.asList(node, next));
                }

            } else {
                // Back edge
                low[node] = Math.min(low[node], discovery[next]);
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/critical-connections-in-a-network/)