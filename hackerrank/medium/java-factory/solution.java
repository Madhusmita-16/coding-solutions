
class SumInLeavesVisitor extends TreeVis {
     private int sum = 0;
     
    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
    
    }

    public void visitLeaf(TreeLeaf leaf) {
      	 sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
   
    private long product = 1;

    public int getResult() {
        return (int) product;
    }

     public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            product = (product * node.getValue()) % 1000000007;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            product = (product * leaf.getValue()) % 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {

    private int evenDepthSum = 0;
    private int greenLeafSum = 0;

    public int getResult() {
        return Math.abs(evenDepthSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            evenDepthSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}
public class Solution {
  
    public static Tree solve() {
                Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] values = new int[n];
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            colors[i] = sc.nextInt();
        }

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        return buildTree(0, -1, 0, values, colors, graph);
    }

    private static Tree buildTree(
            int current,
            int parent,
            int depth,
            int[] values,
            int[] colors,
            ArrayList<Integer>[] graph) {

        Color color;

        if (colors[current] == 0) {
            color = Color.RED;
        } else {
            color = Color.GREEN;
        }

        ArrayList<Integer> children = new ArrayList<>();

        for (int next : graph[current]) {
            if (next != parent) {
                children.add(next);
            }
        }

        if (children.isEmpty()) {
            return new TreeLeaf(
                values[current],
                color,
                depth
            );
        }

        TreeNode node = new TreeNode(
            values[current],
            color,
            depth
        );

        for (int child : children) {
            Tree childTree = buildTree(
                child,
                current,
                depth + 1,
                values,
                colors,
                graph
            );

            node.addChild(childTree);
        }

        return node;
    }

