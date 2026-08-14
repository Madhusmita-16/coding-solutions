import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {

        int n = prices.length;
        int[] answer = prices.clone();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {

            // Remove prices that cannot be the discount
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }

            // Top is the first valid price <= prices[i]
            if (!stack.isEmpty()) {
                answer[i] = prices[i] - stack.peek();
            }

            // Current price becomes a candidate discount
            stack.push(prices[i]);
        }

        return answer;
    }
}