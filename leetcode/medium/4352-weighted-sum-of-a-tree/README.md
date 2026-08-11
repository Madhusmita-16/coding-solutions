# Q2. Weighted Sum of a Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `parent` of length `n` representing a rooted tree with nodes labeled from 0 to `n - 1`.

The tree is  **rooted**  at node 0, so `parent[0] = -1`. For each node `i` where `1 <= i <= n - 1`, `parent[i]` denotes the parent of node `i`.

You are also given an integer array `nums` of length `n`, where `nums[i]` denotes the value of node `i`.

The weight of a node `i` at depth `d` is `nums[i] * (h - d + 1)`, where `h` is the height of the tree.

Return the  **sum**  of the weights of all nodes in the tree.

The  **depth**  of a node is the number of nodes on the path from the root to that node, inclusive, with the root having depth 1.

The  **height**  of the tree is the maximum depth among all nodes in the tree.

 

 **Example 1:** 

​​​​​​​

 **Input:**  parent = [-1,0,0,0,2,2], nums = [5,2,3,1,4,6]

 **Output:**  37

 **Explanation:** 

The height of the tree is 3.

Node	`nums[i]`	Depth (`d`)	Weight
0	5	1	`5 * (3 - 1 + 1) = 15`
1	2	2	`2 * (3 - 2 + 1) = 4`
2	3	2	`3 * (3 - 2 + 1) = 6`
3	1	2	`1 * (3 - 2 + 1) = 2`
4	4	3	`4 * (3 - 3 + 1) = 4`
5	6	3	`6 * (3 - 3 + 1) = 6`

The sum of all node weights is `15 + 4 + 6 + 2 + 4 + 6 = 37`.

 **Example 2:** 

​​​​​​​​​​​​​​

 **Input:**  parent = [-1,0,1,2], nums = [1,2,3,4]

 **Output:**  20

 **Explanation:** 

The height of the tree is 4.

Node	`nums[i]`	Depth (`d`)	Weight
0	1	1	`1 * (4 - 1 + 1) = 4`
1	2	2	`2 * (4 - 2 + 1) = 6`
2	3	3	`3 * (4 - 3 + 1) = 6`
3	4	4	`4 * (4 - 4 + 1) = 4`

The sum of all node weights is `4 + 6 + 6 + 4 = 20`.

 

 **Constraints:** 

- 1 <= n <= 105
- n == parent.length == nums.length
- parent[0] == -1
- 0 <= parent[i] <= n - 1 for all i in [1, n - 1]
- 1 <= nums[i] <= 106
- The input is generated such that the array parent represents a valid tree rooted at node 0.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 96.41%)  
**Memory:** 104 MB (beats 98.38%)  
**Submitted:** 2026-08-11T15:42:39.318Z  

```java
import java.util.*;

class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;

        int[] depth = new int[n];
        depth[0] = 1;

        int[] path = new int[n];
        int height = 1;

        // Calculate depths even when parent index > child index
        for (int i = 1; i < n; i++) {

            int cur = i;
            int size = 0;

            // Go upward until we reach a node whose depth is known
            while (depth[cur] == 0) {
                path[size++] = cur;
                cur = parent[cur];
            }

            // Fill depths from the known parent downward
            while (size > 0) {
                int node = path[--size];
                depth[node] = depth[parent[node]] + 1;
            }
        }

        // Find tree height
        for (int i = 0; i < n; i++) {
            height = Math.max(height, depth[i]);
        }

        // Calculate weighted sum
        long answer = 0;

        for (int i = 0; i < n; i++) {
            answer += (long) nums[i] * (height - depth[i] + 1);
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/weighted-sum-of-a-tree/)