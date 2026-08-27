# Find the Difference of Two Arrays

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given two  **0-indexed**  integer arrays `nums1` and `nums2`, return  *a list*  `answer`  *of size*  `2`  *where:* 

- answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
- answer[1] is a list of all distinct integers in nums2 which are not present in nums1.

 **Note**  that the integers in the lists may be returned in  **any**  order.

 

 **Example 1:** 

```
Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums1. Therefore, answer[1] = [4,6].
```

 **Example 2:** 

```
Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].

```

 

 **Constraints:** 

- 1 <= nums1.length, nums2.length <= 1000
- -1000 <= nums1[i], nums2[i] <= 1000

## Solution

**Language:** Java  
**Runtime:** 10 ms (beats 92.98%)  
**Memory:** 47 MB (beats 98.33%)  
**Submitted:** 2026-08-27T08:27:40.730Z  

```java
import java.util.*;

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        // Add elements to sets
        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Elements in nums1 but not nums2
        for (int num : set1) {
            if (!set2.contains(num)) {
                list1.add(num);
            }
        }

        // Elements in nums2 but not nums1
        for (int num : set2) {
            if (!set1.contains(num)) {
                list2.add(num);
            }
        }

        List<List<Integer>> answer = new ArrayList<>();

        answer.add(list1);
        answer.add(list2);

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-the-difference-of-two-arrays/)