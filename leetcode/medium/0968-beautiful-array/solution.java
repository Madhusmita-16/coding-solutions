import java.util.*;

class Solution {
    public int[] beautifulArray(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(1);

        while (list.size() < n) {
            List<Integer> next = new ArrayList<>();

            // Generate odd numbers: 2*x - 1
            for (int x : list) {
                int odd = 2 * x - 1;
                if (odd <= n) {
                    next.add(odd);
                }
            }

            // Generate even numbers: 2*x
            for (int x : list) {
                int even = 2 * x;
                if (even <= n) {
                    next.add(even);
                }
            }

            list = next;
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}