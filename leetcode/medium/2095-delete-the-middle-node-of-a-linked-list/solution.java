class Solution {
    public ListNode deleteMiddle(ListNode head) {

        // If there is only one node, delete it
        if (head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // slow will reach the node before the middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node
        slow.next = slow.next.next;

        return head;
    }
}