# Q1. Range Sum Query - Mutable

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums`, handle multiple queries of the following types:

- Update the value of an element in nums.
- Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.

Implement the `NumArray` class:

- NumArray(int[] nums) Initializes the object with the integer array nums.
- void update(int index, int val) Updates the value of nums[index] to be val.
- int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] +... + nums[right]).

 

 **Example 1:** 

```
Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8

```

 

 **Constraints:** 

- 1 <= nums.length <= 3 * 104
- -100 <= nums[i] <= 100
- 0 <= index < nums.length
- -100 <= val <= 100
- 0 <= left <= right < nums.length
- At most 3 * 104 calls will be made to update and sumRange.

## Solution

**Language:** Java  
**Runtime:** 74 ms (beats 79.16%)  
**Memory:** 134.3 MB (beats 43.73%)  
**Submitted:** 2026-08-14T17:36:53.939Z  

```java
class NumArray {
    private int[] tree;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums.clone();
        this.tree = new int[nums.length + 1];

        // Build Fenwick Tree
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int difference = val - nums[index];
        nums[index] = val;

        // Fenwick Tree uses 1-based indexing
        add(index + 1, difference);
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    // Add value to a position
    private void add(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    // Sum from index 1 to index
    private int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }

        return sum;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/range-sum-query-mutable/)