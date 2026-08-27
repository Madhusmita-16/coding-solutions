# Evaluate Division

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an array of variable pairs `equations` and an array of real numbers `values`, where `equations[i] = [Ai, Bi]` and `values[i]` represent the equation `Ai / Bi = values[i]`. Each `Ai` or `Bi` is a string that represents a single variable.

You are also given some `queries`, where `queries[j] = [Cj, Dj]` represents the `jth` query where you must find the answer for `Cj / Dj = ?`.

Return  *the answers to all queries*. If a single answer cannot be determined, return `-1.0`.

 **Note:**  The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 **Note:** The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

 

 **Example 1:** 

```
Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
```

 **Example 2:** 

```
Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]

```

 **Example 3:** 

```
Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]

```

 

 **Constraints:** 

- 1 <= equations.length <= 20
- equations[i].length == 2
- 1 <= Ai.length, Bi.length <= 5
- values.length == equations.length
- 0.0 < values[i] <= 20.0
- 1 <= queries.length <= 20
- queries[i].length == 2
- 1 <= Cj.length, Dj.length <= 5
- Ai, Bi, Cj, Dj consist of lower case English letters and digits.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 98.78%)  
**Memory:** 46.7 MB (beats 79.60%)  
**Submitted:** 2026-08-27T08:36:59.335Z  

```java
import java.util.*;

class Solution {

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries) {

        // Graph:
        // variable -> (neighbor, ratio)
        Map<String, List<Edge>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            // a / b = value
            graph.get(a).add(new Edge(b, value));

            // b / a = 1 / value
            graph.get(b).add(new Edge(a, 1.0 / value));
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            // Undefined variable
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                result[i] = -1.0;
                continue;
            }

            // Same variable
            if (start.equals(end)) {
                result[i] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();

            result[i] = dfs(
                    start,
                    end,
                    1.0,
                    graph,
                    visited
            );
        }

        return result;
    }

    private double dfs(
            String current,
            String target,
            double product,
            Map<String, List<Edge>> graph,
            Set<String> visited) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Edge edge : graph.get(current)) {

            if (visited.contains(edge.to)) {
                continue;
            }

            double result = dfs(
                    edge.to,
                    target,
                    product * edge.weight,
                    graph,
                    visited
            );

            if (result != -1.0) {
                return result;
            }
        }

        return -1.0;
    }

    static class Edge {
        String to;
        double weight;

        Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/evaluate-division/)