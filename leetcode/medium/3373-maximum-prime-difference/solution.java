class Solution {
    public int maximumPrimeDifference(int[] nums) {

        int firstPrime = -1;
        int lastPrime = -1;

        for (int i = 0; i < nums.length; i++) {

            if (isPrime(nums[i])) {

                if (firstPrime == -1) {
                    firstPrime = i;
                }

                lastPrime = i;
            }
        }

        return lastPrime - firstPrime;
    }

    private boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}