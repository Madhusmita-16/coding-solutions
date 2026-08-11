import java.util.*;

class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);

        int p = prices.length;
        int d = discounts.length;

        int count = Math.min(p, d);

        double total = 0.0;

        // Undiscounted items
        for (int i = 0; i < p - count; i++) {
            total += prices[i];
        }

        // Pair largest prices with largest discounts
        for (int i = 0; i < count; i++) {
            int price = prices[p - 1 - i];
            int discount = discounts[d - 1 - i];

            total += (double) price * (100 - discount) / 100.0;
        }

        return total;
    }
}