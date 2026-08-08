import java.util.*;

class Solution {

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // robot[i][0] = position
        // robot[i][1] = shooting distance
        int[][] robot = new int[n][2];

        for (int i = 0; i < n; i++) {
            robot[i][0] = robots[i];
            robot[i][1] = distance[i];
        }

        // Sort robots by position.
        Arrays.sort(robot, (a, b) -> Integer.compare(a[0], b[0]));

        // Sort walls.
        Arrays.sort(walls);

        int prevLeft = 0;
        int prevRight = 0;

        for (int i = 0; i < n; i++) {

            int pos = robot[i][0];
            int dist = robot[i][1];

            // --------------------------------------------------
            // Range when current robot shoots LEFT
            // --------------------------------------------------

            int leftUntil = pos - dist;

            if (i > 0) {
                // Previous robot blocks the bullet.
                leftUntil = Math.max(
                    leftUntil,
                    robot[i - 1][0] + 1
                );
            }

            int currentLeft = 0;

            // Current robot shoots left, previous shoots left.
            currentLeft = Math.max(
                currentLeft,
                prevLeft + count(walls, leftUntil, pos)
            );

            // --------------------------------------------------
            // Current robot shoots LEFT
            // Previous robot shoots RIGHT
            // --------------------------------------------------

            if (i > 0) {

                int prevPos = robot[i - 1][0];
                int prevDist = robot[i - 1][1];

                /*
                 * Previous robot shoots right.
                 *
                 * Its bullet can reach:
                 *
                 * [prevPos, prevPos + prevDist]
                 *
                 * The current robot's left bullet covers:
                 *
                 * [leftUntil, pos]
                 *
                 * We must not count walls already destroyed
                 * by the previous robot.
                 */

                int start = Math.max(
                    leftUntil,
                    Math.min(pos, prevPos + prevDist + 1)
                );

                currentLeft = Math.max(
                    currentLeft,
                    prevRight + count(walls, start, pos)
                );
            }

            // --------------------------------------------------
            // Range when current robot shoots RIGHT
            // --------------------------------------------------

            int rightUntil = pos + dist;

            if (i + 1 < n) {
                // Next robot blocks the bullet.
                rightUntil = Math.min(
                    rightUntil,
                    robot[i + 1][0] - 1
                );
            }

            int currentRight = 0;

            // Previous robot shoots LEFT.
            currentRight = Math.max(
                currentRight,
                prevLeft + count(walls, pos, rightUntil)
            );

            // Previous robot shoots RIGHT.
            currentRight = Math.max(
                currentRight,
                prevRight + count(walls, pos, rightUntil)
            );

            // Move to next robot.
            prevLeft = currentLeft;
            prevRight = currentRight;
        }

        return Math.max(prevLeft, prevRight);
    }

    /*
     * Count walls in the inclusive range [left, right].
     */
    private int count(int[] walls, int left, int right) {
        if (left > right) {
            return 0;
        }

        return upperBound(walls, right)
             - lowerBound(walls, left);
    }

    /*
     * First index where arr[index] >= target.
     */
    private int lowerBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    /*
     * First index where arr[index] > target.
     */
    private int upperBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}