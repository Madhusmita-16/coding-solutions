import java.util.*;

class RangeModule {

    // start -> end
    private final TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (left >= right) {
            return;
        }

        // Find the interval immediately before or at 'left'
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        // If it overlaps/touches [left, right), merge it
        if (entry != null && entry.getValue() >= left) {
            left = Math.min(left, entry.getKey());
            right = Math.max(right, entry.getValue());
            map.remove(entry.getKey());
        }

        // Merge all intervals that overlap/touch the current range
        entry = map.ceilingEntry(left);

        while (entry != null && entry.getKey() <= right) {
            right = Math.max(right, entry.getValue());
            map.remove(entry.getKey());
            entry = map.ceilingEntry(left);
        }

        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        if (left >= right) {
            return true;
        }

        // Find the interval with the greatest start <= left
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        return entry != null && entry.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        if (left >= right) {
            return;
        }

        // Check interval containing or starting before 'left'
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);

        if (entry != null && entry.getValue() > left) {
            int start = entry.getKey();
            int end = entry.getValue();

            map.remove(start);

            // Keep the left part
            if (start < left) {
                map.put(start, left);
            }

            // Keep the right part
            if (end > right) {
                map.put(right, end);
                return;
            }
        }

        // Remove intervals completely/partially covered by [left, right)
        entry = map.ceilingEntry(left);

        while (entry != null && entry.getKey() < right) {
            int start = entry.getKey();
            int end = entry.getValue();

            map.remove(start);

            // Preserve the part after 'right'
            if (end > right) {
                map.put(right, end);
                break;
            }

            entry = map.ceilingEntry(left);
        }
    }
}