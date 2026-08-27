class Solution {
    public int[] asteroidCollision(int[] asteroids) {

        int[] stack = new int[asteroids.length];
        int top = 0;

        for (int asteroid : asteroids) {

            boolean destroyed = false;

            // Collision occurs only when:
            // stack top is moving right
            // current asteroid is moving left
            while (top > 0 && stack[top - 1] > 0 && asteroid < 0) {

                int topAsteroid = stack[top - 1];

                if (topAsteroid < -asteroid) {
                    // Stack asteroid is smaller, so it explodes
                    top--;
                }
                else if (topAsteroid == -asteroid) {
                    // Both explode
                    top--;
                    destroyed = true;
                    break;
                }
                else {
                    // Current asteroid is smaller
                    destroyed = true;
                    break;
                }
            }

            // Current asteroid survives
            if (!destroyed) {
                stack[top++] = asteroid;
            }
        }

        return java.util.Arrays.copyOf(stack, top);
    }
}