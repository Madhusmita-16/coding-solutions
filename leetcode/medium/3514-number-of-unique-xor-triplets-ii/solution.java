class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[] present = new boolean[MAX];

        for (int x : nums) {
            present[x] = true;
        }

        // All possible XORs of two elements
        boolean[] pair = new boolean[MAX];

        for (int a = 0; a < MAX; a++) {
            if (!present[a]) continue;

            for (int b = 0; b < MAX; b++) {
                if (present[b]) {
                    pair[a ^ b] = true;
                }
            }
        }

        // All possible XORs of three elements
        boolean[] triplet = new boolean[MAX];

        for (int x = 0; x < MAX; x++) {
            if (!pair[x]) continue;

            for (int y = 0; y < MAX; y++) {
                if (present[y]) {
                    triplet[x ^ y] = true;
                }
            }
        }

        int count = 0;

        for (boolean value : triplet) {
            if (value) {
                count++;
            }
        }

        return count;
    }
}