class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {

        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isWord = true;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode node) {

        // Entire word processed
        if (index == word.length()) {
            return node.isWord;
        }

        char ch = word.charAt(index);

        // Normal character
        if (ch != '.') {

            int childIndex = ch - 'a';

            if (node.children[childIndex] == null) {
                return false;
            }

            return search(word, index + 1,
                          node.children[childIndex]);
        }

        // '.' can represent any character
        for (int i = 0; i < 26; i++) {

            if (node.children[i] != null) {

                if (search(word, index + 1,
                           node.children[i])) {
                    return true;
                }
            }
        }

        return false;
    }
}