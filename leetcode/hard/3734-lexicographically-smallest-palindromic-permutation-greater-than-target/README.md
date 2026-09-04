# Lexicographically Smallest Palindromic Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given two strings `s` and `target`, each of length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest string**  that is  **both**  a  **palindromic permutation**  of `s` and  **strictly**  greater than `target`. If no such permutation exists, return an empty string.

 

 **Example 1:** 

 **Input:**  s = "baba", target = "abba"

 **Output:**  "baab"

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- The lexicographically smallest permutation that is strictly greater than target is "baab".

 **Example 2:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 **Example 3:** 

 **Input:**  s = "abc", target = "abb"

 **Output:**  ""

 **Explanation:** 

`s` has no palindromic permutations. Therefore, the answer is `""`.

 **Example 4:** 

 **Input:**  s = "aac", target = "abb"

 **Output:**  "aca"

 **Explanation:** 

- The only palindromic permutation of s is "aca".
- "aca" is strictly greater than target. Therefore, the answer is "aca".

 

 **Constraints:** 

- 1 <= n == s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 82.55%)  
**Memory:** 46.6 MB (beats 82.52%)  
**Submitted:** 2026-09-04T11:02:59.193Z  

```java
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;


        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        char[] targetHalf = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            targetHalf[i] = target.charAt(i);
        }

        int[] remaining = halfCnt.clone();
        char[] half = new char[halfLen];

        boolean possibleEqual = true;

        for (int i = 0; i < halfLen; i++) {
            int x = targetHalf[i] - 'a';

            if (remaining[x] == 0) {
                possibleEqual = false;
                break;
            }

            half[i] = targetHalf[i];
            remaining[x]--;
        }

        if (possibleEqual) {
  
            String candidate = buildPalindrome(half, mid, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        for (int pos = halfLen - 1; pos >= 0; pos--) {

            int[] used = halfCnt.clone();

            boolean validPrefix = true;

            for (int i = 0; i < pos; i++) {
                int x = targetHalf[i] - 'a';

                if (used[x] == 0) {
                    validPrefix = false;
                    break;
                }

                used[x]--;
            }

            if (!validPrefix) {
                continue;
            }

            int current = targetHalf[pos] - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (used[c] == 0) {
                    continue;
                }

                char[] resultHalf = new char[halfLen];

                for (int i = 0; i < pos; i++) {
                    resultHalf[i] = targetHalf[i];
                }

                resultHalf[pos] = (char) ('a' + c);
                used[c]--;

                int idx = pos + 1;

                for (int ch = 0; ch < 26; ch++) {
                    while (used[ch] > 0) {
                        resultHalf[idx++] = (char) ('a' + ch);
                        used[ch]--;
                    }
                }

                String candidate = buildPalindrome(resultHalf, mid, n);

                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] half, int mid, int n) {
        StringBuilder sb = new StringBuilder(n);

        for (char c : half) {
            sb.append(c);
        }
        if ((n & 1) == 1) {
            sb.append((char) ('a' + mid));
        }
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/)