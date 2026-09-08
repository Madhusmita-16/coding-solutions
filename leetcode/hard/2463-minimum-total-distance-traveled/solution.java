import java.util.*;

class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {

        // Sort robots
        Collections.sort(robot);

        // Sort factories by position
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        int n = robot.size();

        // dp[i] = minimum cost to repair first i robots
        long[] dp = new long[n + 1];

        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;

        for (int[] f : factory) {

            int position = f[0];
            int limit = f[1];

            long[] newDp = new long[n + 1];
            Arrays.fill(newDp, Long.MAX_VALUE / 2);

            // Number of robots already handled
            for (int i = 0; i <= n; i++) {

                if (dp[i] >= Long.MAX_VALUE / 2) {
                    continue;
                }

                long cost = 0;

                // Assign k robots to current factory
                for (int k = 1; k <= limit && i + k <= n; k++) {

                    int robotPosition = robot.get(i + k - 1);

                    cost += Math.abs((long) robotPosition - position);

                    newDp[i + k] = Math.min(
                        newDp[i + k],
                        dp[i] + cost
                    );
                }

                // Current factory repairs zero robots
                newDp[i] = Math.min(newDp[i], dp[i]);
            }

            dp = newDp;
        }

        return dp[n];
    }
}