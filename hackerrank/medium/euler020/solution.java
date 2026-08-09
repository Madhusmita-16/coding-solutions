import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    // Returns true if year is a leap year
    static boolean isLeap(long year) {
        return (year % 400 == 0) ||
               (year % 4 == 0 && year % 100 != 0);
    }

    // Number of leap years before the given year
    static long leapYearsBefore(long year) {
        long y = year - 1;

        return y / 4 - y / 100 + y / 400;
    }

    /*
     * Returns the day of week for the first day of the given year.
     *
     * 0 = Monday
     * 1 = Tuesday
     * 2 = Wednesday
     * 3 = Thursday
     * 4 = Friday
     * 5 = Saturday
     * 6 = Sunday
     *
     * 1 January 1900 was Monday.
     */
    static int firstDayOfYear(long year) {

        long days;

        if (year >= 1900) {
            long years = year - 1900;

            days = 365L * years
                 + (leapYearsBefore(year) - leapYearsBefore(1900));

        } else {
            long years = 1900 - year;

            days = -(365L * years)
                 - (leapYearsBefore(1900) - leapYearsBefore(year));
        }

        long result = days % 7;

        if (result < 0) {
            result += 7;
        }

        return (int) result;
    }

    // Days before a particular month in a year
    static int daysBeforeMonth(long year, int month) {

        int[] normal = {
            0, 31, 59, 90, 120, 151,
            181, 212, 243, 273, 304, 334
        };

        int days = normal[month - 1];

        if (month > 2 && isLeap(year)) {
            days++;
        }

        return days;
    }

    // Returns weekday of the first day of the given month
    static int firstDayOfMonth(long year, int month) {

        int day = firstDayOfYear(year);

        day += daysBeforeMonth(year, month);

        return day % 7;
    }

    // Compare date1 with date2
    // Returns:
    // negative -> date1 < date2
    // zero     -> equal
    // positive -> date1 > date2
    static int compareDate(
            long y1, int m1, int d1,
            long y2, int m2, int d2) {

        if (y1 < y2) return -1;
        if (y1 > y2) return 1;

        if (m1 < m2) return -1;
        if (m1 > m2) return 1;

        return Integer.compare(d1, d2);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            long y1 = in.nextLong();
            int m1 = in.nextInt();
            int d1 = in.nextInt();

            long y2 = in.nextLong();
            int m2 = in.nextInt();
            int d2 = in.nextInt();

            long count = 0;

            long year = y1;
            int month = m1;

            while (year < y2 || (year == y2 && month <= m2)) {

                // First day of current month
                int weekday = firstDayOfMonth(year, month);

                // 6 = Sunday
                if (weekday == 6) {

                    // Check whether the first of this month
                    // falls inside the requested date range.
                    if (compareDate(
                            year, month, 1,
                            y1, m1, d1) >= 0 &&
                        compareDate(
                            year, month, 1,
                            y2, m2, d2) <= 0) {

                        count++;
                    }
                }

                // Move to next month
                month++;

                if (month == 13) {
                    month = 1;
                    year++;
                }
            }

            System.out.println(count);
        }

        in.close();
    }
}
