# Number of Provinces

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

There are `n` cities. Some of them are connected, while some are not. If city `a` is connected directly with city `b`, and city `b` is connected directly with city `c`, then city `a` is connected indirectly with city `c`.

A  **province**  is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an `n x n` matrix `isConnected` where `isConnected[i][j] = 1` if the `ith` city and the `jth` city are directly connected, and `isConnected[i][j] = 0` otherwise.

Return  *the total number of  **provinces***.

 

 **Example 1:** 

```
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

```

 **Example 2:** 

```
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

```

 

 **Constraints:** 

- 1 <= n <= 200
- n == isConnected.length
- n == isConnected[i].length
- isConnected[i][j] is 1 or 0.
- isConnected[i][i] == 1
- isConnected[i][j] == isConnected[j][i]

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.8 MB  
**Submitted:** 2026-08-27T08:46:38.484Z  

```java
class Solution {
    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {
                provinces++;

                dfs(i, isConnected, visited);
            }
        }

        return provinces;
    }

    private void dfs(int city, int[][] isConnected, boolean[] visited) {

        visited[city] = true;

        for (int next = 0; next < isConnected.length; next++) {

            if (isConnected[city][next] == 1 && !visited[next]) {
                dfs(next, isConnected, visited);
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-provinces/)