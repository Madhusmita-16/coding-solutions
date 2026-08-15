class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                sum += current.val;
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add((double) sum / size);
        }
        
        return result;
    }
}