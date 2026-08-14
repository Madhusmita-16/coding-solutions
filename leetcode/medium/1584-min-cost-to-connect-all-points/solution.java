class Solution {
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;

        // minDist[i] = minimum cost to connect point i
        // to the already connected set.
        int[] minDist = new int[n];
        boolean[] used = new boolean[n];

        for (int i = 0; i < n; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }

        // Start from point 0
        minDist[0] = 0;

        int totalCost = 0;

        for (int count = 0; count < n; count++) {

            // Find the unused point with minimum connection cost
            int current = -1;

            for (int i = 0; i < n; i++) {
                if (!used[i] &&
                    (current == -1 || minDist[i] < minDist[current])) {
                    current = i;
                }
            }

            // Add this edge to MST
            used[current] = true;
            totalCost += minDist[current];

            // Update distances of remaining points
            for (int next = 0; next < n; next++) {

                if (!used[next]) {

                    int distance =
                        Math.abs(points[current][0] - points[next][0])
                        + Math.abs(points[current][1] - points[next][1]);

                    minDist[next] = Math.min(minDist[next], distance);
                }
            }
        }

        return totalCost;
    }
}