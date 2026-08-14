# Q3. Largest Rectangle in Histogram

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an array of integers `heights` representing the histogram's bar height where the width of each bar is `1`, return  *the area of the largest rectangle in the histogram*.

 

 **Example 1:** 

```
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

```

 **Example 2:** 

```
Input: heights = [2,4]
Output: 4

```

 

 **Constraints:** 

- 1 <= heights.length <= 105
- 0 <= heights[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 24 ms (beats 91.69%)  
**Memory:** 81.2 MB (beats 17.88%)  
**Submitted:** 2026-08-14T11:23:21.004Z  

```java
import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int maxArea = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {

            // Treat the end as a bar of height 0
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty()
                    && currentHeight < heights[stack.peek()]) {

                int height = heights[stack.pop()];

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            stack.push(i);
        }

        return maxArea;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/largest-rectangle-in-histogram/)