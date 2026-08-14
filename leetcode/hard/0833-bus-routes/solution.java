import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // stop -> buses that visit this stop
        Map<Integer, List<Integer>> stopToBuses = new HashMap<>();

        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBuses
                    .computeIfAbsent(stop, x -> new ArrayList<>())
                    .add(bus);
            }
        }

        // BFS over bus stops
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedBus = new boolean[routes.length];

        queue.offer(source);
        visitedStops.add(source);

        int busesTaken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            busesTaken++;

            for (int i = 0; i < size; i++) {
                int stop = queue.poll();

                List<Integer> buses = stopToBuses.get(stop);

                if (buses == null) {
                    continue;
                }

                for (int bus : buses) {
                    // Process each bus only once
                    if (visitedBus[bus]) {
                        continue;
                    }

                    visitedBus[bus] = true;

                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) {
                            return busesTaken;
                        }

                        if (visitedStops.add(nextStop)) {
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}