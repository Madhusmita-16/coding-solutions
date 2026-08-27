import java.util.*;

class Solution {
    public int[] canSeePersonsCount(int[] heights) {

        int n = heights.length;
        int[] answer = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {

            // Every shorter person can be seen
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.pop();
                answer[i]++;
            }

            // First taller person can also be seen
            if (!stack.isEmpty()) {
                answer[i]++;
            }

            stack.push(heights[i]);
        }

        return answer;
    }
}