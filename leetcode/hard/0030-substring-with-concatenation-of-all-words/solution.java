class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (totalLen > s.length()) {
            return result;
        }

        HashMap<String, Integer> wordMap = new HashMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int count = 0;

            HashMap<String, Integer> currentMap = new HashMap<>();

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (!wordMap.containsKey(word)) {
                    currentMap.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                count++;

                while (currentMap.get(word) > wordMap.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);
                    currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }

                if (count == wordCount) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLen);
                    currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}