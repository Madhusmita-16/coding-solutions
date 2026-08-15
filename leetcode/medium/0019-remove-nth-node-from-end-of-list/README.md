# Remove Nth Node From End of List

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

 

 **Example 1:** 

```
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

```

 **Example 2:** 

```
Input: head = [1], n = 1
Output: []

```

 **Example 3:** 

```
Input: head = [1,2], n = 1
Output: [1]

```

 

 **Constraints:** 

- The number of nodes in the list is sz.
- 1 <= sz <= 30
- 0 <= Node.val <= 100
- 1 <= n <= sz

 

 **Follow up:**  Could you do this in one pass?

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.8 MB (beats 6.88%)  
**Submitted:** 2026-08-15T14:19:38.701Z  

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Dummy node handles the case where the head itself is removed
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // Move fast n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)