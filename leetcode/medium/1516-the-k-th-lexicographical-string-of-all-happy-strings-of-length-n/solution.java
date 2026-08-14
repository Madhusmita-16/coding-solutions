class Solution {
    public String getHappyString(int n, int k) {

        int total = 3 * (1 << (n - 1));

        // Fewer than k happy strings exist
        if (k > total) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        // Number of strings for each starting character
        int groupSize = 1 << (n - 1);

        // Choose first character
        if (k <= groupSize) {
            ans.append('a');
        } else if (k <= 2 * groupSize) {
            ans.append('b');
            k -= groupSize;
        } else {
            ans.append('c');
            k -= 2 * groupSize;
        }

        // Choose remaining characters
        for (int i = 1; i < n; i++) {

            groupSize /= 2;

            char prev = ans.charAt(i - 1);

            char first;
            char second;

            if (prev == 'a') {
                first = 'b';
                second = 'c';
            } else if (prev == 'b') {
                first = 'a';
                second = 'c';
            } else {
                first = 'a';
                second = 'b';
            }

            if (k <= groupSize) {
                ans.append(first);
            } else {
                ans.append(second);
                k -= groupSize;
            }
        }

        return ans.toString();
    }
}