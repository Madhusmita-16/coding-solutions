# Reverse Vowels of a String

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s`, reverse only all the vowels in the string and return it.

The vowels are `'a'`, `'e'`, `'i'`, `'o'`, and `'u'`, and they can appear in both lower and upper cases, more than once.

 

 **Example 1:** 

 **Input:**  s = "IceCreAm"

 **Output:**  "AceCreIm"

 **Explanation:** 

The vowels in `s` are `['I', 'e', 'e', 'A']`. On reversing the vowels, s becomes `"AceCreIm"`.

 **Example 2:** 

 **Input:**  s = "leetcode"

 **Output:**  "leotcede"

 

 **Constraints:** 

- 1 <= s.length <= 3 * 105
- s consist of printable ASCII characters.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-08-27T07:56:06.654Z  

```java
class Solution {
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            // Move left until we find a vowel
            while (left < right && !isVowel(arr[left])) {
                left++;
            }

            // Move right until we find a vowel
            while (left < right && !isVowel(arr[right])) {
                right--;
            }

            // Swap vowels
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return new String(arr);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' ||
               c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' ||
               c == 'O' || c == 'U';
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/reverse-vowels-of-a-string/)