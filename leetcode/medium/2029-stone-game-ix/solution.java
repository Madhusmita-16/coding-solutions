class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];

        for (int stone : stones) {
            cnt[stone % 3]++;
        }

        // Stones divisible by 3 can only be safely removed when
        // the current sum is NOT 0 modulo 3.
        //
        // The known characterization for this game:
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }

        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}