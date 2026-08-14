# Q2. Odd Even Linked List

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `head` of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return  *the reordered list*.

The  **first**  node is considered  **odd**, and the  **second**  node is  **even**, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in `O(1)` extra space complexity and `O(n)` time complexity.

 

 **Example 1:** 

```
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

```

 **Example 2:** 

```
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]

```

 

 **Constraints:** 

- The number of nodes in the linked list is in the range [0, 104].
- -106 <= Node.val <= 106

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.6 MB (beats 17.28%)  
**Submitted:** 2026-08-14T11:50:52.562Z  

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {

            // Connect odd nodes
            odd.next = even.next;
            odd = odd.next;

            // Connect even nodes
            even.next = odd.next;
            even = even.next;
        }

        // Attach even list after odd list
        odd.next = evenHead;

        return head;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/odd-even-linked-list/)