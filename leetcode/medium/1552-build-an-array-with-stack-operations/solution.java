import java.util.*;

class Solution {
    public List<String> buildArray(int[] target, int n) {

        List<String> result = new ArrayList<>();

        int targetIndex = 0;

        for (int num = 1; num <= n && targetIndex < target.length; num++) {

            // Every number from the stream is pushed
            result.add("Push");

            // If this number is not required, remove it
            if (num != target[targetIndex]) {
                result.add("Pop");
            } else {
                // Required number, keep it
                targetIndex++;
            }
        }

        return result;
    }
}