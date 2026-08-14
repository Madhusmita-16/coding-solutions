class Solution {
    public boolean lemonadeChange(int[] bills) {

        int five = 0;
        int ten = 0;

        for (int bill : bills) {

            if (bill == 5) {
                five++;
            }

            else if (bill == 10) {
                // Need $5 change
                if (five == 0) {
                    return false;
                }

                five--;
                ten++;
            }

            else { // bill == 20

                // Need $15 change.
                // Prefer one $10 + one $5.
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                }

                // Otherwise use three $5 bills.
                else if (five >= 3) {
                    five -= 3;
                }

                else {
                    return false;
                }
            }
        }

        return true;
    }
}