# Q3. Ways to Make a Fair Array

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`. You can choose  **exactly one**  index (**0-indexed**) and remove the element. Notice that the index of the elements may change after the removal.

For example, if `nums = [6,1,7,4,1]`:

- Choosing to remove index 1 results in nums = [6,7,4,1].
- Choosing to remove index 2 results in nums = [6,1,4,1].
- Choosing to remove index 4 results in nums = [6,1,7,4].

An array is  **fair**  if the sum of the odd-indexed values equals the sum of the even-indexed values.

Return the  ***number**  of indices that you could choose such that after the removal,  *`nums`* is  **fair**. *

 

 **Example 1:** 

```
Input: nums = [2,1,6,4]
Output: 1
Explanation:
Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
There is 1 index that you can remove to make nums fair.

```

 **Example 2:** 

```
Input: nums = [1,1,1]
Output: 3
Explanation: You can remove any index and the remaining array is fair.

```

 **Example 3:** 

```
Input: nums = [1,2,3]
Output: 0
Explanation: You cannot make a fair array after removing any index.

```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 91.77%)  
**Memory:** 76.2 MB (beats 35.06%)  
**Submitted:** 2026-08-14T12:00:03.224Z  

```java
class Solution {
    public int waysToMakeFair(int[] nums) {

        int totalEven = 0;
        int totalOdd = 0;

        // Calculate total even and odd index sums
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                totalEven += nums[i];
            } else {
                totalOdd += nums[i];
            }
        }

        int leftEven = 0;
        int leftOdd = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {

            // Remove nums[i] from the right side
            if (i % 2 == 0) {
                totalEven -= nums[i];
            } else {
                totalOdd -= nums[i];
            }

            /*
             * After removing nums[i]:
             *
             * Elements on the right shift one position,
             * so their even/odd positions are swapped.
             */

            int newEvenSum = leftEven + totalOdd;
            int newOddSum = leftOdd + totalEven;

            if (newEvenSum == newOddSum) {
                count++;
            }

            // Add current element to the left side
            if (i % 2 == 0) {
                leftEven += nums[i];
            } else {
                leftOdd += nums[i];
            }
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/ways-to-make-a-fair-array/)