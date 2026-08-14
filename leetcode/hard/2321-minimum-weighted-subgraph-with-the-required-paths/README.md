# Q2. Minimum Weighted Subgraph With the Required Paths

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer `n` denoting the number of nodes of a  **weighted directed**  graph. The nodes are numbered from `0` to `n - 1`.

You are also given a 2D integer array `edges` where `edges[i] = [fromi, toi, weighti]` denotes that there exists a  **directed**  edge from `fromi` to `toi` with weight `weighti`.

Lastly, you are given three  **distinct**  integers `src1`, `src2`, and `dest` denoting three distinct nodes of the graph.

Return  *the  **minimum weight**  of a subgraph of the graph such that it is  **possible**  to reach*  `dest`  *from both*  `src1`  *and*  `src2`  *via a set of edges of this subgraph*. In case such a subgraph does not exist, return `-1`.

A  **subgraph**  is a graph whose vertices and edges are subsets of the original graph. The  **weight**  of a subgraph is the sum of weights of its constituent edges.

 

 **Example 1:** 

```
Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
Output: 9
Explanation:
The above figure represents the input graph.
The blue edges represent one of the subgraphs that yield the optimal answer.
Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.

```

 **Example 2:** 

```
Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
Output: -1
Explanation:
The above figure represents the input graph.
It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.

```

 

 **Constraints:** 

- 3 <= n <= 105
- 0 <= edges.length <= 105
- edges[i].length == 3
- 0 <= fromi, toi, src1, src2, dest <= n - 1
- fromi != toi
- src1, src2, and dest are pairwise distinct.
- 1 <= weight[i] <= 105

## Solution

**Language:** Java  
**Runtime:** 143 ms (beats 70.73%)  
**Memory:** 209 MB (beats 83.97%)  
**Submitted:** 2026-08-14T17:22:33.008Z  

```java
import java.util.*;

class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        List<long[]>[] graph = new ArrayList[n];
        List<long[]>[] reverseGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new long[]{v, w});
            reverseGraph[v].add(new long[]{u, w});
        }

        // Shortest distances from src1
        long[] dist1 = dijkstra(graph, src1);

        // Shortest distances from src2
        long[] dist2 = dijkstra(graph, src2);

        // Shortest distances from every node to dest
        // = shortest distances from dest in reversed graph
        long[] distDest = dijkstra(reverseGraph, dest);

        long answer = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (dist1[i] == Long.MAX_VALUE ||
                dist2[i] == Long.MAX_VALUE ||
                distDest[i] == Long.MAX_VALUE) {
                continue;
            }

            long total = dist1[i] + dist2[i] + distDest[i];

            answer = Math.min(answer, total);
        }

        return answer == Long.MAX_VALUE ? -1 : answer;
    }

    private long[] dijkstra(List<long[]>[] graph, int source) {

        int n = graph.length;

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[source] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[1], b[1])
        );

        pq.offer(new long[]{source, 0});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            int node = (int) current[0];
            long distance = current[1];

            // Ignore outdated entry
            if (distance != dist[node]) {
                continue;
            }

            for (long[] edge : graph[node]) {

                int next = (int) edge[0];
                long weight = edge[1];

                long newDistance = distance + weight;

                if (newDistance < dist[next]) {
                    dist[next] = newDistance;
                    pq.offer(new long[]{next, newDistance});
                }
            }
        }

        return dist;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-weighted-subgraph-with-the-required-paths/)