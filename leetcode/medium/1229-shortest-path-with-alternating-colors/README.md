# Q3. Shortest Path with Alternating Colors

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer `n`, the number of nodes in a directed graph where the nodes are labeled from `0` to `n - 1`. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays `redEdges` and `blueEdges` where:

- redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
- blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.

Return an array `answer` of length `n`, where each `answer[x]` is the length of the shortest path from node `0` to node `x` such that the edge colors alternate along the path, or `-1` if such a path does not exist.

 

 **Example 1:** 

```
Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]

```

 **Example 2:** 

```
Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]

```

 

 **Constraints:** 

- 1 <= n <= 100
- 0 <= redEdges.length, blueEdges.length <= 400
- redEdges[i].length == blueEdges[j].length == 2
- 0 <= ai, bi, uj, vj < n

## Solution

**Language:** Java  
**Runtime:** 4 ms (beats 98.36%)  
**Memory:** 46.6 MB (beats 56.12%)  
**Submitted:** 2026-08-14T17:18:45.024Z  

```java
import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(
            int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        // Build red graph
        for (int[] edge : redEdges) {
            red[edge[0]].add(edge[1]);
        }

        // Build blue graph
        for (int[] edge : blueEdges) {
            blue[edge[0]].add(edge[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0] = 0;

        // visited[node][0] = reached using red as last edge
        // visited[node][1] = reached using blue as last edge
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        // We can start with either color
        queue.offer(new int[]{0, 0}); // last edge red
        queue.offer(new int[]{0, 1}); // last edge blue

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int node = current[0];
                int lastColor = current[1];

                // If last was red, next must be blue
                if (lastColor == 0) {
                    for (int next : blue[node]) {
                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            queue.offer(new int[]{next, 1});

                            if (answer[next] == -1) {
                                answer[next] = distance + 1;
                            }
                        }
                    }
                }

                // If last was blue, next must be red
                else {
                    for (int next : red[node]) {
                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            queue.offer(new int[]{next, 0});

                            if (answer[next] == -1) {
                                answer[next] = distance + 1;
                            }
                        }
                    }
                }
            }

            distance++;
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/shortest-path-with-alternating-colors/)