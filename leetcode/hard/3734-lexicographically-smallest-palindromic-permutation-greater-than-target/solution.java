class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        int mid = -1;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;


        int[] halfCnt = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        char[] targetHalf = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            targetHalf[i] = target.charAt(i);
        }

        int[] remaining = halfCnt.clone();
        char[] half = new char[halfLen];

        boolean possibleEqual = true;

        for (int i = 0; i < halfLen; i++) {
            int x = targetHalf[i] - 'a';

            if (remaining[x] == 0) {
                possibleEqual = false;
                break;
            }

            half[i] = targetHalf[i];
            remaining[x]--;
        }

        if (possibleEqual) {
  
            String candidate = buildPalindrome(half, mid, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        for (int pos = halfLen - 1; pos >= 0; pos--) {

            int[] used = halfCnt.clone();

            boolean validPrefix = true;

            for (int i = 0; i < pos; i++) {
                int x = targetHalf[i] - 'a';

                if (used[x] == 0) {
                    validPrefix = false;
                    break;
                }

                used[x]--;
            }

            if (!validPrefix) {
                continue;
            }

            int current = targetHalf[pos] - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (used[c] == 0) {
                    continue;
                }

                char[] resultHalf = new char[halfLen];

                for (int i = 0; i < pos; i++) {
                    resultHalf[i] = targetHalf[i];
                }

                resultHalf[pos] = (char) ('a' + c);
                used[c]--;

                int idx = pos + 1;

                for (int ch = 0; ch < 26; ch++) {
                    while (used[ch] > 0) {
                        resultHalf[idx++] = (char) ('a' + ch);
                        used[ch]--;
                    }
                }

                String candidate = buildPalindrome(resultHalf, mid, n);

                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }
        }

        return "";
    }

    private String buildPalindrome(char[] half, int mid, int n) {
        StringBuilder sb = new StringBuilder(n);

        for (char c : half) {
            sb.append(c);
        }
        if ((n & 1) == 1) {
            sb.append((char) ('a' + mid));
        }
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
}