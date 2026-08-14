# Q4. Bus Routes

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array `routes` representing bus routes where `routes[i]` is a bus route that the `ith` bus repeats forever.

- For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 ->... forever.

You will start at the bus stop `source` (You are not on any bus initially), and you want to go to the bus stop `target`. You can travel between bus stops by buses only.

Return  *the least number of buses you must take to travel from* `source` *to* `target`. Return `-1` if it is not possible.

 

 **Example 1:** 

```
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

```

 **Example 2:** 

```
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

```

 

 

 **Constraints:** 

- 1 <= routes.length <= 500.
- 1 <= routes[i].length <= 105
- All the values of routes[i] are unique.
- sum(routes[i].length) <= 105
- 0 <= routes[i][j] < 106
- 0 <= source, target < 106

## Solution

**Language:** Java  
**Runtime:** 41 ms (beats 84.86%)  
**Memory:** 103.6 MB (beats 52.66%)  
**Submitted:** 2026-08-14T17:19:47.193Z  

```java
import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // stop -> buses that visit this stop
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();

        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses
                    .computeIfAbsent(stop, x -> new ArrayList<>())
                    .add(bus);
            }
        }

        // BFS over bus stops
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedBus = new boolean[routes.length];

        queue.offer(source);
        visitedStops.add(source);

        int busesTaken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            busesTaken++;

            for (int i = 0; i < size; i++) {
                int stop = queue.poll();

                List<Integer> buses = stopToBuses.get(stop);

                if (buses == null) {
                    continue;
                }

                for (int bus : buses) {
                    // Process each bus only once
                    if (visitedBus[bus]) {
                        continue;
                    }

                    visitedBus[bus] = true;

                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return busesTaken;
                        }

                        if (visitedStops.add(nextStop)) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/bus-routes/)