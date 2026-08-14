import java.util.*;

class Solution {
    public String longestWord(String[] words) {

        // Sort lexicographically so that when two words
        // have the same length, the smaller one comes first.
        Arrays.sort(words);

        Set<String> built = new HashSet<>();
        String answer = "";

        for (String word : words) {

            // A word can be built only if its prefix
            // without the last character already exists.
            if (word.length() == 1 || built.contains(word.substring(0, word.length() - 1))) {

                built.add(word);

                if (word.length() > answer.length()) {
                    answer = word;
                }
            }
        }

        return answer;
    }
}