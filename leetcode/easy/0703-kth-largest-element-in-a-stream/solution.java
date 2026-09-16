import java.util.PriorityQueue;

class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> minHeap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);

        // Keep only the k largest elements
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}