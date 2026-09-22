class Solution {
    int k;
    int n;
    int[][] cnt;
    int[] product;

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int rem = nums[l] % k;
            product[node] = rem;
            cnt[node][rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node);
    }

    void merge(int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        product[node] = (product[left] * product[right]) % k;

        for (int i = 0; i < k; i++) {
            cnt[node][i] = cnt[left][i];
        }

        for (int i = 0; i < k; i++) {
            int rem = (product[left] * i) % k;
            cnt[node][rem] += cnt[right][i];
        }
    }

    void update(int node, int l, int r, int idx, int value) {
        if (l == r) {
            cnt[node] = new int[k];

            int rem = value % k;
            product[node] = rem;
            cnt[node][rem] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, value);
        }

        merge(node);
    }

    int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            int[] res = new int[k + 1];

            res[0] = product[node];

            for (int i = 0; i < k; i++) {
                res[i + 1] = cnt[node][i];
            }

            return res;
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        int[] a = query(node * 2, l, mid, ql, qr);
        int[] b = query(node * 2 + 1, mid + 1, r, ql, qr);

        int[] res = new int[k + 1];

        int leftProduct = a[0];
        int rightProduct = b[0];

        res[0] = (leftProduct * rightProduct) % k;

        for (int i = 0; i < k; i++) {
            res[i + 1] = a[i + 1];
        }

        for (int i = 0; i < k; i++) {
            int rem = (leftProduct * i) % k;
            res[rem + 1] += b[i + 1];
        }

        return res;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;

        cnt = new int[4 * n][k];
        product = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            int[] res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res[x + 1];
        }

        return ans;
    }
}