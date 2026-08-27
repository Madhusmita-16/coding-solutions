class Solution {
    public String gcdOfStrings(String str1, String str2) {

        // If they don't have the same repeating pattern,
        // no common divisor exists.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Length of the GCD string
        int len = gcd(str1.length(), str2.length());

        return str1.substring(0, len);
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