import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>())
               .add(seat[1]);
        }

        // Rows with no reservations can fit 2 families
        int result = (n - map.size()) * 2;

        // Check rows having reservations
        for (Set<Integer> seats : map.values()) {

            // Seats 2,3,4,5
            boolean left = true;
            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            // Seats 4,5,6,7
            boolean middle = true;
            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            // Seats 6,7,8,9
            boolean right = true;
            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                result += 2;
            } else if (left || middle || right) {
                result += 1;
            }
        }

        return result;
    }
}