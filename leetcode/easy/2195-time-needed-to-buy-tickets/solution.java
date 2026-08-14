class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {

        int time = 0;

        for (int i = 0; i < tickets.length; i++) {

            if (i <= k) {
                // People before or at k can buy up to tickets[k] times
                time += Math.min(tickets[i], tickets[k]);
            } else {
                // People after k get one less turn
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }

        return time;
    }
}