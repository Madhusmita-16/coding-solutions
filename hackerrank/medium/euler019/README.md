# euler019

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 19 from projecteuler.net

You are given the following information, but you may prefer to do some research for yourself.

1 Jan 1900 was a Monday.
Thirty days has September,
April, June and November.
All the rest have thirty-one,
Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.

A leap year occurs on any year evenly divisible by, but not on a century unless it is divisible by.

How many Sundays fell on the first of the month between two dates(both inclusive)?

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Each testcase will contain two lines
on first line denoting starting date.
on second line denoting ending date.

 **Constraints** 

-
-
-
-
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
1900 1 1
1910 1 1
2000 1 1
2020 1 1

```

 **Sample Output** 

```
18
35

```

 **Explanation** 

For testcase 1, we have the following sundays :-

```
1 April 1900
1 July 1900
1 September 1901
1 December 1901
1 June 1902
1 February 1903
1 March 1903
1 November 1903
1 May 1904
1 January 1905
1 October 1905
1 April 1906
1 July 1906
1 September 1907 
1 December 1907
1 March 1908
1 November 1908
1 August 1909

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:58:03.467Z  

```java
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

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler019/problem)