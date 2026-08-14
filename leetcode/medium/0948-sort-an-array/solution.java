class Solution {
    public int[] sortArray(int[] nums) {
        int n = nums.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // Extract maximum one by one
        for (int i = n - 1; i > 0; i--) {
            // Move largest element to the end
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            // Restore heap
            heapify(nums, i, 0);
        }

        return nums;
    }

    private void heapify(int[] nums, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && nums[left] > nums[largest]) {
                largest = left;
            }

            if (right < size && nums[right] > nums[largest]) {
                largest = right;
            }

            if (largest == i) {
                break;
            }

            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;

            i = largest;
        }
    }
}