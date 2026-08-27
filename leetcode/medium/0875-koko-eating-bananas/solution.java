class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Maximum pile is the maximum possible speed
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        while (low < high) {

            int k = low + (high - low) / 2;

            long hours = 0;

            for (int pile : piles) {
                hours += (pile + (long) k - 1) / k;

                // No need to continue if already too many hours
                if (hours > h) {
                    break;
                }
            }

            if (hours <= h) {
                // k works, try a smaller speed
                high = k;
            } else {
                // k is too slow
                low = k + 1;
            }
        }

        return low;
    }
}