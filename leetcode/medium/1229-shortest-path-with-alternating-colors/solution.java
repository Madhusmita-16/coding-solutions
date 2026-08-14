import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(
            int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        // Build red graph
        for (int[] edge : redEdges) {
            red[edge[0]].add(edge[1]);
        }

        // Build blue graph
        for (int[] edge : blueEdges) {
            blue[edge[0]].add(edge[1]);
        }

        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        answer[0] = 0;

        // visited[node][0] = reached using red as last edge
        // visited[node][1] = reached using blue as last edge
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        // We can start with either color
        queue.offer(new int[]{0, 0}); // last edge red
        queue.offer(new int[]{0, 1}); // last edge blue

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int node = current[0];
                int lastColor = current[1];

                // If last was red, next must be blue
                if (lastColor == 0) {
                    for (int next : blue[node]) {
                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            queue.offer(new int[]{next, 1});

                            if (answer[next] == -1) {
                                answer[next] = distance + 1;
                            }
                        }
                    }
                }

                // If last was blue, next must be red
                else {
                    for (int next : red[node]) {
                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            queue.offer(new int[]{next, 0});

                            if (answer[next] == -1) {
                                answer[next] = distance + 1;
                            }
                        }
                    }
                }
            }

            distance++;
        }

        return answer;
    }
}