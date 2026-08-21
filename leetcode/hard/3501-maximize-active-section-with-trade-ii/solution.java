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