# Max Points on a Line

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an array of `points` where `points[i] = [xi, yi]` represents a point on the  **X-Y**  plane, return  *the maximum number of points that lie on the same straight line*.

 

 **Example 1:** 

```
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

```

 **Example 2:** 

```
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4

```

 

 **Constraints:** 

- 1 <= points.length <= 300
- points[i].length == 2
- -104 <= xi, yi <= 104
- All the points are unique.

## Solution

**Language:** Java  
**Runtime:** 31 ms (beats 64.38%)  
**Memory:** 46.5 MB (beats 45.07%)  
**Submitted:** 2026-08-15T06:46:39.388Z  

```java
import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {

        int n = points.length;

        if (n <= 2) {
            return n;
        }

        int answer = 2;

        for (int i = 0; i < n; i++) {

            Map<String, Integer> map = new HashMap<>();
            int duplicate = 0;
            int localMax = 0;

            for (int j = i + 1; j < n; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                // Points are unique, so this is mainly defensive
                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }

                // Vertical line
                if (dx == 0) {
                    dy = 1;
                    dx = 0;
                }
                // Horizontal line
                else if (dy == 0) {
                    dy = 0;
                    dx = 1;
                }
                // Normalize slope
                else {
                    int gcd = gcd(Math.abs(dx), Math.abs(dy));

                    dx /= gcd;
                    dy /= gcd;

                    // Keep only one sign representation
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                }

                String slope = dy + "/" + dx;

                int count = map.getOrDefault(slope, 0) + 1;

                map.put(slope, count);

                localMax = Math.max(localMax, count);
            }

            answer = Math.max(answer, localMax + duplicate + 1);
        }

        return answer;
    }

    private int gcd(int a, int b) {

        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/max-points-on-a-line/)