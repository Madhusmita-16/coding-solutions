# Longest Substring of One Repeating Character

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **0-indexed**  string `s`. You are also given a  **0-indexed**  string `queryCharacters` of length `k` and a  **0-indexed**  array of integer  **indices**  `queryIndices` of length `k`, both of which are used to describe `k` queries.

The `ith` query updates the character in `s` at index `queryIndices[i]` to the character `queryCharacters[i]`.

Return  *an array*  `lengths`  *of length* `k` *where*  `lengths[i]`  *is the  **length**  of the  **longest substring**  of* `s` *consisting of  **only one repeating**  character  **after**  the*  `ith`  *query** is performed.* 

 

 **Example 1:** 

```
Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
Output: [3,3,4]
Explanation: 
- 1st query updates s = "bbbacc". The longest substring consisting of one repeating character is "bbb" with length 3.
- 2nd query updates s = "bbbccc". 
  The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
- 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating character is "bbbb" with length 4.
Thus, we return [3,3,4].

```

 **Example 2:** 

```
Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
Output: [2,3]
Explanation:
- 1st query updates s = "abazz". The longest substring consisting of one repeating character is "zz" with length 2.
- 2nd query updates s = "aaazz". The longest substring consisting of one repeating character is "aaa" with length 3.
Thus, we return [2,3].

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of lowercase English letters.
- k == queryCharacters.length == queryIndices.length
- 1 <= k <= 105
- queryCharacters consists of lowercase English letters.
- 0 <= queryIndices[i] < s.length

## Solution

**Language:** Java  
**Runtime:** 105 ms (beats 60.87%)  
**Memory:** 103.2 MB (beats 89.85%)  
**Submitted:** 2026-08-14T10:38:44.157Z  

```java
class Solution {

    int[] pre;
    int[] suf;
    int[] maxLen;
    int[] length;
    char[] leftChar;
    char[] rightChar;
    char[] str;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        str = s.toCharArray();

        int size = 4 * n;

        pre = new int[size];
        suf = new int[size];
        maxLen = new int[size];
        length = new int[size];

        leftChar = new char[size];
        rightChar = new char[size];

        // Build segment tree
        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Change character
            str[index] = ch;

            // Update segment tree
            update(1, 0, n - 1, index);

            // Root contains the answer
            ans[i] = maxLen[1];
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int l, int r) {

        // Store segment length
        length[node] = r - l + 1;

        // Leaf node
        if (l == r) {

            pre[node] = 1;
            suf[node] = 1;
            maxLen[node] = 1;

            leftChar[node] = str[l];
            rightChar[node] = str[l];

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    // Merge two child nodes
    private void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        // Boundary characters
        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        // Start with children's values
        pre[node] = pre[left];
        suf[node] = suf[right];

        maxLen[node] = Math.max(maxLen[left], maxLen[right]);

        // If characters at the boundary are equal
        if (rightChar[left] == leftChar[right]) {

            // Entire left segment has same character
            if (pre[left] == length[left]) {
                pre[node] = length[left] + pre[right];
            }

            // Entire right segment has same character
            if (suf[right] == length[right]) {
                suf[node] = suf[left] + length[right];
            }

            // Sequence crossing the middle
            maxLen[node] = Math.max(
                maxLen[node],
                suf[left] + pre[right]
            );
        }
    }

    // Point update
    private void update(int node, int l, int r, int index) {

        // Leaf node
        if (l == r) {

            pre[node] = 1;
            suf[node] = 1;
            maxLen[node] = 1;

            leftChar[node] = str[l];
            rightChar[node] = str[l];

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        // Recalculate current node
        merge(node);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-substring-of-one-repeating-character/)