class Solution {
    public String shortestPalindrome(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        String rev = new StringBuilder(s).reverse().toString();

        // Find the longest prefix of s that is also a suffix of rev
        String combined = s + "#" + rev;

        int[] lps = new int[combined.length()];

        for (int i = 1; i < combined.length(); i++) {

            int j = lps[i - 1];

            while (j > 0 && combined.charAt(i) != combined.charAt(j)) {
                j = lps[j - 1];
            }

            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        // Length of longest palindromic prefix
        int palindromeLength = lps[combined.length() - 1];

        // Remaining suffix
        String suffix = s.substring(palindromeLength);

        // Add reversed suffix to the front
        return new StringBuilder(suffix)
                .reverse()
                .append(s)
                .toString();
    }
}