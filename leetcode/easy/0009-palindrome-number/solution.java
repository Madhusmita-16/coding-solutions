class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are never palindromes
        // Numbers ending in 0 are not palindromes unless the number is 0
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        // Reverse only half of the digits
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // Even number of digits
        // Odd number of digits: ignore the middle digit
        return x == reversedHalf || x == reversedHalf / 10;
    }
}