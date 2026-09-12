class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count available copies of each digit
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Hundreds digit: 1-9 (no leading zero)
        for (int a = 1; a <= 9; a++) {
            if (freq[a] == 0) continue;

            // Tens digit: 0-9
            for (int b = 0; b <= 9; b++) {
                if (freq[b] == 0) continue;

                // Units digit must be even
                for (int c = 0; c <= 8; c += 2) {
                    if (freq[c] == 0) continue;

                    // Check whether we have enough copies
                    int[] used = new int[10];
                    used[a]++;
                    used[b]++;
                    used[c]++;

                    boolean possible = true;

                    for (int d = 0; d <= 9; d++) {
                        if (used[d] > freq[d]) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}