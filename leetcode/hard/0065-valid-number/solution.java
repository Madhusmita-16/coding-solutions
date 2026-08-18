class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;

        // Optional sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            i++;
        }

        // Digits before decimal point
        boolean digitBeforeDot = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            digitBeforeDot = true;
            i++;
        }

        // Optional decimal point
        if (i < n && s.charAt(i) == '.') {
            i++;
        }

        // Digits after decimal point
        boolean digitAfterDot = false;
        while (i < n && Character.isDigit(s.charAt(i))) {
            digitAfterDot = true;
            i++;
        }

        // Must have at least one digit
        if (!digitBeforeDot && !digitAfterDot) {
            return false;
        }

        // Optional exponent
        if (i < n && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;

            // Optional exponent sign
            if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                i++;
            }

            // Exponent must contain at least one digit
            boolean exponentDigit = false;

            while (i < n && Character.isDigit(s.charAt(i))) {
                exponentDigit = true;
                i++;
            }

            if (!exponentDigit) {
                return false;
            }
        }

        // Everything must have been consumed
        return i == n;
    }
}