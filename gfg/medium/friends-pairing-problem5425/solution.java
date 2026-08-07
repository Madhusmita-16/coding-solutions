class Solution {
    public int countFriendsPairings(int n) {
        if (n <= 2) {
            return n;
        }

        long prev2 = 1; // f(1)
        long prev1 = 2; // f(2)

        for (int i = 3; i <= n; i++) {
            long current = prev1 + (i - 1) * prev2;

            prev2 = prev1;
            prev1 = current;
        }

        return (int) prev1;
    }
}