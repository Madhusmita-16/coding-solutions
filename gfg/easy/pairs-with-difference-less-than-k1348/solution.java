import java.util.Arrays;

class Solution {
    public static int countPairs(int arr[], int k) {
        int n = arr.length;

        Arrays.sort(arr);

        int left = 0;
        long count = 0;

        for (int right = 0; right < n; right++) {

            // Make sure arr[right] - arr[left] < k
            while (left < right && arr[right] - arr[left] >= k) {
                left++;
            }

            // All elements from left to right-1
            // form valid pairs with arr[right]
            count += right - left;
        }

        return (int) count;
    }
}
