# Populating Next Right Pointers in Each Node

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **perfect binary tree**  where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

```

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

 

 **Example 1:** 

```
Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

```

 **Example 2:** 

```
Input: root = []
Output: []

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 212 - 1].
- -1000 <= Node.val <= 1000

 

 **Follow-up:** 

- You may only use constant extra space.
- The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.7 MB (beats 17.69%)  
**Submitted:** 2026-09-08T05:35:48.308Z  

```java
class Solution {
    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        Node leftmost = root;

        // Process each level
        while (leftmost.left != null) {

            Node current = leftmost;

            while (current != null) {

                // Connect left child to right child
                current.left.next = current.right;

                // Connect right child to next parent's left child
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                // Move to next node in the same level
                current = current.next;
            }

            // Move to the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)