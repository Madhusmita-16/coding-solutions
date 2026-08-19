# Cinema Seat Allocation

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A cinema has `n` rows of seats, numbered from 1 to `n`. Each row has 10 seats, numbered from 1 to 10.

You are given a 2D integer array `reservedSeats`, where `reservedSeats[i] = [rowi, seati]` means that seat `seati` in row `rowi` is already reserved.

A four-person group must be assigned to four seats in the  **same**  row. The group can be seated in one of the following seat blocks:

- seats 2, 3, 4, 5
- seats 4, 5, 6, 7
- seats 6, 7, 8, 9

A block can be used only if  **none**  of its seats are reserved. Each seat can be assigned to  **at most** one group.

Return an integer denoting the  **maximum**  number of four-person groups that can be assigned.

 

 **Example 1:** 

```
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.

```

 **Example 2:** 

```
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2

```

 **Example 3:** 

```
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4

```

 

 **Constraints:** 

- 1 <= n <= 109
- 1 <= reservedSeats.length <= min(10 * n, 104)
- reservedSeats[i] == [rowi, seati]
- 1 <= rowi <= n
- 1 <= seati <= 10
- All reservedSeats[i] are distinct.

## Solution

**Language:** Java  
**Runtime:** 28 ms (beats 41.58%)  
**Memory:** 52.7 MB (beats 48.16%)  
**Submitted:** 2026-08-19T15:12:17.266Z  

```java
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>())
               .add(seat[1]);
        }

        // Initially, every row can have 2 families
        int result = (n - map.size()) * 2;

        for (Set<Integer> seats : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            // Left block: 2,3,4,5
            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            // Middle block: 4,5,6,7
            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            // Right block: 6,7,8,9
            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                // Two families can fit
                result += 2;
            } else if (left || middle || right) {
                // Only one family can fit
                result += 1;
            }
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/cinema-seat-allocation/)