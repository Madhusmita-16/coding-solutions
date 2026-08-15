class Solution extends SolBase {
    public int rand10() {

        while (true) {
            int row = rand7();
            int col = rand7();

            int num = (row - 1) * 7 + col; // 1 to 49

            // Accept only 1 to 40
            if (num <= 40) {
                return 1 + (num - 1) % 10;
            }
        }
    }
}