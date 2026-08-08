class Solution {
    public boolean canBeEqual(String s1, String s2) {

        // Even indices: 0, 2
        boolean evenSame =
            (s1.charAt(0) == s2.charAt(0) &&
             s1.charAt(2) == s2.charAt(2)) ||
            (s1.charAt(0) == s2.charAt(2) &&
             s1.charAt(2) == s2.charAt(0));

        // Odd indices: 1, 3
        boolean oddSame =
            (s1.charAt(1) == s2.charAt(1) &&
             s1.charAt(3) == s2.charAt(3)) ||
            (s1.charAt(1) == s2.charAt(3) &&
             s1.charAt(3) == s2.charAt(1));

        return evenSame && oddSame;
    }
}