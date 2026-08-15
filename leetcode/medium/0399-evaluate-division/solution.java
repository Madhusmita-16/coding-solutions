import java.util.*;

class Solution {

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries) {

        // Graph:
        // variable -> (neighbor, ratio)
        Map<String, List<Edge>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            // a / b = value
            graph.get(a).add(new Edge(b, value));

            // b / a = 1 / value
            graph.get(b).add(new Edge(a, 1.0 / value));
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            // Undefined variable
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                result[i] = -1.0;
                continue;
            }

            // Same variable
            if (start.equals(end)) {
                result[i] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();

            result[i] = dfs(
                    start,
                    end,
                    1.0,
                    graph,
                    visited
            );
        }

        return result;
    }

    private double dfs(
            String current,
            String target,
            double product,
            Map<String, List<Edge>> graph,
            Set<String> visited) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Edge edge : graph.get(current)) {

            if (visited.contains(edge.to)) {
                continue;
            }

            double result = dfs(
                    edge.to,
                    target,
                    product * edge.weight,
                    graph,
                    visited
            );

            if (result != -1.0) {
                return result;
            }
        }

        return -1.0;
    }

    static class Edge {
        String to;
        double weight;

        Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
