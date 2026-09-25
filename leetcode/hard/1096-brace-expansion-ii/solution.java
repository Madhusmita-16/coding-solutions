import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length());

        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);

        return answer;
    }

    private Set<String> parse(String s, int start, int end) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        int i = start;

        while (i < end) {
            char ch = s.charAt(i);

            // Union
            if (ch == ',') {
                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                i++;
            }

            // Nested expression
            else if (ch == '{') {
                int j = findClosingBrace(s, i);

                Set<String> inside = parse(s, i + 1, j);

                current = multiply(current, inside);

                i = j + 1;
            }

            // Letter
            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                current = multiply(current, letter);

                i++;
            }
        }

        result.addAll(current);

        return result;
    }

    private int findClosingBrace(String s, int open) {
        int balance = 0;

        for (int i = open; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;

                if (balance == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}