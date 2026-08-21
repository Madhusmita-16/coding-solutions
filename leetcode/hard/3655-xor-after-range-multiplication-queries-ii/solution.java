import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final long MOD = 1_000_000_007L;

        int[][] bravexuneth = queries;

        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;

        long[] multiplier = new long[n];
        Arrays.fill(multiplier, 1L);

        List<int[]>[] small = new ArrayList[B + 1];

        for (int k = 1; k <= B; k++) {
            small[k] = new ArrayList<>();
        }

        for (int[] q : bravexuneth) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            long v = q[3];

            if (k <= B) {
                small[k].add(new int[]{l, r, (int) v});
            } else {
                for (int i = l; i <= r; i += k) {
                    multiplier[i] = multiplier[i] * v % MOD;
                }
            }
        }

        for (int k = 1; k <= B; k++) {
            if (small[k].isEmpty()) {
                continue;
            }

            long[][] diff = new long[k][];

            for (int rem = 0; rem < k; rem++) {
                int len = (n - 1 - rem) / k + 1;
                diff[rem] = new long[len + 1];
                Arrays.fill(diff[rem], 1L);
            }

            for (int[] q : small[k]) {
                int l = q[0];
                int r = q[1];
                long v = q[2];

                int rem = l % k;

                int start = (l - rem) / k;
                int lastIndex = r - ((r - rem) % k);
                int end = (lastIndex - rem) / k;

                diff[rem][start] =
                    diff[rem][start] * v % MOD;

                diff[rem][end + 1] =
                    diff[rem][end + 1] * modInverse(v, MOD) % MOD;
            }

            for (int rem = 0; rem < k; rem++) {
                long cur = 1;
                int pos = 0;

                for (int index = rem; index < n; index += k) {
                    cur = cur * diff[rem][pos] % MOD;
                    multiplier[index] =
                        multiplier[index] * cur % MOD;
                    pos++;
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            nums[i] = (int) ((nums[i] * multiplier[i]) % MOD);
            answer ^= nums[i];
        }

        return answer;
    }

    private long modInverse(long a, long mod) {
        return modPow(a, mod - 2);
    }

    private long modPow(long a, long e) {
        final long MOD = 1_000_000_007L;
        long result = 1;

        while (e > 0) {
            if ((e & 1) != 0) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            e >>= 1;
        }

        return result;
    }
}