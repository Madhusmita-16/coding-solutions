# Q2. Course Schedule

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you  **must**  take course `bi` first if you want to take course `ai`.

- For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return `true` if you can finish all courses. Otherwise, return `false`.

 

 **Example 1:** 

```
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

```

 **Example 2:** 

```
Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

```

 

 **Constraints:** 

- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- 0 <= ai, bi < numCourses
- All the pairs prerequisites[i] are unique.

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 56.56%)  
**Memory:** 47.2 MB (beats 48.39%)  
**Submitted:** 2026-08-27T09:05:57.864Z  

```java
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // Build graph
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];

            graph.get(preCourse).add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;

        while (!queue.isEmpty()) {

            int current = queue.poll();
            completed++;

            for (int next : graph.get(current)) {

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // If all courses are processed, no cycle exists
        return completed == numCourses;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/course-schedule/)