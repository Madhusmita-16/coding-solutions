# Minimum Genetic Mutation

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A gene string can be represented by an 8-character long string, with choices from `'A'`, `'C'`, `'G'`, and `'T'`.

Suppose we need to investigate a mutation from a gene string `startGene` to a gene string `endGene` where one mutation is defined as one single character changed in the gene string.

- For example, "AACCGGTT" --> "AACCGGTA" is one mutation.

There is also a gene bank `bank` that records all the valid gene mutations. A gene must be in `bank` to make it a valid gene string.

Given the two gene strings `startGene` and `endGene` and the gene bank `bank`, return  *the minimum number of mutations needed to mutate from* `startGene` *to* `endGene`. If there is no such a mutation, return `-1`.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

 

 **Example 1:** 

```
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

```

 **Example 2:** 

```
Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2

```

 

 **Constraints:** 

- 0 <= bank.length <= 10
- startGene.length == endGene.length == bank[i].length == 8
- startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 91.97%)  
**Memory:** 43 MB (beats 14.14%)  
**Submitted:** 2026-08-15T08:58:32.850Z  

```java
import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {

        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // End gene must be present in the bank
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        Set<String> visited = new HashSet<>();
        visited.add(startGene);

        char[] genes = {'A', 'C', 'G', 'T'};

        int mutations = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String current = queue.poll();

                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                // Change one character at a time
                for (int j = 0; j < 8; j++) {

                    char original = chars[j];

                    for (char gene : genes) {

                        if (gene == original) {
                            continue;
                        }

                        chars[j] = gene;
                        String next = new String(chars);

                        if (bankSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    // Restore original character
                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-genetic-mutation/)