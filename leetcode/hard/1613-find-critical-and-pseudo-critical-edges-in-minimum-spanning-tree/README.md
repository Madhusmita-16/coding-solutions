# Q1. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a weighted undirected connected graph with `n` vertices numbered from `0` to `n - 1`, and an array `edges` where `edges[i] = [ai, bi, weighti]` represents a bidirectional and weighted edge between nodes `ai` and `bi`. A minimum spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the minimum possible total edge weight.

Find  *all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST)*. An MST edge whose deletion from the graph would cause the MST weight to increase is called a  *critical edge*. On the other hand, a pseudo-critical edge is that which can appear in some MSTs but not all.

Note that you can return the indices of the edges in any order.

 

 **Example 1:** 

```
Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
Output: [[0,1],[2,3,4,5]]
Explanation: The figure above describes the graph.
The following figure shows all the possible MSTs:

Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.

```

 **Example 2:** 

```
Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
Output: [[],[0,1,2,3]]
Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.

```

 

 **Constraints:** 

- 2 <= n <= 100
- 1 <= edges.length <= min(200, n * (n - 1) / 2)
- edges[i].length == 3
- 0 <= ai < bi < n
- 1 <= weighti <= 1000
- All pairs (ai, bi) are distinct.

## Solution

**Language:** Java  
**Runtime:** 21 ms (beats 80.58%)  
**Memory:** 47.2 MB (beats 23.60%)  
**Submitted:** 2026-08-14T17:46:34.086Z  

```java
import java.util.*;

class Solution {

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        int m = edges.length;

        // Add original index to every edge:
        // [u, v, weight, originalIndex]
        int[][] e = new int[m][4];

        for (int i = 0; i < m; i++) {
            e[i][0] = edges[i][0];
            e[i][1] = edges[i][1];
            e[i][2] = edges[i][2];
            e[i][3] = i;
        }

        // Sort by weight
        Arrays.sort(e, (a, b) -> Integer.compare(a[2], b[2]));

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        // Weight of the original MST
        int baseWeight = kruskal(n, e, -1, -1);

        for (int i = 0; i < m; i++) {

            // If removing this edge makes MST impossible
            // or increases its weight, it is critical.
            int withoutEdge = kruskal(n, e, i, -1);

            if (withoutEdge > baseWeight) {
                critical.add(e[i][3]);
                continue;
            }

            // Force this edge into the MST.
            int withEdge = kruskal(n, e, -1, i);

            if (withEdge == baseWeight) {
                pseudo.add(e[i][3]);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudo);

        return result;
    }

    private int kruskal(int n, int[][] edges, int skip, int force) {

        DSU dsu = new DSU(n);
        int weight = 0;
        int count = 0;

        // Force the selected edge first
        if (force != -1) {
            int u = edges[force][0];
            int v = edges[force][1];
            int w = edges[force][2];

            dsu.union(u, v);
            weight += w;
            count++;
        }

        for (int i = 0; i < edges.length; i++) {

            if (i == skip || i == force) {
                continue;
            }

            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            if (dsu.union(u, v)) {
                weight += w;
                count++;

                if (count == n - 1) {
                    break;
                }
            }
        }

        // If we couldn't connect all vertices,
        // return a very large value.
        if (count != n - 1) {
            return Integer.MAX_VALUE;
        }

        return weight;
    }

    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        boolean union(int a, int b) {

            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }

            return true;
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/)