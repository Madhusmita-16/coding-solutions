# Minimum Increment or Double Operations to Convert

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array  **arr[]**. Initially, you have another array containing only 0s.
In one operation, you may either:

- Choose any one element and increase its value by 1, or
- Double the values of all elements in the array simultaneously.

Find the minimum number of operations required to transform the initial all-zero array into the given array arr[].

 **Examples:** 

```
Input: arr[] = [16, 16, 16]
Output: 7
Explanation: 
First, increase each element to make the array [1, 1, 1] (3 steps).
Then, multiply the whole array by 2 four times:
[1,1,1] -> [2,2,2] -> [4,4,4] -> [8,8,8] -> [16,16,16]
Total steps = 3 + 4 = 7.
```

```
Input: arr[] = [2, 3]
Output: 4
Explanation: 
Start from [0, 0].
Increase both elements to get [1, 1] (2 steps)
Multiply once: [2, 2] (1 step)
Increase second element once: [2, 3] (1 step)
Total steps = 2 + 1 + 1 = 4.
```

 **Constraints:** 
1 ≤ arr.size() ≤ 105
0 ≤ arr[i] ≤ 109

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T18:41:05.900Z  

```java
class Solution {
    public int countMinOperations(int arr[]) {
        long increments = 0;
        int maxBits = 0;

        for (int x : arr) {
            int bits = 0;
            int value = x;

            while (value > 0) {
                if ((value & 1) == 1) {
                    increments++;
                }

                bits++;
                value >>= 1;
            }

            maxBits = Math.max(maxBits, bits);
        }

        if (maxBits == 0) {
            return 0;
        }

        return (int) (increments + maxBits - 1);
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/minimum-steps-to-get-desired-array5519/1)