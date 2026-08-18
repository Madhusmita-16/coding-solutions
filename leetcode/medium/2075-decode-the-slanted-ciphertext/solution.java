class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();

        if (rows == 1 || n == 0) {
            return encodedText;
        }

        int cols = n / rows;

        StringBuilder ans = new StringBuilder();

        // Start from each column in the first row
        for (int startCol = 0; startCol < cols; startCol++) {

            int r = 0;
            int c = startCol;

            while (r < rows && c < cols) {
                ans.append(encodedText.charAt(r * cols + c));

                r++;
                c++;
            }
        }

        // Remove trailing spaces
        int end = ans.length();

        while (end > 0 && ans.charAt(end - 1) == ' ') {
            end--;
        }

        return ans.substring(0, end);
    }
}