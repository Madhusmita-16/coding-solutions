import java.util.HashMap;

class Solution {
    public int minSubarray(int[] nums, int p) {

        long total = 0;

        for (int num : nums) {
            total = (total + num) % p;
        }

        int target = (int) total;

        // Already divisible
        if (target == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        // remainder 0 before starting
        map.put(0, -1);

        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {

            prefix = (prefix + nums[i]) % p;

            int current = (int) prefix;

            // Need previous remainder:
            // (current - previous + p) % p = target
            int needed = (current - target + p) % p;

            if (map.containsKey(needed)) {
                ans = Math.min(ans, i - map.get(needed));
            }

            // Store the latest index to get the shortest subarray
            map.put(current, i);
        }

        // Cannot remove the entire array
        return ans == nums.length ? -1 : ans;
    }
}