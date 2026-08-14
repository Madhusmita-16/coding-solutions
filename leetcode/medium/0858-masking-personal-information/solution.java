class Solution {
    public String maskPII(String s) {

        // Email
        if (s.contains("@")) {

            s = s.toLowerCase();

            int at = s.indexOf('@');

            char first = s.charAt(0);
            char last = s.charAt(at - 1);

            return first + "*****" + last + s.substring(at);
        }

        // Phone number
        StringBuilder digits = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }

        int n = digits.length();

        // Last 4 digits
        String lastFour = digits.substring(n - 4);

        StringBuilder result = new StringBuilder();

        // Country code
        int countryCodeLength = n - 10;

        if (countryCodeLength > 0) {
            result.append("+");

            for (int i = 0; i < countryCodeLength; i++) {
                result.append("*");
            }

            result.append("-");
        }

        result.append("***-***-");
        result.append(lastFour);

        return result.toString();
    }
}