# Word Ladder

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A  **transformation sequence**  from word `beginWord` to word `endWord` using a dictionary `wordList` is a sequence of words `beginWord -> s1 -> s2 ->... -> sk` such that:

- Every adjacent pair of words differs by a single letter.
- Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
- sk == endWord

Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return  *the  **number of words**  in the  **shortest transformation sequence**  from*  `beginWord`  *to*  `endWord` *, or* `0` *if no such sequence exists.* 

 

 **Example 1:** 

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

```

 **Example 2:** 

```
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

```

 

 **Constraints:** 

- 1 <= beginWord.length <= 10
- endWord.length == beginWord.length
- 1 <= wordList.length <= 5000
- wordList[i].length == beginWord.length
- beginWord, endWord, and wordList[i] consist of lowercase English letters.
- beginWord != endWord
- All the words in wordList are unique.

## Solution

**Language:** Java  
**Runtime:** 78 ms (beats 50.80%)  
**Memory:** 48 MB (beats 59.15%)  
**Submitted:** 2026-08-15T08:57:45.950Z  

```java
import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // endWord must be present in the dictionary
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();
                char[] chars = current.toCharArray();

                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[j] = c;
                        String next = new String(chars);

                        // Found the end word
                        if (next.equals(endWord)) {
                            return level + 1;
                        }

                        // Add only unused dictionary words
                        if (wordSet.contains(next)) {
                            queue.offer(next);
                            wordSet.remove(next);
                        }
                    }

                    chars[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/word-ladder/)