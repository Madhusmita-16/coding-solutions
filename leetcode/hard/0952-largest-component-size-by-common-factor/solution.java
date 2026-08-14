class Solution {

    public int largestComponentSize(int[] nums) {
        int n = nums.length;

        DSU dsu = new DSU(n);

        // prime factor -> index of the first number containing it
        int[] factorIndex = new int[100001];
        java.util.Arrays.fill(factorIndex, -1);

        for (int i = 0; i < n; i++) {

            int num = nums[i];

            for (int factor = 2; factor * factor <= num; factor++) {

                if (num % factor == 0) {

                    if (factorIndex[factor] == -1) {
                        factorIndex[factor] = i;
                    } else {
                        dsu.union(i, factorIndex[factor]);
                    }

                    // Remove all occurrences of this factor
                    while (num % factor == 0) {
                        num /= factor;
                    }
                }
            }

            // Remaining number is a prime factor
            if (num > 1) {
                if (factorIndex[num] == -1) {
                    factorIndex[num] = i;
                } else {
                    dsu.union(i, factorIndex[num]);
                }
            }
        }

        int answer = 1;

        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, dsu.size(i));
        }

        return answer;
    }

    // Disjoint Set Union
    static class DSU {

        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            // Union by size
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        int size(int x) {
            return size[find(x)];
        }
    }
}