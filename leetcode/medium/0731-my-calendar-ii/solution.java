import java.util.*;

class MyCalendarTwo {

    private List<int[]> booked;
    private List<int[]> overlap;

    public MyCalendarTwo() {
        booked = new ArrayList<>();
        overlap = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {

        // If the new event overlaps an already double-booked
        // interval, it would create a triple booking.
        for (int[] interval : overlap) {
            int start = Math.max(startTime, interval[0]);
            int end = Math.min(endTime, interval[1]);

            if (start < end) {
                return false;
            }
        }

        // Find portions that become double-booked.
        for (int[] interval : booked) {
            int start = Math.max(startTime, interval[0]);
            int end = Math.min(endTime, interval[1]);

            if (start < end) {
                overlap.add(new int[]{start, end});
            }
        }

        // Add the new event only after all checks succeed.
        booked.add(new int[]{startTime, endTime});

        return true;
    }
}