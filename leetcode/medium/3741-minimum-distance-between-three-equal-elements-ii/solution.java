import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        List<Integer>[] positions = new ArrayList[n + 1];

        for (int i = 0; i < n; i++) {
            if (positions[nums[i]] == null) {
                positions[nums[i]] = new ArrayList<>();
            }
            positions[nums[i]].add(i);
        }

        for (int value = 1; value <= n; value++) {
            List<Integer> list = positions[value];

            if (list == null || list.size() < 3) {
                continue;
            }

            // For i < j < k:
            // distance = 2 * (k - i)
            //
            // Therefore, among any 3 consecutive occurrences,
            // the first and third give the minimum possible span.
            for (int i = 0; i + 2 < list.size(); i++) {
                int first = list.get(i);
                int third = list.get(i + 2);

                ans = Math.min(ans, 2 * (third - first));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}