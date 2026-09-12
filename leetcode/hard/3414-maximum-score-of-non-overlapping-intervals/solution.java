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