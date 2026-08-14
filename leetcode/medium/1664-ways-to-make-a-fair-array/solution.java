class Solution {
    public int waysToMakeFair(int[] nums) {

        int totalEven = 0;
        int totalOdd = 0;

        // Calculate total even and odd index sums
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                totalEven += nums[i];
            } else {
                totalOdd += nums[i];
            }
        }

        int leftEven = 0;
        int leftOdd = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            // Remove nums[i] from the right side
            if (i % 2 == 0) {
                totalEven -= nums[i];
            } else {
                totalOdd -= nums[i];
            }

            /*
             * After removing nums[i]:
             *
             * Elements on the right shift one position,
             * so their even/odd positions are swapped.
             */

            int newEvenSum = leftEven + totalOdd;
            int newOddSum = leftOdd + totalEven;

            if (newEvenSum == newOddSum) {
                count++;
            }

            // Add current element to the left side
            if (i % 2 == 0) {
                leftEven += nums[i];
            } else {
                leftOdd += nums[i];
            }
        }

        return count;
    }
}