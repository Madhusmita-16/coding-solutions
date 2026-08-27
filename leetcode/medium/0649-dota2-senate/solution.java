import java.util.*;

class Solution {
    public String predictPartyVictory(String senate) {

        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        int n = senate.length();

        // Store the indices of each party
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {

            int r = radiant.poll();
            int d = dire.poll();

            if (r < d) {
                // Radiant senator acts first and bans Dire
                radiant.offer(r + n);
            } else {
                // Dire senator acts first and bans Radiant
                dire.offer(d + n);
            }
        }

        if (radiant.isEmpty()) {
            return "Dire";
        } else {
            return "Radiant";
        }
    }
}