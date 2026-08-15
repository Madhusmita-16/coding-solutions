# Average of Levels in Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given the `root` of a binary tree, return  *the average value of the nodes on each level in the form of an array*. Answers within `10-5` of the actual answer will be accepted.

 

 **Example 1:** 

```
Input: root = [3,9,20,null,null,15,7]
Output: [3.00000,14.50000,11.00000]
Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
Hence return [3, 14.5, 11].

```

 **Example 2:** 

```
Input: root = [3,9,20,15,7]
Output: [3.00000,14.50000,11.00000]

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [1, 104].
- -231 <= Node.val <= 231 - 1

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 97.32%)  
**Memory:** 48.3 MB (beats 90.72%)  
**Submitted:** 2026-08-15T10:12:24.118Z  

```java
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                sum += current.val;
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add((double) sum / size);
        }
        
        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/average-of-levels-in-binary-tree/)