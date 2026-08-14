# Q2. Restore IP Addresses

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A  **valid IP address**  consists of exactly four integers separated by single dots. Each integer is between `0` and `255` (**inclusive**) and cannot have leading zeros.

- For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.

Given a string `s` containing only digits, return  *all possible valid IP addresses that can be formed by inserting dots into* `s`. You are  **not**  allowed to reorder or remove any digits in `s`. You may return the valid IP addresses in  **any**  order.

 

 **Example 1:** 

```
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]

```

 **Example 2:** 

```
Input: s = "0000"
Output: ["0.0.0.0"]

```

 **Example 3:** 

```
Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

```

 

 **Constraints:** 

- 1 <= s.length <= 20
- s consists of digits only.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.85%)  
**Memory:** 43.5 MB (beats 90.83%)  
**Submitted:** 2026-08-14T15:53:35.645Z  

```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(String s, int index, int parts,
                            StringBuilder current,
                            List<String> result) {

        // Four parts created
        if (parts == 4) {
            if (index == s.length()) {
                result.add(current.toString());
            }
            return;
        }

        // Try segment lengths 1, 2, and 3
        for (int len = 1; len <= 3; len++) {

            if (index + len > s.length()) {
                break;
            }

            String part = s.substring(index, index + len);

            // Leading zero is not allowed
            if (part.length() > 1 && part.charAt(0) == '0') {
                continue;
            }

            // Value must be <= 255
            int value = Integer.parseInt(part);

            if (value > 255) {
                continue;
            }

            // Add dot before every part except the first
            int oldLength = current.length();

            if (parts > 0) {
                current.append('.');
            }

            current.append(part);

            backtrack(s, index + len, parts + 1, current, result);

            // Backtrack
            current.setLength(oldLength);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/restore-ip-addresses/)