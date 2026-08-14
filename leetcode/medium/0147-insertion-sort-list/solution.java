class Solution {
    public ListNode insertionSortList(ListNode head) {

        // Dummy node to represent the start of sorted list
        ListNode dummy = new ListNode(0);

        ListNode current = head;

        while (current != null) {
            // Save the next node before changing current.next
            ListNode next = current.next;

            // Find the correct position in sorted list
            ListNode prev = dummy;

            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }

            // Insert current between prev and prev.next
            current.next = prev.next;
            prev.next = current;

            // Move to next unsorted node
            current = next;
        }

        return dummy.next;
    }
}