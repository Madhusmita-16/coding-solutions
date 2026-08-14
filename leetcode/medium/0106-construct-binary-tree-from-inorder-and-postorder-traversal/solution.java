import java.util.HashMap;

class Solution {

    private int postIndex;
    private HashMap<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        inorderMap = new HashMap<>();

        // Store value -> index in inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        postIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element in postorder is the root
        int rootValue = postorder[postIndex--];

        TreeNode root = new TreeNode(rootValue);

        int rootIndex = inorderMap.get(rootValue);

        // IMPORTANT:
        // Build right subtree first because we are
        // traversing postorder from right to left.
        root.right = build(postorder, rootIndex + 1, right);

        root.left = build(postorder, left, rootIndex - 1);

        return root;
    }
}