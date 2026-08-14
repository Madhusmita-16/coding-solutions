# Q2. Create Binary Tree From Descriptions

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a 2D integer array `descriptions` where `descriptions[i] = [parenti, childi, isLefti]` indicates that `parenti` is the  **parent**  of `childi` in a  **binary**  tree of  **unique**  values. Furthermore,

- If isLefti == 1, then childi is the left child of parenti.
- If isLefti == 0, then childi is the right child of parenti.

Construct the binary tree described by `descriptions` and return  *its  **root***.

The test cases will be generated such that the binary tree is  **valid**.

 

 **Example 1:** 

```
Input: descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
Output: [50,20,80,15,17,19]
Explanation: The root node is the node with value 50 since it has no parent.
The resulting binary tree is shown in the diagram.

```

 **Example 2:** 

```
Input: descriptions = [[1,2,1],[2,3,0],[3,4,1]]
Output: [1,2,null,null,3,4]
Explanation: The root node is the node with value 1 since it has no parent.
The resulting binary tree is shown in the diagram.

```

 

 **Constraints:** 

- 1 <= descriptions.length <= 104
- descriptions[i].length == 3
- 1 <= parenti, childi <= 105
- 0 <= isLefti <= 1
- The binary tree described by descriptions is valid.

## Solution

**Language:** Java  
**Runtime:** 79 ms (beats 24.73%)  
**Memory:** 87.1 MB (beats 23.40%)  
**Submitted:** 2026-08-14T17:27:29.227Z  

```java
import java.util.*;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            // Create nodes if they don't exist
            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            // Connect parent and child
            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }

            // Child cannot be the root
            children.add(child);
        }

        // Find the node that never appeared as a child
        for (int value : map.keySet()) {
            if (!children.contains(value)) {
                return map.get(value);
            }
        }

        return null;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/create-binary-tree-from-descriptions/)