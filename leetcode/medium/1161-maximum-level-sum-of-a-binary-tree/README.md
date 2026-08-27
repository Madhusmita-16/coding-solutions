# Maximum Level Sum of a Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree, the level of its root is `1`, the level of its children is `2`, and so on.

Return the  **smallest**  level `x` such that the sum of all the values of nodes at level `x` is  **maximal**.

 

 **Example 1:** 

```
Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation: 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

```

 **Example 2:** 

```
Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [1, 104].
- -105 <= Node.val <= 105

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 61.80%)  
**Memory:** 49.2 MB (beats 57.75%)  
**Submitted:** 2026-08-27T08:34:34.991Z  

```java
import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        int answer = 1;
        int maxSum = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {

            int size = queue.size();
            int sum = 0;

            // Process all nodes of the current level
            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Use > so the smallest level is retained
            if (sum > maxSum) {
                maxSum = sum;
                answer = level;
            }

            level++;
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/)