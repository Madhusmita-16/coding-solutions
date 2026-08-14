import java.util.*;

class Solution {
    public boolean isPossible(int[] target) {

        int n = target.length;

        // Max heap
        PriorityQueue<Long> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;

        for (int x : target) {
            maxHeap.offer((long) x);
            sum += x;
        }

        while (true) {

            long largest = maxHeap.poll();

            // All elements are 1
            if (largest == 1) {
                return true;
            }

            long rest = sum - largest;

            // Invalid cases
            if (rest <= 0 || largest <= rest) {
                return false;
            }

            // Previous value of the largest element
            long previous = largest % rest;

            // If previous becomes 0, it cannot come from a positive array
            if (previous == 0) {
                return false;
            }

            // Replace largest with its previous value
            maxHeap.offer(previous);

            sum = rest + previous;
        }
    }
}