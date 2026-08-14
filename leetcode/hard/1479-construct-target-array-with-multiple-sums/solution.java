import java.util.*;

class Solution {
    public boolean isPossible(int[] target) {

        PriorityQueue<Long> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;

        for (int x : target) {
            pq.offer((long) x);
            sum += x;
        }

        while (true) {

            long largest = pq.poll();
            long rest = sum - largest;

            // We reached [1, 1, ..., 1]
            if (largest == 1) {
                return true;
            }

            // Cannot reduce a single element
            if (rest == 0) {
                return false;
            }

            // If the rest sums to 1, we can always reduce
            // the largest to 1.
            if (rest == 1) {
                return true;
            }

            // Largest must be greater than the sum of others
            if (largest <= rest) {
                return false;
            }

            // Reverse the operation
            long previous = largest % rest;

            if (previous == 0) {
                return false;
            }

            pq.offer(previous);

            sum = rest + previous;
        }
    }
}