# Q3. Process Restricted Friend Requests

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer `n` indicating the number of people in a network. Each person is labeled from `0` to `n - 1`.

You are also given a  **0-indexed**  2D integer array `restrictions`, where `restrictions[i] = [xi, yi]` means that person `xi` and person `yi`  **cannot** become  **friends**, either  **directly**  or  **indirectly**  through other people.

Initially, no one is friends with each other. You are given a list of friend requests as a  **0-indexed**  2D integer array `requests`, where `requests[j] = [uj, vj]` is a friend request between person `uj` and person `vj`.

A friend request is  **successful** if `uj` and `vj` can be  **friends**. Each friend request is processed in the given order (i.e., `requests[j]` occurs before `requests[j + 1]`), and upon a successful request, `uj` and `vj`  **become direct friends**  for all future friend requests.

Return  *a  **boolean array*** `result`, *where each* `result[j]` *is* `true` *if the* `jth` *friend request is  **successful**  or* `false` *if it is not*.

 **Note:**  If `uj` and `vj` are already direct friends, the request is still  **successful**.

 

 **Example 1:** 

```
Input: n = 3, restrictions = [[0,1]], requests = [[0,2],[2,1]]
Output: [true,false]
Explanation:
Request 0: Person 0 and person 2 can be friends, so they become direct friends. 
Request 1: Person 2 and person 1 cannot be friends since person 0 and person 1 would be indirect friends (1--2--0).

```

 **Example 2:** 

```
Input: n = 3, restrictions = [[0,1]], requests = [[1,2],[0,2]]
Output: [true,false]
Explanation:
Request 0: Person 1 and person 2 can be friends, so they become direct friends.
Request 1: Person 0 and person 2 cannot be friends since person 0 and person 1 would be indirect friends (0--2--1).

```

 **Example 3:** 

```
Input: n = 5, restrictions = [[0,1],[1,2],[2,3]], requests = [[0,4],[1,2],[3,1],[3,4]]
Output: [true,false,true,false]
Explanation:
Request 0: Person 0 and person 4 can be friends, so they become direct friends.
Request 1: Person 1 and person 2 cannot be friends since they are directly restricted.
Request 2: Person 3 and person 1 can be friends, so they become direct friends.
Request 3: Person 3 and person 4 cannot be friends since person 0 and person 1 would be indirect friends (0--4--3--1).

```

 

 **Constraints:** 

- 2 <= n <= 1000
- 0 <= restrictions.length <= 1000
- restrictions[i].length == 2
- 0 <= xi, yi <= n - 1
- xi != yi
- 1 <= requests.length <= 1000
- requests[j].length == 2
- 0 <= uj, vj <= n - 1
- uj != vj

## Solution

**Language:** Java  
**Runtime:** 65 ms (beats 58.33%)  
**Memory:** 47.3 MB (beats 10.42%)  
**Submitted:** 2026-08-14T16:47:59.412Z  

```java
class Solution {
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {

        DSU dsu = new DSU(n);

        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {

            int u = requests[i][0];
            int v = requests[i][1];

            int rootU = dsu.find(u);
            int rootV = dsu.find(v);

            // Already in the same group
            if (rootU == rootV) {
                result[i] = true;
                continue;
            }

            boolean allowed = true;

            // Check every restriction
            for (int[] restriction : restrictions) {

                int a = restriction[0];
                int b = restriction[1];

                int rootA = dsu.find(a);
                int rootB = dsu.find(b);

                // If merging u and v would put restricted
                // people into the same connected component
                if ((rootA == rootU && rootB == rootV) ||
                    (rootA == rootV && rootB == rootU)) {

                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                dsu.union(rootU, rootV);
                result[i] = true;
            } else {
                result[i] = false;
            }
        }

        return result;
    }

    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
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

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/process-restricted-friend-requests/)