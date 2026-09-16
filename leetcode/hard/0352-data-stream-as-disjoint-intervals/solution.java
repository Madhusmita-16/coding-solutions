import java.util.Map;
import java.util.TreeMap;

class SummaryRanges {

    private final TreeMap<Integer, Integer> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int value) {

        // Already covered by an existing interval
        Map.Entry<Integer, Integer> left = intervals.floorEntry(value);

        if (left != null && left.getValue() >= value) {
            return;
        }

        Map.Entry<Integer, Integer> right = intervals.ceilingEntry(value);

        boolean mergeLeft = left != null && left.getValue() + 1 == value;
        boolean mergeRight = right != null && right.getKey() - 1 == value;

        // Merge both left and right intervals
        if (mergeLeft && mergeRight) {

            int newEnd = right.getValue();

            intervals.put(left.getKey(), newEnd);
            intervals.remove(right.getKey());

        }
        // Extend left interval
        else if (mergeLeft) {

            intervals.put(left.getKey(), value);

        }
        // Extend right interval to the left
        else if (mergeRight) {

            int end = right.getValue();

            intervals.remove(right.getKey());
            intervals.put(value, end);

        }
        // Create a new interval
        else {

            intervals.put(value, value);
        }
    }

    public int[][] getIntervals() {

        int[][] result = new int[intervals.size()][2];

        int i = 0;

        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }

        return result;
    }
}