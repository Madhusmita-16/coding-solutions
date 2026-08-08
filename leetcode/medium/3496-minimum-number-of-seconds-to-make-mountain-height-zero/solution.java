class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long left = 0;

        // Upper bound: the fastest/sufficient worker doing
        // the entire mountain alone.
        long maxWorker = 0;

        for (int time : workerTimes) {
            maxWorker = Math.max(maxWorker, time);
        }

        long right = maxWorker
                * (long) mountainHeight
                * (mountainHeight + 1)
                / 2;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canFinish(mountainHeight, workerTimes, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canFinish(
            int mountainHeight,
            int[] workerTimes,
            long seconds) {

        long reduced = 0;

        for (int worker : workerTimes) {

            /*
             * worker * k * (k + 1) / 2 <= seconds
             *
             * We need the largest possible k.
             *
             * Solve:
             *
             * k(k + 1) / 2 <= seconds / worker
             */

            long work = seconds / worker;

            /*
             * k = floor((-1 + sqrt(1 + 8 * work)) / 2)
             */
            long k = (long) ((Math.sqrt(1.0 + 8.0 * work) - 1) / 2);

            /*
             * Correct possible floating-point rounding.
             */
            while ((k + 1) * (k + 2) / 2 <= work) {
                k++;
            }

            while (k * (k + 1) / 2 > work) {
                k--;
            }

            reduced += k;

            if (reduced >= mountainHeight) {
                return true;
            }
        }

        return false;
    }
}