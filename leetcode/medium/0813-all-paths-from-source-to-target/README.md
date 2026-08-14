# Q2. All Paths From Source to Target

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a directed acyclic graph (**DAG**) of `n` nodes labeled from `0` to `n - 1`, find all possible paths from node `0` to node `n - 1` and return them in  **any order**.

The graph is given as follows: `graph[i]` is a list of all nodes you can visit from node `i` (i.e., there is a directed edge from node `i` to node `graph[i][j]`).

 

 **Example 1:** 

```
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

```

 **Example 2:** 

```
Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]

```

 

 **Constraints:** 

- n == graph.length
- 2 <= n <= 15
- 0 <= graph[i][j] < n
- graph[i][j] != i (i.e., there will be no self-loops).
- All the elements of graph[i] are unique.
- The input graph is guaranteed to be a DAG.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 90.54%)  
**Memory:** 47.7 MB (beats 88.34%)  
**Submitted:** 2026-08-14T17:03:43.407Z  

```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfs(0, graph, path, result);

        return result;
    }

    private void dfs(int node, int[][] graph,
                     List<Integer> path,
                     List<List<Integer>> result) {

        // Reached target
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }

        // Visit all adjacent nodes
        for (int next : graph[node]) {
            path.add(next);

            dfs(next, graph, path, result);

            // Backtrack
            path.remove(path.size() - 1);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/all-paths-from-source-to-target/)