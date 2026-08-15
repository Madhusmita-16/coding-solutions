import java.util.HashMap;

class Solution {

    HashMap<Integer, Integer> map;
    int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();

        // Store inorder value -> index
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        // First element in preorder is the root
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int mid = map.get(rootValue);

        // Build left subtree first
        root.left = build(preorder, left, mid - 1);

        // Then build right subtree
        root.right = build(preorder, mid + 1, right);

        return root;
    }
}