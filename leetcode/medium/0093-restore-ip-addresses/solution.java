class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(String s, int index, int parts,
                            StringBuilder current,
                            List<String> result) {

        // Four parts created
        if (parts == 4) {
            if (index == s.length()) {
                result.add(current.toString());
            }
            return;
        }

        // Try segment lengths 1, 2, and 3
        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) {
                break;
            }

            String part = s.substring(index, index + len);

            // Leading zero is not allowed
            if (part.length() > 1 && part.charAt(0) == '0') {
                continue;
            }

            // Value must be <= 255
            int value = Integer.parseInt(part);

            if (value > 255) {
                continue;
            }

            // Add dot before every part except the first
            int oldLength = current.length();

            if (parts > 0) {
                current.append('.');
            }

            current.append(part);

            backtrack(s, index + len, parts + 1, current, result);

            // Backtrack
            current.setLength(oldLength);
        }
    }
}