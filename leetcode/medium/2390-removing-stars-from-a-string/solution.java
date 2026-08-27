class Solution {
    public String removeStars(String s) {

        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (c == '*') {
                // Remove the closest non-star character to the left
                result.deleteCharAt(result.length() - 1);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}