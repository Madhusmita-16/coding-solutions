import java.util.Arrays;

class Solution {
    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);

        int operations = 0;
        int distinctLevels = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                distinctLevels++;
            }

            operations += distinctLevels;
        }

        return operations;
    }
}