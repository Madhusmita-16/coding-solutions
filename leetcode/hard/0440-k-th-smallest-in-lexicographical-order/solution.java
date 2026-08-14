class Solution {
    public int findKthNumber(int n, int k) {

        int current = 1;
        k--; // We are already at the first number: 1

        while (k > 0) {

            long steps = countSteps(n, current, current + 1);

            if (steps <= k) {
                // Skip this entire prefix subtree
                current++;
                k -= steps;
            } else {
                // Go deeper into this prefix
                current *= 10;
                k--;
            }
        }

        return current;
    }

    private long countSteps(long n, long prefix1, long prefix2) {

        long steps = 0;

        while (prefix1 <= n) {
            steps += Math.min(n + 1, prefix2) - prefix1;

            prefix1 *= 10;
            prefix2 *= 10;
        }

        return steps;
    }
}