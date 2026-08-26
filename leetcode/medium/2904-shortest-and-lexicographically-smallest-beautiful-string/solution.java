class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String answer = "";

        for (int i = 0; i < n; i++) {
            int ones = 0;

            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') {
                    ones++;
                }

                if (ones == k) {
                    String current = s.substring(i, j + 1);

                    if (current.length() < minLen ||
                        (current.length() == minLen &&
                         (answer.isEmpty() || current.compareTo(answer) < 0))) {

                        minLen = current.length();
                        answer = current;
                    }

                    // Adding more characters can only make the
                    // substring longer, so stop for this i.
                    break;
                }
            }
        }

        return answer;
    }
}