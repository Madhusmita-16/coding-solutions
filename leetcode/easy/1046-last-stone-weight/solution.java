import java.util.*;

class Solution {
    public int lastStoneWeight(int[] stones) {

        // Max heap
        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        // Add all stones
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Smash the two heaviest stones
        while (maxHeap.size() > 1) {

            int y = maxHeap.poll(); // heaviest
            int x = maxHeap.poll(); // second heaviest

            if (x != y) {
                maxHeap.offer(y - x);
            }
        }

        // Return remaining stone
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
}