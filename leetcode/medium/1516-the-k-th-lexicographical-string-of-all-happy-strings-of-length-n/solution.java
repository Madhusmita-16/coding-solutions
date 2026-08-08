class Solution {
    public String getHappyString(int n, int k) {

        // Number of happy strings of length n:
        // 3 * 2^(n-1)
        int total = 3 * (1 << (n - 1));

        if (k > total) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // Each starting character ('a', 'b', 'c')
        // has 2^(n-1) strings.
        int groupSize = 1 << (n - 1);

        // Find the first character.
        if (k <= groupSize) {
            result.append('a');
        } else if (k <= 2 * groupSize) {
            result.append('b');
            k -= groupSize;
        } else {
            result.append('c');
            k -= 2 * groupSize;
        }

        // Construct remaining characters.
        for (int i = 1; i < n; i++) {

            groupSize /= 2;

            char prev = result.charAt(i - 1);

            char first;
            char second;

            // Determine the two possible characters
            // in lexicographical order.
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
                result.append(first);
            } else {
                result.append(second);
                k -= groupSize;
            }
        }

        return result.toString();
    }
}