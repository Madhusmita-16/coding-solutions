# Min Edge Movements to Connect a Graph

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a graph with  **n**  vertices (0 to n-1) and  **m**  edges. You can remove one edge from anywhere and add that edge between any two vertices in one operation.

Find the  **minimum**  number of operations that will be required to connect the graph. If it is not possible to connect the graph, return  **-1**.

 **Examples:**  

```
Input: n = 4, edges[][] = [[0, 1], [0, 2], [1, 2]]

Output: 1
Explanation: Remove edge between vertices 1 and 2 and add between vertices 1 and 3.

```

```
Input: n = 6, edges[][] = [[0,1], [0,2], [0,3], [1,2], [1,3]]

Output: 2
Explanation: Remove edge between (1,2) and(0,3) and add edge between (1,4) and (3,5)

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T18:43:19.875Z  

```java
class Solution {

    int[] parent;
    int[] rank;

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
            // This edge is redundant
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }

    int minEdgesReq(int n, int[][] edges) {
        // Not enough edges to connect n vertices
        if (edges.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;

        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                components--;
            }
        }

        // Need one operation for each connection between components
        return components - 1;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/connecting-the-graph/1)