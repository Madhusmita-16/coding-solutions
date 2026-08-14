# Q2. Largest Component Size by Common Factor

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array of unique positive integers `nums`. Consider the following graph:

- There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
- There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.

Return  *the size of the largest connected component in the graph*.

 

 **Example 1:** 

```
Input: nums = [4,6,15,35]
Output: 4

```

 **Example 2:** 

```
Input: nums = [20,50,9,63]
Output: 2

```

 **Example 3:** 

```
Input: nums = [2,3,6,7,4,12,21,39]
Output: 8

```

 

 **Constraints:** 

- 1 <= nums.length <= 2 * 104
- 1 <= nums[i] <= 105
- All the values of nums are unique.

## Solution

**Language:** Java  
**Runtime:** 61 ms (beats 95.54%)  
**Memory:** 49.3 MB (beats 74.33%)  
**Submitted:** 2026-08-14T16:45:58.003Z  

```java
class Solution {

    public int largestComponentSize(int[] nums) {
        int n = nums.length;

        DSU dsu = new DSU(n);

        // prime factor -> index of the first number containing it
        int[] factorIndex = new int[100001];
        java.util.Arrays.fill(factorIndex, -1);

        for (int i = 0; i < n; i++) {

            int num = nums[i];

            for (int factor = 2; factor * factor <= num; factor++) {

                if (num % factor == 0) {

                    if (factorIndex[factor] == -1) {
                        factorIndex[factor] = i;
                    } else {
                        dsu.union(i, factorIndex[factor]);
                    }

                    // Remove all occurrences of this factor
                    while (num % factor == 0) {
                        num /= factor;
                    }
                }
            }

            // Remaining number is a prime factor
            if (num > 1) {
                if (factorIndex[num] == -1) {
                    factorIndex[num] = i;
                } else {
                    dsu.union(i, factorIndex[num]);
                }
            }
        }

        int answer = 1;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dsu.size(i));
        }

        return answer;
    }

    // Disjoint Set Union
    static class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            // Union by size
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        int size(int x) {
            return size[find(x)];
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/largest-component-size-by-common-factor/)