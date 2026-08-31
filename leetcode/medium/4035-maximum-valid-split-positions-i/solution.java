class Solution {
    public int maxValidSplits(int[] nums) {

        int n = nums.length;
        int answer = 0;

        // k = index to remove
        // k = n means remove nothing
        for (int k = 0; k <= n; k++) {

            int m = (k == n) ? n : n - 1;

            // Prefix GCD
            int[] prefix = new int[m];
            int g = 0;

            for (int i = 0, j = 0; i < n; i++) {
                if (i == k) continue;

                g = gcd(g, nums[i]);
                prefix[j++] = g;
            }

            // Count valid splits
            int count = 0;
            int suffixGcd = 0;

            // Build suffix GCD while moving from right to left
            int[] arr = new int[m];
            int idx = 0;

            for (int i = 0; i < n; i++) {
                if (i != k) {
                    arr[idx++] = nums[i];
                }
            }

            for (int i = m - 1; i > 0; i--) {

                suffixGcd = gcd(suffixGcd, arr[i]);

                if (prefix[i - 1] == suffixGcd) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}