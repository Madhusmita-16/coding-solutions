import java.util.*;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {

        int[] result = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        int prevTime = 0;

        for (String log : logs) {

            String[] parts = log.split(":");

            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (type.equals("start")) {

                // Current function on stack was running
                // from prevTime up to time - 1
                if (!stack.isEmpty()) {
                    result[stack.peek()] += time - prevTime;
                }

                // New function starts
                stack.push(id);

                // Its execution starts at 'time'
                prevTime = time;

            } else {

                // End time is inclusive
                result[stack.peek()] += time - prevTime + 1;

                // Function finished
                stack.pop();

                // Next execution starts after this timestamp
                prevTime = time + 1;
            }
        }

        return result;
    }
}