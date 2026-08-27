class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // prefix sum of all stones
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }

        // This represents the best score difference
        // starting from the current state.
        int best = sum;

        // Try every possible prefix that Alice/Bob can choose.
        // The first move must remove at least 2 stones,
        // so prefix indices go from 1 to n - 2.
        for (int i = n - 2; i >= 1; i--) {
            sum -= stones[i + 1];

            best = Math.max(best, sum - best);
        }

        return best;
    }
}