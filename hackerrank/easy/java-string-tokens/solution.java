

    static boolean isAnagram(String a, String b) {
        
        a = a.toLowerCase();
        b = b.toLowerCase();

        if (a.length() != b.length()) {
            return false;
        }

        int[] frequency = new int[26];

        // Count characters in a
        for (int i = 0; i < a.length(); i++) {
            frequency[a.charAt(i) - 'a']++;
        }

        // Remove characters found in b
        for (int i = 0; i < b.length(); i++) {
            frequency[b.charAt(i) - 'a']--;
        }

        // Check if all frequencies are zero
        for (int i = 0; i < 26; i++) {
            if (frequency[i] != 0) {
                return false;
            }
        }

        return true;
    }

