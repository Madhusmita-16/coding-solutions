# Q1. Remove Duplicates from Sorted List

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given the `head` of a sorted linked list,  *delete all duplicates such that each element appears only once*. Return  *the linked list  **sorted**  as well*.

 

 **Example 1:** 

```
Input: head = [1,1,2]
Output: [1,2]

```

 **Example 2:** 

```
Input: head = [1,1,2,3,3]
Output: [1,2,3]

```

 

 **Constraints:** 

- The number of nodes in the list is in the range [0, 300].
- -100 <= Node.val <= 100
- The list is guaranteed to be sorted in ascending order.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 45.2 MB (beats 85.42%)  
**Submitted:** 2026-08-14T11:49:25.744Z  

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode current = head;

        while (current != null && current.next != null) {

            if (current.val == current.next.val) {
                // Skip duplicate node
                current.next = current.next.next;
            } else {
                // Move to next node
                current = current.next;
            }
        }

        return head;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)