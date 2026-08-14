import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int min = nums[0];
        int max = nums[0];

        // Find minimum and maximum
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // Mark all numbers present
        boolean[] present = new boolean[max + 1];

        for (int num : nums) {
            present[num] = true;
        }

        // Find missing numbers in [min, max]
        for (int i = min; i <= max; i++) {
            if (!present[i]) {
                result.add(i);
            }
        }

        return result;
    }
}