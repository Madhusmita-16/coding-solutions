class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } 
            else if (ch == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;   // '*' acts as ')'
                high++;  // '*' acts as '('
            }

            // Minimum cannot be negative
            if (low < 0) {
                low = 0;
            }

            // Even the maximum possibility is invalid
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}