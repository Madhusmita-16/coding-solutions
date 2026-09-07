class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        // last[c] = number of distinct subsequences
        // that were created ending with character c
        long[] last = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            // Every existing subsequence can append ch,
            // and ch itself forms a new subsequence.
            long newSubseq = (total + 1) % MOD;

            // Remove the old contribution ending with ch
            // to avoid duplicate subsequences.
            total = (total + newSubseq - last[c] + MOD) % MOD;

            // Store the new contribution for this character
            last[c] = newSubseq;
        }

        return (int) total;
    }
}