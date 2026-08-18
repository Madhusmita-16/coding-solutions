# Decode the Slanted Ciphertext

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A string `originalText` is encoded using a  **slanted transposition cipher**  to a string `encodedText` with the help of a matrix having a  **fixed number of rows**  `rows`.

`originalText` is placed first in a top-left to bottom-right manner.

The blue cells are filled first, followed by the red cells, then the yellow cells, and so on, until we reach the end of `originalText`. The arrow indicates the order in which the cells are filled. All empty cells are filled with `' '`. The number of columns is chosen such that the rightmost column will  **not be empty**  after filling in `originalText`.

`encodedText` is then formed by appending all characters of the matrix in a row-wise fashion.

The characters in the blue cells are appended first to `encodedText`, then the red cells, and so on, and finally the yellow cells. The arrow indicates the order in which the cells are accessed.

For example, if `originalText = "cipher"` and `rows = 3`, then we encode it in the following manner:

The blue arrows depict how `originalText` is placed in the matrix, and the red arrows denote the order in which `encodedText` is formed. In the above example, `encodedText = "ch ie pr"`.

Given the encoded string `encodedText` and number of rows `rows`, return  *the original string*  `originalText`.

 **Note:**  `originalText`  **does not**  have any trailing spaces `' '`. The test cases are generated such that there is only one possible `originalText`.

 

 **Example 1:** 

```
Input: encodedText = "ch   ie   pr", rows = 3
Output: "cipher"
Explanation: This is the same example described in the problem description.

```

 **Example 2:** 

```
Input: encodedText = "iveo    eed   l te   olc", rows = 4
Output: "i love leetcode"
Explanation: The figure above denotes the matrix that was used to encode originalText. 
The blue arrows show how we can find originalText from encodedText.

```

 **Example 3:** 

```
Input: encodedText = "coding", rows = 1
Output: "coding"
Explanation: Since there is only 1 row, both originalText and encodedText are the same.

```

 

 **Constraints:** 

- 0 <= encodedText.length <= 106
- encodedText consists of lowercase English letters and ' ' only.
- encodedText is a valid encoding of some originalText that does not have trailing spaces.
- 1 <= rows <= 1000
- The testcases are generated such that there is only one possible originalText.

## Solution

**Language:** Java  
**Runtime:** 19 ms (beats 77.04%)  
**Memory:** 55.1 MB (beats 91.82%)  
**Submitted:** 2026-08-18T14:18:30.175Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/decode-the-slanted-ciphertext/)