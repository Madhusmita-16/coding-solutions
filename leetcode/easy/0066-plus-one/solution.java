class Solution {
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // 9 becomes 0 and carry goes to the left
            digits[i] = 0;
        }

        // If all digits were 9, we need an extra digit
        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }
}