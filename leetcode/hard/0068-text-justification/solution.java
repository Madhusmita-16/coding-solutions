import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < words.length) {
            int j = i;
            int lineLength = 0;

            // Find how many words can fit in this line
            while (j < words.length) {
                int requiredLength = lineLength + words[j].length();

                // Add one space between consecutive words
                if (j > i) {
                    requiredLength++;
                }

                if (requiredLength > maxWidth) {
                    break;
                }

                lineLength += words[j].length();
                j++;
            }

            int wordCount = j - i;
            int totalSpaces = maxWidth - lineLength;

            StringBuilder line = new StringBuilder();

            // Last line OR line contains only one word
            if (j == words.length || wordCount == 1) {

                for (int k = i; k < j; k++) {
                    if (k > i) {
                        line.append(" ");
                    }
                    line.append(words[k]);
                }

                // Add remaining spaces at the end
                while (line.length() < maxWidth) {
                    line.append(" ");
                }

            } else {
                // Fully justified line
                int gaps = wordCount - 1;

                int spacesPerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        // Left gaps get one extra space
                        int spaces = spacesPerGap;

                        if (k - i < extraSpaces) {
                            spaces++;
                        }

                        for (int s = 0; s < spaces; s++) {
                            line.append(" ");
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}