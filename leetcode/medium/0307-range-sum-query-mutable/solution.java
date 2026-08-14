class NumArray {
    private int[] tree;
    private int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums.clone();
        this.tree = new int[nums.length + 1];

        // Build Fenwick Tree
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int difference = val - nums[index];
        nums[index] = val;

        // Fenwick Tree uses 1-based indexing
        add(index + 1, difference);
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    // Add value to a position
    private void add(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    // Sum from index 1 to index
    private int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }

        return sum;
    }
}