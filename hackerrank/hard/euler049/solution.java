import java.io.*;
import java.util.*;

public class Solution {

    static final int MAX = 1000000;
    static boolean[] prime;

    static void sieve() {

        prime = new boolean[MAX];

        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i * i < MAX; i++) {

            if (prime[i]) {

                for (int j = i * i; j < MAX; j += i) {
                    prime[j] = false;
                }
            }
        }
    }

    // Two numbers are permutations if their sorted digits are equal.
    static String signature(int n) {

        char[] digits = String.valueOf(n).toCharArray();

        Arrays.sort(digits);

        return new String(digits);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int limit = sc.nextInt();
        int k = sc.nextInt();

        sieve();

        /*
         * Group all primes >= 1000 by their digits.
         */
        HashMap<String, ArrayList<Integer>> groups =
                new HashMap<String, ArrayList<Integer>>();

        for (int p = 1000; p < MAX; p++) {

            if (!prime[p]) {
                continue;
            }

            String key = signature(p);

            ArrayList<Integer> list = groups.get(key);

            if (list == null) {
                list = new ArrayList<Integer>();
                groups.put(key, list);
            }

            list.add(p);
        }

        ArrayList<String> answers = new ArrayList<String>();

        /*
         * Process each permutation group.
         */
        for (ArrayList<Integer> list : groups.values()) {

            if (list.size() < k) {
                continue;
            }

            Collections.sort(list);

            HashSet<Integer> set = new HashSet<Integer>(list);

            /*
             * Try every possible starting prime.
             */
            for (int i = 0; i < list.size(); i++) {

                int start = list.get(i);

                // Only the first element must be below limit.
                if (start >= limit) {
                    break;
                }

                /*
                 * Choose the second element.
                 * It determines the common difference.
                 */
                for (int j = i + 1; j < list.size(); j++) {

                    int difference = list.get(j) - start;

                    boolean valid = true;

                    /*
                     * Check:
                     *
                     * start
                     * start + difference
                     * start + 2*difference
                     * ...
                     */
                    for (int x = 2; x < k; x++) {

                        long value =
                                (long) start +
                                (long) x * difference;

                        if (value >= MAX ||
                            !set.contains((int) value)) {

                            valid = false;
                            break;
                        }
                    }

                    if (!valid) {
                        continue;
                    }

                    /*
                     * Build concatenated answer.
                     */
                    StringBuilder result = new StringBuilder();

                    for (int x = 0; x < k; x++) {

                        result.append(
                            start + x * difference
                        );
                    }

                    answers.add(result.toString());
                }
            }
        }

        /*
         * Numerical ordering of the smallest starting value.
         *
         * All numbers in a sequence have the same number
         * of digits, so sorting the concatenated strings gives
         * the required ordering.
         */
        Collections.sort(answers);

        for (String answer : answers) {
            System.out.println(answer);
        }

        sc.close();
    }
}
