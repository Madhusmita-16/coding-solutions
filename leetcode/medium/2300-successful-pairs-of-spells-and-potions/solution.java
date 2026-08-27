import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        Arrays.sort(potions);

        int[] pairs = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {

            int left = 0;
            int right = potions.length;

            // Find first potion where spell * potion >= success
            while (left < right) {

                int mid = left + (right - left) / 2;

                if ((long) spells[i] * potions[mid] >= success) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // All potions from left to end are successful
            pairs[i] = potions.length - left;
        }

        return pairs;
    }
}