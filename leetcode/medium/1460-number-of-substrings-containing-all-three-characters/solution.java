class Solution {
    public int numberOfSubstrings(String s) {

        int lastA = -1;
        int lastB = -1;
        int lastC = -1;

        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == 'a') {
                lastA = i;
            } else if (ch == 'b') {
                lastB = i;
            } else {
                lastC = i;
            }

            int min = Math.min(lastA, Math.min(lastB, lastC));

            if (min != -1) {
                count += min + 1;
            }
        }

        return count;
    }
}