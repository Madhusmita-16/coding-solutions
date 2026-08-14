class Solution {
    public boolean detectCapitalUse(String word) {

        int upper = 0;

        for (char ch : word.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                upper++;
            }
        }

        // All uppercase
        if (upper == word.length()) {
            return true;
        }

        // All lowercase
        if (upper == 0) {
            return true;
        }

        // Only first character uppercase
        return upper == 1 && Character.isUpperCase(word.charAt(0));
    }
}
 
