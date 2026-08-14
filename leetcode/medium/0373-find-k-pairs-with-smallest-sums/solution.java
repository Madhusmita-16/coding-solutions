import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1])
        );

        // Add the first possible pair for each nums1 element
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], i, 0});
        }

        while (k > 0 && !minHeap.isEmpty()) {

            int[] current = minHeap.poll();

            int value1 = current[0];
            int value2 = current[1];
            int i = current[2];
            int j = current[3];

            result.add(Arrays.asList(value1, value2));
            k--;

            // Move to the next element in nums2
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{
                    nums1[i],
                    nums2[j + 1],
                    i,
                    j + 1
                });
            }
        }

        return result;
    }
}