class Solution {
    public int repeatedStringMatch(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Repeat until length is at least b.length()
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        // Check after the minimum required repetitions
        if (sb.toString().contains(b)) {
            return count;
        }

        // One extra repetition may be required
        sb.append(a);
        count++;

        if (sb.toString().contains(b)) {
            return count;
        }

        return -1;
    }
}