class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Find length and last node
        int length = 1;
        ListNode tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Avoid unnecessary rotations
        k = k % length;

        if (k == 0) {
            return head;
        }

        // Make the list circular
        tail.next = head;

        // Find the new tail
        int steps = length - k;
        ListNode newTail = tail;

        for (int i = 0; i < steps; i++) {
            newTail = newTail.next;
        }

        // New head comes after new tail
        ListNode newHead = newTail.next;

        // Break the circle
        newTail.next = null;

        return newHead;
    }
}