# Partition List

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `head` of a linked list and a value `x`, partition it such that all nodes  **less than**  `x` come before nodes  **greater than or equal**  to `x`.

You should  **preserve**  the original relative order of the nodes in each of the two partitions.

 

 **Example 1:** 

```
Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]

```

 **Example 2:** 

```
Input: head = [2,1], x = 2
Output: [1,2]

```

 

 **Constraints:** 

- The number of nodes in the list is in the range [0, 200].
- -100 <= Node.val <= 100
- -200 <= x <= 200

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.8 MB (beats 68.75%)  
**Submitted:** 2026-08-15T14:27:14.774Z  

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greater = greaterDummy;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }

            head = head.next;
        }

        // End the greater/equal list
        greater.next = null;

        // Connect both lists
        less.next = greaterDummy.next;

        return lessDummy.next;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/partition-list/)