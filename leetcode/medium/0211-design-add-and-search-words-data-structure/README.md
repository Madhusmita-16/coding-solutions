# Design Add and Search Words Data Structure

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the `WordDictionary` class:

- WordDictionary() Initializes the object.
- void addWord(word) Adds word to the data structure, it can be matched later.
- bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.

 

 **Example:** 

```
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

```

 

 **Constraints:** 

- 1 <= word.length <= 25
- word in addWord consists of lowercase English letters.
- word in search consist of '.' or lowercase English letters.
- There will be at most 2 dots in word for search queries.
- At most 104 calls will be made to addWord and search.

## Solution

**Language:** Java  
**Runtime:** 191 ms (beats 87.24%)  
**Memory:** 274.2 MB (beats 58.26%)  
**Submitted:** 2026-08-15T08:43:28.263Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/design-add-and-search-words-data-structure/)