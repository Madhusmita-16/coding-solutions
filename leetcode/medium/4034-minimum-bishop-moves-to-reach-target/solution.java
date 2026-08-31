class Solution {
    public int minBishopMoves(int[] source, int[] target) {

        int sr = source[0];
        int sc = source[1];

        int tr = target[0];
        int tc = target[1];

        // Bishop can only stay on the same color
        if ((sr + sc) % 2 != (tr + tc) % 2) {
            return -1;
        }

        // Same diagonal
        if (Math.abs(sr - tr) == Math.abs(sc - tc)) {
            return 1;
        }

        // Same color but different diagonal -> always reachable in 2 moves
        return 2;
    }
}