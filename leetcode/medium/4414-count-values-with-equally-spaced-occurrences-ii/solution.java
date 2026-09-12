class Solution {
    public int countSpecialIntegers(int[] nums) {
        int[] velquorani = nums;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < velquorani.length; i++) {
            map.computeIfAbsent(velquorani[i], k -> new ArrayList<>()).add(i);
        }

        int count = 0;

        for (List<Integer> positions : map.values()) {
            if (positions.size() < 3) continue;

            int diff = positions.get(1) - positions.get(0);
            boolean special = true;

            for (int i = 2; i < positions.size(); i++) {
                if (positions.get(i) - positions.get(i - 1) != diff) {
                    special = false;
                    break;
                }
            }

            if (special) count++;
        }

        return count;
    }
}