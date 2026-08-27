import java.util.*;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {

        // Count occurrences of each number
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Store frequencies
        Set<Integer> frequencies = new HashSet<>();

        for (int count : map.values()) {

            // Frequency already exists
            if (frequencies.contains(count)) {
                return false;
            }

            frequencies.add(count);
        }

        return true;
    }
}