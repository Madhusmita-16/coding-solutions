import java.util.*;

class Solution {
    public int equalPairs(int[][] grid) {

        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();

        // Store all rows
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n; j++) {
                row.append(grid[i][j]).append(",");
            }

            String key = row.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int count = 0;

        // Check all columns
        for (int j = 0; j < n; j++) {
            StringBuilder column = new StringBuilder();

            for (int i = 0; i < n; i++) {
                column.append(grid[i][j]).append(",");
            }

            String key = column.toString();

            if (map.containsKey(key)) {
                count += map.get(key);
            }
        }

        return count;
    }
}