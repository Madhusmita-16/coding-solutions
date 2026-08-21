class Solution {
    public int minimumDistance(String word) {
        int n = word.length();

        // dp[other] = minimum cost after typing the current character,
        // where one finger is on the current character and the other
        // finger is at position 'other'.
        int[][] dist = new int[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                dist[i][j] = distance(i, j);
            }
        }

        int INF = 1_000_000_000;

        int[] dp = new int[27];
        for (int i = 0; i < 27; i++) {
            dp[i] = INF;
        }

        // 26 means the second finger is still unused/free.
        dp[26] = 0;

        int prev = word.charAt(0) - 'A';

        for (int i = 1; i < n; i++) {
            int cur = word.charAt(i) - 'A';

            int[] next = new int[27];
            for (int j = 0; j < 27; j++) {
                next[j] = INF;
            }

            for (int other = 0; other < 27; other++) {
                if (dp[other] == INF) {
                    continue;
                }

                // Use the finger currently on 'prev'.
                int cost = dp[other] + distance(prev, cur);
                next[other] = Math.min(next[other], cost);

                // Use the other finger.
                if (other == 26) {
                    // Its initial position is free.
                    next[prev] = Math.min(next[prev], dp[other]);
                } else {
                    cost = dp[other] + distance(other, cur);
                    next[prev] = Math.min(next[prev], cost);
                }
            }

            dp = next;
            prev = cur;
        }

        int answer = INF;

        for (int cost : dp) {
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    private int distance(int a, int b) {
        if (a == 26 || b == 26) {
            return 0;
        }

        int x1 = a / 6;
        int y1 = a % 6;

        int x2 = b / 6;
        int y2 = b % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}