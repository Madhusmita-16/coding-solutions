class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Only the first half needs to be arranged.
        int[] half = new int[26];
        int halfLen = s.length() / 2;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        // Check whether at least k permutations exist.
        if (countPermutations(half, k) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();
        int remaining = halfLen;

        while (remaining > 0) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) {
                    continue;
                }

                // Try putting character i at the current position.
                half[i]--;
                long ways = countPermutations(half, k);

                if (ways >= k) {
                    // This character is part of the answer.
                    left.append((char) ('a' + i));
                    remaining--;
                    break;
                } else {
                    // Skip all permutations beginning with this character.
                    k -= ways;
                    half[i]++;
                }
            }
        }

        // Construct the palindrome.
        StringBuilder right = new StringBuilder(left).reverse();

        char middle = 0;

        if (s.length() % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    middle = (char) ('a' + i);
                    break;
                }
            }
        }

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right.toString();
    }

    // Returns the number of distinct permutations,
    // capped at LIMIT.
    private long countPermutations(int[] count, int LIMIT) {
        long result = 1;
        int used = 0;

        for (int c : count) {
            if (c == 0) {
                continue;
            }

            long combinations = binomialCapped(
                    used + c,
                    c,
                    LIMIT
            );

            result *= combinations;

            if (result >= LIMIT) {
                return LIMIT;
            }

            used += c;
        }

        return result;
    }

    // Computes C(n, r), but stops once it reaches LIMIT.
    private long binomialCapped(int n, int r, int LIMIT) {
        r = Math.min(r, n - r);

        long result = 1;

        for (int i = 1; i <= r; i++) {
            // Use BigInteger-like protection through a division-first
            // calculation using gcd.
            long a = n - r + i;
            long b = i;

            long g = gcd(a, b);
            a /= g;
            b /= g;

            g = gcd(result, b);
            result /= g;
            b /= g;

            // If multiplication would exceed LIMIT,
            // we only need to know that it is >= LIMIT.
            if (b > 1 && result >= (LIMIT + b - 1) / b) {
                return LIMIT;
            }

            result *= a;
            result /= b;

            if (result >= LIMIT) {
                return LIMIT;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}