import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // Current temperature is warmer than
            // temperatures waiting in the stack
            while (!stack.isEmpty()
                    && temperatures[i] > temperatures[stack.peek()]) {

                int previousDay = stack.pop();

                answer[previousDay] = i - previousDay;
            }

            // Store current day's index
            stack.push(i);
        }

        return answer;
    }
}