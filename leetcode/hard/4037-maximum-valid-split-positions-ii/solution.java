import java.util.*;

class Solution {

    int[][] st;
    int[] log;
    int n;

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int rangeGcd(int l, int r) {
        if (l > r) return 0;

        int len = r - l + 1;
        int j = log[len];

        return gcd(
            st[j][l],
            st[j][r - (1 << j) + 1]
        );
    }
    private int prefixGcd(int k, int len) {

        if (k == 0) {
            return rangeGcd(1, len);
        }

        if (len <= k) {
            return rangeGcd(0, len - 1);
        }

        return gcd(
            rangeGcd(0, k - 1),
            rangeGcd(k + 1, len)
        );
    }
    private int suffixGcd(int k, int len) {

        if (k == n - 1) {
            return rangeGcd(n - len - 1, n - 2);
        }

        int after = n - 1 - k;

        if (len <= after) {
            return rangeGcd(n - len, n - 1);
        }

        return gcd(
            rangeGcd(n - len - 1, k - 1),
            rangeGcd(k + 1, n - 1)
        );
    }
    private int firstPrefix(int k, int target) {
        int m = n - 1;
        int lo = 1;
        int hi = m;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (prefixGcd(k, mid) == target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private int firstSuffix(int k, int target) {

        int m = n - 1;
        int lo = 1;
        int hi = m;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (suffixGcd(k, mid) == target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
    private int scoreWithoutDeletion(int[] nums) {

        int answer = 0;
        int prefix = 0;

        for (int i = 0; i < n - 1; i++) {

            prefix = gcd(prefix, nums[i]);

            int suffix = rangeGcd(i + 1, n - 1);

            if (prefix == suffix) {
                answer++;
            }
        }
        return answer;
    }
    public int maxValidSplits(int[] nums) {

        n = nums.length;
        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
        int K = log[n] + 1;
        st = new int[K][n];

        for (int i = 0; i < n; i++) {
            st[0][i] = nums[i];
        }
       for (int j = 1; j < K; j++) {
            int half = 1 << (j - 1);
            int len = 1 << j;
        for (int i = 0; i + len <= n; i++) {
                st[j][i] = gcd(
                    st[j - 1][i],
                    st[j - 1][i + half]
                );
            }
        }


        int answer = scoreWithoutDeletion(nums);

  
        for (int k = 0; k < n; k++) {


            int leftGcd = rangeGcd(0, k - 1);
            int rightGcd = rangeGcd(k + 1, n - 1);

            int target = gcd(leftGcd, rightGcd);

            int m = n - 1;

   
            int L = firstPrefix(k, target);

      
            int R = firstSuffix(k, target);

         
            int score = m - R - L + 1;

            answer = Math.max(answer, Math.max(0, score));
        }

        return answer;
    }
}