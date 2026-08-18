import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleSet = new HashSet<>();

        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }

        // Directions: North, East, South, West
        int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
        };

        int x = 0;
        int y = 0;
        int dir = 0; // Initially facing North
        int maxDistance = 0;

        for (int command : commands) {

            if (command == -2) {
                // Turn left
                dir = (dir + 3) % 4;

            } else if (command == -1) {
                // Turn right
                dir = (dir + 1) % 4;

            } else {
                // Move forward one unit at a time
                for (int step = 0; step < command; step++) {
                    int newX = x + directions[dir][0];
                    int newY = y + directions[dir][1];

                    // Stop if obstacle is directly ahead
                    if (obstacleSet.contains(newX + "," + newY)) {
                        break;
                    }

                    x = newX;
                    y = newY;

                    maxDistance = Math.max(
                        maxDistance,
                        x * x + y * y
                    );
                }
            }
        }

        return maxDistance;
    }
}