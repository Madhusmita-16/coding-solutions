class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {

        if (left > right) {
            return null;
        }

        // Choose the middle element
        int mid = left + (right - left) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        // Build left and right subtrees
        root.left = buildTree(nums, left, mid - 1);
        root.right = buildTree(nums, mid + 1, right);

        return root;
    }
}