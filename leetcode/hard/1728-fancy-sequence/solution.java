class Fancy {

    private static final long MOD = 1_000_000_007L;

    // Global transformation:
    // actualValue = storedValue * mul + add
    private long mul = 1;
    private long add = 0;

    private java.util.ArrayList<Long> sequence;

    public Fancy() {
        sequence = new java.util.ArrayList<>();
    }

    public void append(int val) {
        /*
         * We want:
         *
         * stored * mul + add = val
         *
         * Therefore:
         *
         * stored = (val - add) / mul
         *
         * Division modulo MOD is multiplication
         * by modular inverse.
         */
        long value = (val - add + MOD) % MOD;

        value = value * modPow(mul, MOD - 2) % MOD;

        sequence.add(value);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = mul * m % MOD;
        add = add * m % MOD;
    }

    public int getIndex(int idx) {
        if (idx < 0 || idx >= sequence.size()) {
            return -1;
        }

        long value = sequence.get(idx);

        long result = value * mul % MOD;
        result = (result + add) % MOD;

        return (int) result;
    }

    private long modPow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}