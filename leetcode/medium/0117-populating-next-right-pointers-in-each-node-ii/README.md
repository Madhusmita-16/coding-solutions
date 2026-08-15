# Populating Next Right Pointers in Each Node II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a binary tree

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
Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

```

 **Example 2:** 

```
Input: root = []
Output: []

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 6000].
- -100 <= Node.val <= 100

 

 **Follow-up:** 

- You may only use constant extra space.
- The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.3 MB (beats 37.07%)  
**Submitted:** 2026-08-15T15:04:53.977Z  

```java
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node current = root;

        while (current != null) {
            Node dummy = new Node(0);
            Node tail = dummy;

            // Traverse current level using next pointers
            while (current != null) {

                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }

                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }

                current = current.next;
            }

            // Move to the first node of the next level
            current = dummy.next;
        }

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/)