# Delete Node in a BST

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return  *the  **root node reference**  (possibly updated) of the BST*.

Basically, the deletion can be divided into two stages:

- Search for a node to remove.
- If the node is found, delete the node.

 

 **Example 1:** 

```
Input: root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]
Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.

```

 **Example 2:** 

```
Input: root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]
Explanation: The tree does not contain a node with value = 0.

```

 **Example 3:** 

```
Input: root = [], key = 0
Output: []

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 104].
- -105 <= Node.val <= 105
- Each node has a unique value.
- root is a valid binary search tree.
- -105 <= key <= 105

 

 **Follow up:**  Could you solve it with time complexity `O(height of tree)`?

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 47.3 MB (beats 77.24%)  
**Submitted:** 2026-08-27T08:35:16.966Z  

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // Key not found
        if (root == null) {
            return null;
        }

        // Search in left subtree
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        // Search in right subtree
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        // Node found
        else {
            // Case 1: No left child
            if (root.left == null) {
                return root.right;
            }

            // Case 2: No right child
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            // Find the smallest value in the right subtree
            TreeNode successor = root.right;

            while (successor.left != null) {
                successor = successor.left;
            }

            // Replace current value with successor value
            root.val = successor.val;

            // Delete the successor node
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/delete-node-in-a-bst/)