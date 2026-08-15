import java.util.*;

class Solution {

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums,
                           boolean[] used,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // A complete permutation is formed
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // Skip numbers already used
            if (used[i]) {
                continue;
            }

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, used, current, result);

            // Backtrack
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
