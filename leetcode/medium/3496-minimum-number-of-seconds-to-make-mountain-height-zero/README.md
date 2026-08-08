# Minimum Number of Seconds to Make Mountain Height Zero

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer `mountainHeight` denoting the height of a mountain.

You are also given an integer array `workerTimes` representing the work time of workers in  **seconds**.

Each worker may reduce the mountain's height by any  **non-negative integer**  amount. If worker `i` reduces the height by `x`, then:

- reducing the first unit of height takes workerTimes[i] seconds,
- reducing the second unit takes workerTimes[i] * 2 seconds,
-...
- reducing the x-th unit takes workerTimes[i] * x seconds.

The total time spent by worker `i` is the sum of the times required for all `x` units they reduce. As all workers operate simultaneously, the total time required is the  **maximum**  time spent by any worker.

Return an integer representing the  **minimum**  number of seconds required for the workers to make the height of the mountain 0.

 

 **Example 1:** 

 **Input:**  mountainHeight = 4, workerTimes = [2,1,1]

 **Output:**  3

 **Explanation:** 

One way the height of the mountain can be reduced to 0 is:

- Worker 0 reduces the height by 1, taking workerTimes[0] = 2 seconds.
- Worker 1 reduces the height by 2, taking workerTimes[1] + workerTimes[1] * 2 = 3 seconds.
- Worker 2 reduces the height by 1, taking workerTimes[2] = 1 second.

Since they work simultaneously, the minimum time needed is `max(2, 3, 1) = 3` seconds.

 **Example 2:** 

 **Input:**  mountainHeight = 10, workerTimes = [3,2,2,4]

 **Output:**  12

 **Explanation:** 

- Worker 0 reduces the height by 2, taking workerTimes[0] + workerTimes[0] * 2 = 9 seconds.
- Worker 1 reduces the height by 3, taking workerTimes[1] + workerTimes[1]  *2 + workerTimes[1]*  3 = 12 seconds.
- Worker 2 reduces the height by 3, taking workerTimes[2] + workerTimes[2]  *2 + workerTimes[2]*  3 = 12 seconds.
- Worker 3 reduces the height by 2, taking workerTimes[3] + workerTimes[3] * 2 = 12 seconds.

The number of seconds needed is `max(9, 12, 12, 12) = 12` seconds.

 **Example 3:** 

 **Input:**  mountainHeight = 5, workerTimes = [1]

 **Output:**  15

 **Explanation:** 

There is only one worker in this example, so the answer is `workerTimes[0] + workerTimes[0]  *2 + workerTimes[0]*  3 + workerTimes[0]  *4 + workerTimes[0]*  5 = 15`.

 

 **Constraints:** 

- 1 <= mountainHeight <= 105
- 1 <= workerTimes.length <= 104
- 1 <= workerTimes[i] <= 106

## Solution

**Language:** Java  
**Runtime:** 18 ms (beats 50.12%)  
**Memory:** 47.2 MB (beats 96.03%)  
**Submitted:** 2026-08-08T12:19:16.479Z  

```java
class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 0;

        // Upper bound: the fastest/sufficient worker doing
        // the entire mountain alone.
        long maxWorker = 0;

        for (int time : workerTimes) {
            maxWorker = Math.max(maxWorker, time);
        }

        long right = maxWorker
                * (long) mountainHeight
                * (mountainHeight + 1)
                / 2;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canFinish(mountainHeight, workerTimes, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFinish(
            int mountainHeight,
            int[] workerTimes,
            long seconds) {

        long reduced = 0;

        for (int worker : workerTimes) {

            /*
             * worker * k * (k + 1) / 2 <= seconds
             *
             * We need the largest possible k.
             *
             * Solve:
             *
             * k(k + 1) / 2 <= seconds / worker
             */

            long work = seconds / worker;

            /*
             * k = floor((-1 + sqrt(1 + 8 * work)) / 2)
             */
            long k = (long) ((Math.sqrt(1.0 + 8.0 * work) - 1) / 2);

            /*
             * Correct possible floating-point rounding.
             */
            while ((k + 1) * (k + 2) / 2 <= work) {
                k++;
            }

            while (k * (k + 1) / 2 > work) {
                k--;
            }

            reduced += k;

            if (reduced >= mountainHeight) {
                return true;
            }
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero/)