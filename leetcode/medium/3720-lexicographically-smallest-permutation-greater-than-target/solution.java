class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to keep the prefix equal to target.
        // If we can make a character larger, do it at the
        // rightmost possible position to get the smallest answer.
        for (int i = n - 1; i >= 0; i--) {

            // Restore characters from position i onward.
            int[] count = freq.clone();

            // Remove characters used by target[0 ... i-1].
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int idx = target.charAt(j) - 'a';

                if (count[idx] == 0) {
                    possible = false;
                    break;
                }

                count[idx]--;
            }

            if (!possible) {
                continue;
            }

            // At position i, choose the smallest character
            // strictly greater than target[i].
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {
                if (count[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix equal to target.
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    // Make this position strictly greater.
                    ans.append((char) ('a' + c));
                    count[c]--;

                    // Fill remaining positions with the
                    // smallest possible characters.
                    for (int x = 0; x < 26; x++) {
                        while (count[x] > 0) {
                            ans.append((char) ('a' + x));
                            count[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}