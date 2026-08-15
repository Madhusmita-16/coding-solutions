# Word Search II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an `m x n` `board` of characters and a list of strings `words`, return  *all words on the board*.

Each word must be constructed from letters of sequentially adjacent cells, where  **adjacent cells**  are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

 **Example 1:** 

```
Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

```

 **Example 2:** 

```
Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

```

 

 **Constraints:** 

- m == board.length
- n == board[i].length
- 1 <= m, n <= 12
- board[i][j] is a lowercase English letter.
- 1 <= words.length <= 3 * 104
- 1 <= words[i].length <= 10
- words[i] consists of lowercase English letters.
- All the strings of words are unique.

## Solution

**Language:** Java  
**Runtime:** 156 ms (beats 58.68%)  
**Memory:** 47 MB (beats 70.21%)  
**Submitted:** 2026-08-15T08:40:06.253Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/word-search-ii/)