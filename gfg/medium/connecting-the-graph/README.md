# Min Edge Movements to Connect a Graph

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a graph with  **n**  vertices (0 to n-1) and  **m**  edges. You can remove one edge from anywhere and add that edge between any two vertices in one operation.

Find the minimum number of operations required to connect the graph. If it is not possible to connect the graph, return -1.

 **Examples:**  

```
Input: n = 4, edges[][] = [[0, 1], [0, 2], [1, 2]]

Output: 1
Explanation: Remove edge between vertices 1 and 2 and add between vertices 1 and 3.

```

```
Input: n = 6, edges[][] = [[0,1], [0,2], [0,3], [1,2], [1,3]]

Output: 2
Explanation: Remove edge between (1,2) and (0,3), and add edge between (1,4) and (3,5)

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:59:26.496Z  

```java
class Solution {
    int[] parent, rank_;

    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (rank_[ra] < rank_[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank_[ra] == rank_[rb]) rank_[ra]++;
    }

    int minEdgesReq(int n, int[][] edges) {
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        int c = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) c++;
        }

        int m = edges.length;
        int treeEdgesNeeded = n - c;   // edges required for a spanning forest
        int redundant = m - treeEdgesNeeded; // extra/removable edges available
        int needed = c - 1;            // edges needed to link c components into 1

        return (redundant >= needed) ? needed : -1;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/connecting-the-graph/1)