# Q3. My Calendar II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a  **triple booking**.

A  **triple booking**  happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).

The event can be represented as a pair of integers `startTime` and `endTime` that represents a booking on the half-open interval `[startTime, endTime)`, the range of real numbers `x` such that `startTime <= x < endTime`.

Implement the `MyCalendarTwo` class:

- MyCalendarTwo() Initializes the calendar object.
- boolean book(int startTime, int endTime) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

 

 **Example 1:** 

```
Input
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, true, true, true, false, true, true]

Explanation
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked. 
myCalendarTwo.book(50, 60); // return True, The event can be booked. 
myCalendarTwo.book(10, 40); // return True, The event can be double booked. 
myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.

```

 

 **Constraints:** 

- 0 <= start < end <= 109
- At most 1000 calls will be made to book.

## Solution

**Language:** Java  
**Runtime:** 45 ms (beats 90.05%)  
**Memory:** 47.3 MB (beats 68.67%)  
**Submitted:** 2026-08-14T17:42:08.409Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/my-calendar-ii/)