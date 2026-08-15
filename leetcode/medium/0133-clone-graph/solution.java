import java.util.*;

class Solution {
    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        // Original node -> cloned node
        Map<Node, Node> clones = new HashMap<>();

        Queue<Node> queue = new LinkedList<>();

        // Clone the starting node
        Node clonedStart = new Node(node.val);
        clones.put(node, clonedStart);
        queue.offer(node);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            for (Node neighbor : current.neighbors) {

                // If neighbor has not been cloned yet
                if (!clones.containsKey(neighbor)) {

                    Node clonedNeighbor = new Node(neighbor.val);

                    clones.put(neighbor, clonedNeighbor);
                    queue.offer(neighbor);
                }

                // Connect cloned nodes
                clones.get(current).neighbors.add(
                    clones.get(neighbor)
                );
            }
        }

        return clonedStart;
    }
}