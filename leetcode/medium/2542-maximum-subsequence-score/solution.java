import java.util.*;

class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {

        int n = nums1.length;

        // Store {nums1[i], nums2[i]}
        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
        }

        // Sort by nums2 in descending order
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[1], a[1]));

        // Min-heap for the selected nums1 values
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        long sum = 0;
        long maxScore = 0;

        for (int[] pair : pairs) {

            int num1 = pair[0];
            int num2 = pair[1];

            minHeap.offer(num1);
            sum += num1;

            // Keep only the largest k nums1 values
            if (minHeap.size() > k) {
                sum -= minHeap.poll();
            }

            // If we have exactly k elements
            if (minHeap.size() == k) {
                long score = sum * num2;
                maxScore = Math.max(maxScore, score);
            }
        }

        return maxScore;
    }
}