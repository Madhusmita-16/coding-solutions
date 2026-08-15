import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    private TrieNode root = new TrieNode();
    private char[][] board;
    private int rows;
    private int cols;
    private List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {

        this.board = board;
        rows = board.length;
        cols = board[0].length;

        // Build Trie
        for (String word : words) {
            insert(word);
        }

        // Start DFS from every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(i, j, root);
            }
        }

        return result;
    }

    private void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int index = ch - 'a';

            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }

            node = node.children[index];
        }

        node.word = word;
    }

    private void dfs(int row, int col, TrieNode node) {

        // Boundary check
        if (row < 0 || row >= rows ||
            col < 0 || col >= cols) {
            return;
        }

        char ch = board[row][col];

        // Already visited
        if (ch == '#') {
            return;
        }

        TrieNode next = node.children[ch - 'a'];

        // Current path doesn't exist in Trie
        if (next == null) {
            return;
        }

        // Complete word found
        if (next.word != null) {
            result.add(next.word);

            // Prevent duplicate result
            next.word = null;
        }

        // Mark current cell as visited
        board[row][col] = '#';

        // Explore four directions
        dfs(row - 1, col, next); // up
        dfs(row + 1, col, next); // down
        dfs(row, col - 1, next); // left
        dfs(row, col + 1, next); // right

        // Restore cell
        board[row][col] = ch;
    }
}