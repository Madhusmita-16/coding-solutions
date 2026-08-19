class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>())
               .add(seat[1]);
        }

        // Initially, every row can have 2 families
        int result = (n - map.size()) * 2;

        for (Set<Integer> seats : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            // Left block: 2,3,4,5
            for (int i = 2; i <= 5; i++) {
                if (seats.contains(i)) {
                    left = false;
                    break;
                }
            }

            // Middle block: 4,5,6,7
            for (int i = 4; i <= 7; i++) {
                if (seats.contains(i)) {
                    middle = false;
                    break;
                }
            }

            // Right block: 6,7,8,9
            for (int i = 6; i <= 9; i++) {
                if (seats.contains(i)) {
                    right = false;
                    break;
                }
            }

            if (left && right) {
                // Two families can fit
                result += 2;
            } else if (left || middle || right) {
                // Only one family can fit
                result += 1;
            }
        }

        return result;
    }
}