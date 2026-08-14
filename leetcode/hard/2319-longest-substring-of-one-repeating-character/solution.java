class Solution {

    int[] pre;
    int[] suf;
    int[] maxLen;
    int[] length;
    char[] leftChar;
    char[] rightChar;
    char[] str;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        int n = s.length();

        str = s.toCharArray();

        int size = 4 * n;

        pre = new int[size];
        suf = new int[size];
        maxLen = new int[size];
        length = new int[size];

        leftChar = new char[size];
        rightChar = new char[size];

        // Build segment tree
        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            // Change character
            str[index] = ch;

            // Update segment tree
            update(1, 0, n - 1, index);

            // Root contains the answer
            ans[i] = maxLen[1];
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int l, int r) {

        // Store segment length
        length[node] = r - l + 1;

        // Leaf node
        if (l == r) {

            pre[node] = 1;
            suf[node] = 1;
            maxLen[node] = 1;

            leftChar[node] = str[l];
            rightChar[node] = str[l];

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    // Merge two child nodes
    private void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        // Boundary characters
        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        // Start with children's values
        pre[node] = pre[left];
        suf[node] = suf[right];

        maxLen[node] = Math.max(maxLen[left], maxLen[right]);

        // If characters at the boundary are equal
        if (rightChar[left] == leftChar[right]) {

            // Entire left segment has same character
            if (pre[left] == length[left]) {
                pre[node] = length[left] + pre[right];
            }

            // Entire right segment has same character
            if (suf[right] == length[right]) {
                suf[node] = suf[left] + length[right];
            }

            // Sequence crossing the middle
            maxLen[node] = Math.max(
                maxLen[node],
                suf[left] + pre[right]
            );
        }
    }

    // Point update
    private void update(int node, int l, int r, int index) {

        // Leaf node
        if (l == r) {

            pre[node] = 1;
            suf[node] = 1;
            maxLen[node] = 1;

            leftChar[node] = str[l];
            rightChar[node] = str[l];

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        // Recalculate current node
        merge(node);
    }
}