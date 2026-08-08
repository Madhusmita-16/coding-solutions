# Maximize Spanning Tree Stability with Upgrades

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer `n`, representing `n` nodes numbered from 0 to `n - 1` and a list of `edges`, where `edges[i] = [ui, vi, si, musti]`:

- ui and vi indicates an undirected edge between nodes ui and vi.
- si is the strength of the edge.
- musti is an integer (0 or 1). If musti == 1, the edge must be included in the spanning tree. These edges cannot be upgraded.

You are also given an integer `k`, the  **maximum**  number of upgrades you can perform. Each upgrade  **doubles**  the strength of an edge, and each eligible edge (with `musti == 0`) can be upgraded  **at most**  once.

The  **stability**  of a spanning tree is defined as the  **minimum**  strength score among all edges included in it.

Return the  **maximum**  possible stability of any valid spanning tree. If it is impossible to connect all nodes, return `-1`.

 **Note** : A  **spanning tree**  of a graph with `n` nodes is a subset of the edges that connects all nodes together (i.e. the graph is  **connected**)  *without*  forming any cycles, and uses  **exactly**  `n - 1` edges.

 

 **Example 1:** 

 **Input:**  n = 3, edges = [[0,1,2,1],[1,2,3,0]], k = 1

 **Output:**  2

 **Explanation:** 

- Edge [0,1] with strength = 2 must be included in the spanning tree.
- Edge [1,2] is optional and can be upgraded from 3 to 6 using one upgrade.
- The resulting spanning tree includes these two edges with strengths 2 and 6.
- The minimum strength in the spanning tree is 2, which is the maximum possible stability.

 **Example 2:** 

 **Input:**  n = 3, edges = [[0,1,4,0],[1,2,3,0],[0,2,1,0]], k = 2

 **Output:**  6

 **Explanation:** 

- Since all edges are optional and up to k = 2 upgrades are allowed.
- Upgrade edges [0,1] from 4 to 8 and [1,2] from 3 to 6.
- The resulting spanning tree includes these two edges with strengths 8 and 6.
- The minimum strength in the tree is 6, which is the maximum possible stability.

 **Example 3:** 

 **Input:**  n = 3, edges = [[0,1,1,1],[1,2,1,1],[2,0,1,1]], k = 0

 **Output:**  -1

 **Explanation:** 

- All edges are mandatory and form a cycle, which violates the spanning tree property of acyclicity. Thus, the answer is -1.

 

 **Constraints:** 

- 2 <= n <= 105
- 1 <= edges.length <= 105
- edges[i] = [ui, vi, si, musti]
- 0 <= ui, vi < n
- ui != vi
- 1 <= si <= 105
- musti is either 0 or 1.
- 0 <= k <= n
- There are no duplicate edges.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.7 MB  
**Submitted:** 2026-08-08T12:56:22.285Z  

```java
class Solution {

    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a);
            int pb = find(b);

            if (pa == pb) {
                return false;
            }

            if (size[pa] < size[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }

            parent[pb] = pa;
            size[pa] += size[pb];
            components--;

            return true;
        }
    }

    private int n;
    private int[][] edges;
    private int k;

    private boolean canBuild(int limit) {
        DSU dsu = new DSU(n);

        /*
         * First take all edges whose original strength
         * is already >= limit.
         *
         * Mandatory edges are included automatically.
         */
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];

            if (strength >= limit) {
                dsu.union(u, v);
            }
        }

        int upgradesLeft = k;

        /*
         * Now consider optional edges that can reach
         * 'limit' after doubling.
         *
         * strength * 2 >= limit
         *
         * We can use at most k upgrades.
         */
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];
            int must = edge[3];

            // Mandatory edges cannot be upgraded.
            if (must == 1) {
                continue;
            }

            if (upgradesLeft > 0 && strength * 2 >= limit) {
                if (dsu.union(u, v)) {
                    upgradesLeft--;
                }
            }
        }

        return dsu.components == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {

        this.n = n;
        this.edges = edges;
        this.k = k;

        DSU dsu = new DSU(n);

        /*
         * Mandatory edges must all be present.
         *
         * If they form a cycle, no valid spanning tree exists.
         */
        int minMandatory = Integer.MAX_VALUE;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];
            int must = edge[3];

            if (must == 1) {
                minMandatory = Math.min(minMandatory, strength);

                if (!dsu.union(u, v)) {
                    return -1;
                }
            }
        }

        /*
         * Check whether the complete graph is connected at all.
         */
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        if (dsu.components != 1) {
            return -1;
        }

        /*
         * If there are mandatory edges, the answer cannot exceed
         * their minimum strength because mandatory edges cannot
         * be upgraded.
         *
         * If there are no mandatory edges, we can potentially
         * reach 2 * maxStrength.
         */
        int maxStrength = 0;

        for (int[] edge : edges) {
            maxStrength = Math.max(maxStrength, edge[2]);
        }

        int right;

        if (minMandatory == Integer.MAX_VALUE) {
            right = maxStrength * 2;
        } else {
            right = minMandatory;
        }

        int left = 1;

        /*
         * Binary search for the maximum feasible stability.
         */
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (canBuild(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximize-spanning-tree-stability-with-upgrades/)