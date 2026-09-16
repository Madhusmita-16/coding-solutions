# Q2. Stream of Characters

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings `words`.

For example, if `words = ["abc", "xyz"]` and the stream added the four characters (one by one) `'a'`, `'x'`, `'y'`, and `'z'`, your algorithm should detect that the suffix `"xyz"` of the characters `"axyz"` matches `"xyz"` from `words`.

Implement the `StreamChecker` class:

- StreamChecker(String[] words) Initializes the object with the strings array words.
- boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.

 

 **Example 1:** 

```
Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist

```

 

 **Constraints:** 

- 1 <= words.length <= 2000
- 1 <= words[i].length <= 200
- words[i] consists of lowercase English letters.
- letter is a lowercase English letter.
- At most 4 * 104 calls will be made to query.

## Solution

**Language:** Java  
**Runtime:** 101 ms (beats 29.80%)  
**Memory:** 151.8 MB (beats 72.02%)  
**Submitted:** 2026-09-16T02:45:18.853Z  

```java
class StreamChecker {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    private final TrieNode root;
    private final StringBuilder stream;
    private int maxLen;

    public StreamChecker(String[] words) {
        root = new TrieNode();
        stream = new StringBuilder();
        maxLen = 0;

        // Build Trie using reversed words
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());

            TrieNode node = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                int index = word.charAt(i) - 'a';

                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }

                node = node.children[index];
            }

            node.isWord = true;
        }
    }

    public boolean query(char letter) {

        stream.append(letter);

        TrieNode node = root;

        // Check suffixes, starting from the newest character
        int start = Math.max(0, stream.length() - maxLen);

        for (int i = stream.length() - 1; i >= start; i--) {

            int index = stream.charAt(i) - 'a';

            if (node.children[index] == null) {
                return false;
            }

            node = node.children[index];

            if (node.isWord) {
                return true;
            }
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/stream-of-characters/)