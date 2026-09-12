class Solution {
    public int countSpecialIntegers(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int count = 0;

        for (List<Integer> indices : map.values()) {
            if (indices.size() == 3) {
                int d1 = indices.get(1) - indices.get(0);
                int d2 = indices.get(2) - indices.get(1);

                if (d1 == d2) {
                    count++;
                }
            }
        }

        return count;
    }
}