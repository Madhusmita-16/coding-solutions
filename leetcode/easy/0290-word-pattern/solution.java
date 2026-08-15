class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        HashMap<Character, String> mapChar = new HashMap<>();
        HashMap<String, Character> mapWord = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (mapChar.containsKey(c)) {
                if (!mapChar.get(c).equals(word)) {
                    return false;
                }
            } else {
                mapChar.put(c, word);
            }

            if (mapWord.containsKey(word)) {
                if (mapWord.get(word) != c) {
                    return false;
                }
            } else {
                mapWord.put(word, c);
            }
        }

        return true;
    }
}