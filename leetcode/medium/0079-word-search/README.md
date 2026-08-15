# Word Search

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an `m x n` grid of characters `board` and a string `word`, return `true`  *if*  `word`  *exists in the grid*.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

 **Example 1:** 

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

```

 **Example 2:** 

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

```

 **Example 3:** 

```
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

```

 

 **Constraints:** 

- m == board.length
- n = board[i].length
- 1 <= m, n <= 6
- 1 <= word.length <= 15
- board and word consists of only lowercase and uppercase English letters.

 

 **Follow up:**  Could you use search pruning to make your solution faster with a larger `board`?

## Solution

**Language:** Java  
**Runtime:** 132 ms (beats 62.79%)  
**Memory:** 43.1 MB (beats 44.00%)  
**Submitted:** 2026-08-15T08:46:44.394Z  

```java
class Solution {

    private int rows;
    private int cols;

    public boolean exist(char[][] board, String word) {

        rows = board.length;
        cols = board[0].length;

        // Search from every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word,
                        int row, int col, int index) {

        // Entire word has been matched
        if (index == word.length()) {
            return true;
        }

        // Boundary check
        if (row < 0 || row >= rows ||
            col < 0 || col >= cols) {
            return false;
        }

        // Character doesn't match
        if (board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark as visited
        char original = board[row][col];
        board[row][col] = '#';

        // Explore four directions
        boolean found =
                dfs(board, word, row - 1, col, index + 1) || // up
                dfs(board, word, row + 1, col, index + 1) || // down
                dfs(board, word, row, col - 1, index + 1) || // left
                dfs(board, word, row, col + 1, index + 1);   // right

        // Backtrack
        board[row][col] = original;

        return found;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/word-search/)