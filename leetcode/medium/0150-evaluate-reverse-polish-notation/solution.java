import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            // If token is a number
            if (!token.equals("+") &&
                !token.equals("-") &&
                !token.equals("*") &&
                !token.equals("/")) {

                stack.push(Integer.parseInt(token));

            } else {

                // Right operand comes out first
                int b = stack.pop();

                // Left operand comes out second
                int a = stack.pop();

                int result = 0;

                switch (token) {
                    case "+":
                        result = a + b;
                        break;

                    case "-":
                        result = a - b;
                        break;

                    case "*":
                        result = a * b;
                        break;

                    case "/":
                        result = a / b;
                        break;
                }

                stack.push(result);
            }
        }

        return stack.pop();
    }
}