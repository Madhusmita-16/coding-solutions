import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            // Keep only the k largest elements
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Smallest among the k largest = kth largest
        return minHeap.peek();
    }
}