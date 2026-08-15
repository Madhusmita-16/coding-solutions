class Solution {

    private double radius;
    private double xCenter;
    private double yCenter;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.xCenter = x_center;
        this.yCenter = y_center;
    }

    public double[] randPoint() {

        while (true) {

            double x = (Math.random() * 2 - 1) * radius;
            double y = (Math.random() * 2 - 1) * radius;

            // Check whether the point is inside the circle
            if (x * x + y * y <= radius * radius) {
                return new double[] {
                    xCenter + x,
                    yCenter + y
                };
            }
        }
    }
}