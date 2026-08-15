import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < words.length) {
            int j = i;
            int wordsLength = 0;

            // Find all words that can fit in this line
            while (j < words.length) {
                int currentLength = wordsLength + words[j].length();

                // Add one mandatory space between words
                if (j > i) {
                    currentLength += (j - i);
                }

                if (currentLength > maxWidth) {
                    break;
                }

                wordsLength += words[j].length();
                j++;
            }

            int wordCount = j - i;
            int totalSpaces = maxWidth - wordsLength;

            StringBuilder line = new StringBuilder();

            // Last line or line with only one word
            if (j == words.length || wordCount == 1) {

                for (int k = i; k < j; k++) {
                    if (k > i) {
                        line.append(" ");
                    }
                    line.append(words[k]);
                }

                // Left justify: remaining spaces go at the end
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
                        int spaces = spacesPerGap;

                        // Extra spaces go to the left gaps
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