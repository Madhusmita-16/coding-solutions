# Maximum Walls Destroyed by Robots

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

There is an endless straight line populated with some robots and walls. You are given integer arrays `robots`, `distance`, and `walls`:
- robots[i] is the position of the ith robot.
- distance[i] is the maximum distance the ith robot's bullet can travel.
- walls[j] is the position of the jth wall.

Every robot has  **one**  bullet that can either fire to the left or the right  **at most** `distance[i]` meters.

A bullet destroys every wall in its path that lies within its range. Robots are fixed obstacles: if a bullet hits another robot before reaching a wall, it  **immediately stops**  at that robot and cannot continue.

Return the  **maximum**  number of  **unique**  walls that can be destroyed by the robots.

Notes:

- A wall and a robot may share the same position; the wall can be destroyed by the robot at that position.
- Robots are not destroyed by bullets.

 

 **Example 1:** 

 **Input:**  robots = [4], distance = [3], walls = [1,10]

 **Output:**  1

 **Explanation:** 

- robots[0] = 4 fires left with distance[0] = 3, covering [1, 4] and destroys walls[0] = 1.
- Thus, the answer is 1.

 **Example 2:** 

 **Input:**  robots = [10,2], distance = [5,1], walls = [5,2,7]

 **Output:**  3

 **Explanation:** 

- robots[0] = 10 fires left with distance[0] = 5, covering [5, 10] and destroys walls[0] = 5 and walls[2] = 7.
- robots[1] = 2 fires left with distance[1] = 1, covering [1, 2] and destroys walls[1] = 2.
- Thus, the answer is 3.
 **Example 3:** 

 **Input:**  robots = [1,2], distance = [100,1], walls = [10]

 **Output:**  0

 **Explanation:** 

In this example, only `robots[0]` can reach the wall, but its shot to the  **right**  is blocked by `robots[1]`; thus the answer is 0.

 

 **Constraints:** 

- 1 <= robots.length == distance.length <= 105
- 1 <= walls.length <= 105
- 1 <= robots[i], walls[j] <= 109
- 1 <= distance[i] <= 105
- All values in robots are unique
- All values in walls are unique

## Solution

**Language:** Java  
**Runtime:** 294 ms (beats 19.05%)  
**Memory:** 140.5 MB (beats 94.56%)  
**Submitted:** 2026-08-08T13:10:51.941Z  

```java
import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // robot[i][0] = position
        // robot[i][1] = shooting distance
        int[][] robot = new int[n][2];

        for (int i = 0; i < n; i++) {
            robot[i][0] = robots[i];
            robot[i][1] = distance[i];
        }

        // Sort robots by position.
        Arrays.sort(robot, (a, b) -> Integer.compare(a[0], b[0]));

        // Sort walls.
        Arrays.sort(walls);

        int prevLeft = 0;
        int prevRight = 0;

        for (int i = 0; i < n; i++) {

            int pos = robot[i][0];
            int dist = robot[i][1];

            // --------------------------------------------------
            // Range when current robot shoots LEFT
            // --------------------------------------------------

            int leftUntil = pos - dist;

            if (i > 0) {
                // Previous robot blocks the bullet.
                leftUntil = Math.max(
                    leftUntil,
                    robot[i - 1][0] + 1
                );
            }

            int currentLeft = 0;

            // Current robot shoots left, previous shoots left.
            currentLeft = Math.max(
                currentLeft,
                prevLeft + count(walls, leftUntil, pos)
            );

            // --------------------------------------------------
            // Current robot shoots LEFT
            // Previous robot shoots RIGHT
            // --------------------------------------------------

            if (i > 0) {

                int prevPos = robot[i - 1][0];
                int prevDist = robot[i - 1][1];

                /*
                 * Previous robot shoots right.
                 *
                 * Its bullet can reach:
                 *
                 * [prevPos, prevPos + prevDist]
                 *
                 * The current robot's left bullet covers:
                 *
                 * [leftUntil, pos]
                 *
                 * We must not count walls already destroyed
                 * by the previous robot.
                 */

                int start = Math.max(
                    leftUntil,
                    Math.min(pos, prevPos + prevDist + 1)
                );

                currentLeft = Math.max(
                    currentLeft,
                    prevRight + count(walls, start, pos)
                );
            }

            // --------------------------------------------------
            // Range when current robot shoots RIGHT
            // --------------------------------------------------

            int rightUntil = pos + dist;

            if (i + 1 < n) {
                // Next robot blocks the bullet.
                rightUntil = Math.min(
                    rightUntil,
                    robot[i + 1][0] - 1
                );
            }

            int currentRight = 0;

            // Previous robot shoots LEFT.
            currentRight = Math.max(
                currentRight,
                prevLeft + count(walls, pos, rightUntil)
            );

            // Previous robot shoots RIGHT.
            currentRight = Math.max(
                currentRight,
                prevRight + count(walls, pos, rightUntil)
            );

            // Move to next robot.
            prevLeft = currentLeft;
            prevRight = currentRight;
        }

        return Math.max(prevLeft, prevRight);
    }

    /*
     * Count walls in the inclusive range [left, right].
     */
    private int count(int[] walls, int left, int right) {
        if (left > right) {
            return 0;
        }

        return upperBound(walls, right)
             - lowerBound(walls, left);
    }

    /*
     * First index where arr[index] >= target.
     */
    private int lowerBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    /*
     * First index where arr[index] > target.
     */
    private int upperBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-walls-destroyed-by-robots/)