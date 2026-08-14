# Q2. Min Cost to Connect All Points

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an array `points` representing integer coordinates of some points on a 2D-plane, where `points[i] = [xi, yi]`.

The cost of connecting two points `[xi, yi]` and `[xj, yj]` is the  **manhattan distance**  between them: `|xi - xj| + |yi - yj|`, where `|val|` denotes the absolute value of `val`.

Return  *the minimum cost to make all points connected.*  All points are connected if there is  **exactly one**  simple path between any two points.

 

 **Example 1:** 

```
Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
Output: 20
Explanation: 

We can connect the points as shown above to get the minimum cost of 20.
Notice that there is a unique path between every pair of points.

```

 **Example 2:** 

```
Input: points = [[3,12],[-2,5],[-4,1]]
Output: 18

```

 

 **Constraints:** 

- 1 <= points.length <= 1000
- -106 <= xi, yi <= 106
- All pairs (xi, yi) are distinct.

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 98.53%)  
**Memory:** 44.8 MB (beats 96.76%)  
**Submitted:** 2026-08-14T17:47:21.763Z  

```java
class Solution {
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        // minDist[i] = minimum cost to connect point i
        // to the already connected set.
        int[] minDist = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }

        // Start from point 0
        minDist[0] = 0;

        int totalCost = 0;

        for (int count = 0; count < n; count++) {

            // Find the unused point with minimum connection cost
            int current = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i] &&
                    (current == -1 || minDist[i] < minDist[current])) {
                    current = i;
                }
            }

            // Add this edge to MST
            used[current] = true;
            totalCost += minDist[current];

            // Update distances of remaining points
            for (int next = 0; next < n; next++) {

                if (!used[next]) {

                    int distance =
                        Math.abs(points[current][0] - points[next][0])
                        + Math.abs(points[current][1] - points[next][1]);

                    minDist[next] = Math.min(minDist[next], distance);
                }
            }
        }

        return totalCost;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/min-cost-to-connect-all-points/)