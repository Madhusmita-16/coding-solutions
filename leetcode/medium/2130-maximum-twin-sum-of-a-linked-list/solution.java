class Solution {
    public int pairSum(ListNode head) {

        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        ListNode prev = null;
        ListNode current = slow;

        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Compare first half with reversed second half
        ListNode first = head;
        ListNode second = prev;

        int maxSum = 0;

        while (second != null) {
            maxSum = Math.max(maxSum, first.val + second.val);

            first = first.next;
            second = second.next;
        }

        return maxSum;
    }
}