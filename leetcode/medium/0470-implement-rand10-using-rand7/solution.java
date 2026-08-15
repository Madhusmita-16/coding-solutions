class Solution extends SolBase {
    public int rand10() {

        while (true) {
            int row = rand7();
            int col = rand7();

            int num = (row - 1) * 7 + col; // Uniformly 1..49

            if (num <= 40) {
                return 1 + (num - 1) % 10;
            }
        }
    }
}