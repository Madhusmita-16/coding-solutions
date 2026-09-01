class Solution {
    public ArrayList<Integer> fibonacciNumbers(int n) {
        ArrayList<Integer> result = new ArrayList<>();

        result.add(0);

        if (n == 1) {
            return result;
        }

        result.add(1);

        for (int i = 2; i < n; i++) {
            result.add(result.get(i - 1) + result.get(i - 2));
        }

        return result;
    }
}