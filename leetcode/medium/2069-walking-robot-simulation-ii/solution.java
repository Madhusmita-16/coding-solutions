class Robot {
    private int width, height;
    private int x, y;
    private int dir;

    // 0 = East, 1 = North, 2 = West, 3 = South
    private final String[] directions = {
        "East", "North", "West", "South"
    };

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0;
    }

    public void step(int num) {
        int perimeter = 2 * (width + height) - 4;

        int steps = num % perimeter;

        // If num is a multiple of perimeter, we must
        // actually process one complete cycle so that
        // the direction becomes South at (0,0).
        if (steps == 0) {
            steps = perimeter;
        }

        while (steps > 0) {
            if (dir == 0) { // East
                int move = Math.min(steps, width - 1 - x);
                x += move;
                steps -= move;

                if (steps > 0) {
                    dir = 1; // North
                }
            }
            else if (dir == 1) { // North
                int move = Math.min(steps, height - 1 - y);
                y += move;
                steps -= move;

                if (steps > 0) {
                    dir = 2; // West
                }
            }
            else if (dir == 2) { // West
                int move = Math.min(steps, x);
                x -= move;
                steps -= move;

                if (steps > 0) {
                    dir = 3; // South
                }
            }
            else { // South
                int move = Math.min(steps, y);
                y -= move;
                steps -= move;

                if (steps > 0) {
                    dir = 0; // East
                }
            }
        }
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    public String getDir() {
        return directions[dir];
    }
}