class Solution {
    public boolean increasingTriplet(int[] nums) {

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {

            // Smallest first element
            if (num <= first) {
                first = num;
            }

            // Smallest possible second element
            else if (num <= second) {
                second = num;
            }

            // num > first and num > second
            else {
                return true;
            }
        }

        return false;
    }
}