class Solution {
    public String removeDuplicates(String s, int k) {

        char[] chars = new char[s.length()];
        int[] count = new int[s.length()];

        int top = -1;

        for (char c : s.toCharArray()) {

            // Add character to stack
            top++;
            chars[top] = c;

            // If same as previous character, increase count
            if (top > 0 && chars[top - 1] == c) {
                count[top] = count[top - 1] + 1;
            } else {
                count[top] = 1;
            }

            // Remove k consecutive characters
            if (count[top] == k) {
                top -= k;
            }
        }

        // Build result
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <= top; i++) {
            result.append(chars[i]);
        }

        return result.toString();
    }
}