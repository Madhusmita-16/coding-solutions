# Maximum Score of Non-overlapping Intervals

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a 2D integer array `intervals`, where `intervals[i] = [li, ri, weighti]`. Interval `i` starts at position `li` and ends at `ri`, and has a weight of `weighti`. You can choose  *up to*  4  **non-overlapping**  intervals. The  **score**  of the chosen intervals is defined as the total sum of their weights.

Return the lexicographically smallest array of at most 4 indices from `intervals` with  **maximum**  score, representing your choice of non-overlapping intervals.

Two intervals are said to be  **non-overlapping**  if they do not share any points. In particular, intervals sharing a left or right boundary are considered overlapping.

 

 **Example 1:** 

 **Input:**  intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]

 **Output:**  [2,3]

 **Explanation:** 

You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.

 **Example 2:** 

 **Input:**  intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]

 **Output:**  [1,3,5,6]

 **Explanation:** 

You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.

 

 **Constraints:** 

- 1 <= intevals.length <= 5 * 104
- intervals[i].length == 3
- intervals[i] = [li, ri, weighti]
- 1 <= li <= ri <= 109
- 1 <= weighti <= 109

## Solution

**Language:** Java  
**Runtime:** 203 ms (beats 9.26%)  
**Memory:** 241.3 MB (beats 14.81%)  
**Submitted:** 2026-09-12T00:53:40.370Z  

```java
class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        long[][] arr = new long[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[0], b[0]);
        });

        /*
         * prev[i] = last interval whose right endpoint
         * is strictly smaller than arr[i].left
         */
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            long left = arr[i][0];

            int lo = 0, hi = i - 1;
            int ans = -1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid][1] < left) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = ans;
        }

        /*
         * dp[k][i] = best result using at most k intervals
         * among intervals [0 ... i].
         *
         * We store the selected indices as int[].
         */
        long[][] dpWeight = new long[5][n + 1];
        List<Integer>[][] dpIndices = new ArrayList[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dpIndices[k][i] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            int idx = i - 1;

            for (int k = 1; k <= 4; k++) {

                // Option 1: don't take current interval
                long skipWeight = dpWeight[k][i - 1];
                List<Integer> skipList = dpIndices[k][i - 1];

                // Option 2: take current interval
                int p = prev[idx] + 1;

                long takeWeight = dpWeight[k - 1][p] + arr[idx][2];

                List<Integer> takeList =
                        new ArrayList<>(dpIndices[k - 1][p]);

                takeList.add((int) arr[idx][3]);

                Collections.sort(takeList);

                if (takeWeight > skipWeight) {
                    dpWeight[k][i] = takeWeight;
                    dpIndices[k][i] = takeList;
                } else if (takeWeight < skipWeight) {
                    dpWeight[k][i] = skipWeight;
                    dpIndices[k][i] = new ArrayList<>(skipList);
                } else {
                    // Same score -> lexicographically smaller indices
                    if (lexicographicallySmaller(takeList, skipList)) {
                        dpWeight[k][i] = takeWeight;
                        dpIndices[k][i] = takeList;
                    } else {
                        dpWeight[k][i] = skipWeight;
                        dpIndices[k][i] = new ArrayList<>(skipList);
                    }
                }
            }
        }

        return dpIndices[4][n]
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-score-of-non-overlapping-intervals/)