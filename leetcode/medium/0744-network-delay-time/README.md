# Q1. Network Delay Time

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a network of `n` nodes, labeled from `1` to `n`. You are also given `times`, a list of travel times as directed edges `times[i] = (ui, vi, wi)`, where `ui` is the source node, `vi` is the target node, and `wi` is the time it takes for a signal to travel from source to target.

We will send a signal from a given node `k`. Return  *the  **minimum**  time it takes for all the*  `n`  *nodes to receive the signal*. If it is impossible for all the `n` nodes to receive the signal, return `-1`.

 

 **Example 1:** 

```
Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2

```

 **Example 2:** 

```
Input: times = [[1,2,1]], n = 2, k = 1
Output: 1

```

 **Example 3:** 

```
Input: times = [[1,2,1]], n = 2, k = 2
Output: -1

```

 

 **Constraints:** 

- 1 <= k <= n <= 100
- 1 <= times.length <= 6000
- times[i].length == 3
- 1 <= ui, vi <= n
- ui != vi
- 0 <= wi <= 100
- All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 79.67%)  
**Memory:** 49 MB (beats 57.50%)  
**Submitted:** 2026-08-14T17:21:01.493Z  

```java
import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new int[]{v, w});
        }

        // Dijkstra's algorithm
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int node = current[0];
            int time = current[1];

            // Ignore outdated entries
            if (time > dist[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int weight = edge[1];

                int newTime = time + weight;

                if (newTime < dist[next]) {
                    dist[next] = newTime;
                    pq.offer(new int[]{next, newTime});
                }
            }
        }

        // Find the maximum shortest distance
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/network-delay-time/)