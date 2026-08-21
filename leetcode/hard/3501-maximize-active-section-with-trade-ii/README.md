# Maximize Active Section with Trade II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a binary string `s` of length `n`, where:

- '1' represents an active section.
- '0' represents an inactive section.

You can perform  **at most one trade**  to maximize the number of active sections in `s`. In a trade, you:

- Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
- Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.

Additionally, you are given a  **2D array**  `queries`, where `queries[i] = [li, ri]` represents a substring `s[li...ri]`.

For each query, determine the  **maximum**  possible number of active sections in `s` after making the optimal trade on the substring `s[li...ri]`.

Return an array `answer`, where `answer[i]` is the result for `queries[i]`.

 **Note** 

- For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
- The queries are independent of each other.

 

 **Example 1:** 

 **Input:**  s = "01", queries = [[0,1]]

 **Output:**  [1]

 **Explanation:** 

Because there is no block of `'1'`s surrounded by `'0'`s, no valid trade is possible. The maximum number of active sections is 1.

 **Example 2:** 

 **Input:**  s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]

 **Output:**  [4,3,1,1]

 **Explanation:** 

- Query [0, 3] → Substring "0100" → Augmented to "101001" Choose "0100", convert "0100" → "0000" → "1111". The final string without augmentation is "1111". The maximum number of active sections is 4.
- Query [0, 2] → Substring "010" → Augmented to "10101" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "1110". The maximum number of active sections is 3.
- Query [1, 3] → Substring "100" → Augmented to "11001" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
- Query [2, 3] → Substring "00" → Augmented to "1001" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

 **Example 3:** 

 **Input:**  s = "1000100", queries = [[1,5],[0,6],[0,4]]

 **Output:**  [6,7,2]

 **Explanation:** 

- Query [1, 5] → Substring "00010" → Augmented to "1000101" Choose "00010", convert "00010" → "00000" → "11111". The final string without augmentation is "1111110". The maximum number of active sections is 6.
- Query [0, 6] → Substring "1000100" → Augmented to "110001001" Choose "000100", convert "000100" → "000000" → "111111". The final string without augmentation is "1111111". The maximum number of active sections is 7.
- Query [0, 4] → Substring "10001" → Augmented to "1100011" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 **Example 4:** 

 **Input:**  s = "01010", queries = [[0,3],[1,4],[1,3]]

 **Output:**  [4,4,2]

 **Explanation:** 

- Query [0, 3] → Substring "0101" → Augmented to "101011" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "11110". The maximum number of active sections is 4.
- Query [1, 4] → Substring "1010" → Augmented to "110101" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "01111". The maximum number of active sections is 4.
- Query [1, 3] → Substring "101" → Augmented to "11011" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 

 **Constraints:** 

- 1 <= n == s.length <= 105
- 1 <= queries.length <= 105
- s[i] is either '0' or '1'.
- queries[i] = [li, ri]
- 0 <= li <= ri < n

## Solution

**Language:** Java  
**Runtime:** 244 ms (beats 36.47%)  
**Memory:** 226.9 MB (beats 83.43%)  
**Submitted:** 2026-08-21T06:50:50.474Z  

```java
import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) == '1' ? 1 : 0);
        }

        int totalOnes = prefix[n];

        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        List<Integer> leftZeroStart = new ArrayList<>();
        List<Integer> rightZeroEnd = new ArrayList<>();

        int i = 0;

        while (i < n) {
            if (s.charAt(i) == '0') {
                i++;
                continue;
            }

            int st = i;

            while (i < n && s.charAt(i) == '1') {
                i++;
            }

            int en = i - 1;

            // This 1-block must be surrounded by 0s.
            if (st > 0 && en < n - 1 &&
                s.charAt(st - 1) == '0' &&
                s.charAt(en + 1) == '0') {

                int left = st - 1;
                while (left > 0 && s.charAt(left - 1) == '0') {
                    left--;
                }

                int right = en + 1;
                while (right + 1 < n && s.charAt(right + 1) == '0') {
                    right++;
                }

                starts.add(st);
                ends.add(en);
                leftZeroStart.add(left);
                rightZeroEnd.add(right);
            }
        }

        int m = starts.size();

        int size = 1;
        while (size < m) {
            size <<= 1;
        }

        int[] seg = new int[size * 2];
        Arrays.fill(seg, Integer.MIN_VALUE);

        for (i = 0; i < m; i++) {
            int gain =
                (starts.get(i) - leftZeroStart.get(i)) +
                (rightZeroEnd.get(i) - ends.get(i));

            seg[size + i] = gain;
        }

        for (i = size - 1; i > 0; i--) {
            seg[i] = Math.max(seg[i << 1], seg[i << 1 | 1]);
        }

        List<Integer> answer = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];

            // The answer counts 1s in the ENTIRE string.
            int base = totalOnes;

            /*
             * Find 1-blocks completely inside [l, r].
             */
            int first = upperBound(starts, l);
            int last = lowerBound(ends, r) - 1;

            int bestGain = 0;

            if (first <= last) {
                if (first == last) {
                    int st = starts.get(first);
                    int en = ends.get(first);

                    int left = leftZeroStart.get(first);
                    int right = rightZeroEnd.get(first);

                    int leftGain = st - Math.max(l, left);
                    int rightGain = Math.min(r, right) - en;

                    bestGain = leftGain + rightGain;
                } else {
                    // First candidate: left zero block can be clipped.
                    int st = starts.get(first);
                    int en = ends.get(first);
                    int left = leftZeroStart.get(first);
                    int right = rightZeroEnd.get(first);

                    int gain =
                        st - Math.max(l, left)
                        + right - en;

                    bestGain = Math.max(bestGain, gain);

                    // Last candidate: right zero block can be clipped.
                    st = starts.get(last);
                    en = ends.get(last);
                    left = leftZeroStart.get(last);
                    right = rightZeroEnd.get(last);

                    gain =
                        st - left
                        + Math.min(r, right) - en;

                    bestGain = Math.max(bestGain, gain);

                    // Completely internal candidates.
                    if (first + 1 <= last - 1) {
                        bestGain = Math.max(
                            bestGain,
                            rangeMax(
                                seg,
                                size,
                                first + 1,
                                last - 1
                            )
                        );
                    }
                }
            }

            /*
             * If there is no 1-block inside the query, no trade
             * is possible. The original total number of 1s remains.
             */
            answer.add(base + bestGain);
        }

        return answer;
    }

    private int lowerBound(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = (lo + hi) >>> 1;

            if (list.get(mid) >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int upperBound(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = (lo + hi) >>> 1;

            if (list.get(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int rangeMax(int[] seg, int size, int l, int r) {
        l += size;
        r += size;

        int result = Integer.MIN_VALUE;

        while (l <= r) {
            if ((l & 1) == 1) {
                result = Math.max(result, seg[l++]);
            }

            if ((r & 1) == 0) {
                result = Math.max(result, seg[r--]);
            }

            l >>= 1;
            r >>= 1;
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximize-active-section-with-trade-ii/)