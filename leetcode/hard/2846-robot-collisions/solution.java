import java.util.*;

class Solution {
    public List<Integer> survivedRobotsHealths(
            int[] positions,
            int[] healths,
            String directions) {

        int n = positions.length;

        // Store robot indices
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort robots by their positions
        Arrays.sort(indices, (a, b) ->
            Integer.compare(positions[a], positions[b])
        );

        // Stack stores indices of surviving R-moving robots
        Deque<Integer> stack = new ArrayDeque<>();

        for (int idx : indices) {

            // Right-moving robot
            if (directions.charAt(idx) == 'R') {
                stack.push(idx);
            }

            // Left-moving robot
            else {
                while (!stack.isEmpty() && healths[idx] > 0) {

                    int right = stack.peek();

                    // R robot has greater health
                    if (healths[right] > healths[idx]) {
                        healths[right]--;
                        healths[idx] = 0;
                    }

                    // L robot has greater health
                    else if (healths[right] < healths[idx]) {
                        healths[idx]--;
                        healths[right] = 0;
                        stack.pop();
                    }

                    // Equal health
                    else {
                        healths[idx] = 0;
                        healths[right] = 0;
                        stack.pop();
                    }
                }
            }
        }

        // Return survivors in their original input order
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }

        return result;
    }
}