class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i = 0; i < flowerbed.length; i++) {

            if (flowerbed[i] == 0) {

                // Check left side
                boolean leftEmpty = (i == 0 || flowerbed[i - 1] == 0);

                // Check right side
                boolean rightEmpty = (i == flowerbed.length - 1
                                      || flowerbed[i + 1] == 0);

                // We can plant here
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;
                    n--;

                    if (n == 0) {
                        return true;
                    }
                }
            }
        }

        return n <= 0;
    }
}