# Q2. Sort an Array

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array of integers `nums`, sort the array in ascending order and return it.

You must solve the problem  **without using any built-in**  functions in `O(nlog(n))` time complexity and with the smallest space complexity possible.

 

 **Example 1:** 

```
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).

```

 **Example 2:** 

```
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessarily unique.

```

 

 **Constraints:** 

- 1 <= nums.length <= 5 * 104
- -5  *104 <= nums[i] <= 5*  104

## Solution

**Language:** Java  
**Runtime:** 34 ms (beats 24.92%)  
**Memory:** 86.7 MB (beats 20.70%)  
**Submitted:** 2026-08-14T12:07:51.945Z  

```java
class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // Extract maximum one by one
        for (int i = n - 1; i > 0; i--) {
            // Move largest element to the end
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            // Restore heap
            heapify(nums, i, 0);
        }

        return nums;
    }

    private void heapify(int[] nums, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && nums[left] > nums[largest]) {
                largest = left;
            }

            if (right < size && nums[right] > nums[largest]) {
                largest = right;
            }

            if (largest == i) {
                break;
            }

            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;

            i = largest;
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/sort-an-array/)