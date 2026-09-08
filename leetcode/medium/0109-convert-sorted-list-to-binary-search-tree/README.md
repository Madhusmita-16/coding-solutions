# Convert Sorted List to Binary Search Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `head` of a singly linked list where elements are sorted in  **ascending order**, convert  *it to a   height-balanced binary search tree*.

 

 **Example 1:** 

```
Input: head = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.

```

 **Example 2:** 

```
Input: head = []
Output: []

```

 

 **Constraints:** 

- The number of nodes in head is in the range [0, 2 * 104].
- -105 <= Node.val <= 105

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.7 MB (beats 73.01%)  
**Submitted:** 2026-09-08T05:31:24.551Z  

```java
class Solution {
    public TreeNode sortedListToBST(ListNode head) {

        // Empty list
        if (head == null) {
            return null;
        }

        // One node
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        // Find middle node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Break the list into two halves
        prev.next = null;

        // slow is the middle node
        TreeNode root = new TreeNode(slow.val);

        // Left half
        root.left = sortedListToBST(head);

        // Right half
        root.right = sortedListToBST(slow.next);

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/)