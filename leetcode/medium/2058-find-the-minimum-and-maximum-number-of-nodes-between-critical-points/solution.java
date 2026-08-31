class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int minDistance = Integer.MAX_VALUE;
        int first = -1;
        int previous = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {

            // Check if curr is a local maxima or local minima
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // Calculate distance from previous critical point
                if (previous != -1) {
                    minDistance = Math.min(minDistance, index - previous);
                }

                previous = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || first == previous) {
            return new int[]{-1, -1};
        }

        // Distance between first and last critical point
        int maxDistance = previous - first;

        return new int[]{minDistance, maxDistance};
    }
}