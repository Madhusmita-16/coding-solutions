# Generate Random Point in a Circle

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the radius and the position of the center of a circle, implement the function `randPoint` which generates a uniform random point inside the circle.

Implement the `Solution` class:

- Solution(double radius, double x_center, double y_center) initializes the object with the radius of the circle radius and the position of the center (x_center, y_center).
- randPoint() returns a random point inside the circle. A point on the circumference of the circle is considered to be in the circle. The answer is returned as an array [x, y].

 

 **Example 1:** 

```
Input
["Solution", "randPoint", "randPoint", "randPoint"]
[[1.0, 0.0, 0.0], [], [], []]
Output
[null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]

Explanation
Solution solution = new Solution(1.0, 0.0, 0.0);
solution.randPoint(); // return [-0.02493, -0.38077]
solution.randPoint(); // return [0.82314, 0.38945]
solution.randPoint(); // return [0.36572, 0.17248]

```

 

 **Constraints:** 

- 0 < radius <= 108
- -107 <= x_center, y_center <= 107
- At most 3 * 104 calls will be made to randPoint.

## Solution

**Language:** Java  
**Runtime:** 209 ms (beats 72.97%)  
**Memory:** 57 MB (beats 43.92%)  
**Submitted:** 2026-08-15T06:18:25.685Z  

```java
class Solution {

    private double radius;
    private double xCenter;
    private double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {

        while (true) {

            double x = (Math.random() * 2 - 1) * radius;
            double y = (Math.random() * 2 - 1) * radius;

            // Check whether the point is inside the circle
            if (x * x + y * y <= radius * radius) {
                return new double[] {
                    xCenter + x,
                    yCenter + y
                };
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/generate-random-point-in-a-circle/)