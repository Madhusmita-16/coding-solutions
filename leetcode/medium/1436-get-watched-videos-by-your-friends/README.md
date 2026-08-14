# Q2. Get Watched Videos by Your Friends

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

There are `n` people, each person has a unique  *id*  between `0` and `n-1`. Given the arrays `watchedVideos` and `friends`, where `watchedVideos[i]` and `friends[i]` contain the list of watched videos and the list of friends respectively for the person with `id = i`.

Level  **1**  of videos are all watched videos by your friends, level  **2**  of videos are all watched videos by the friends of your friends and so on. In general, the level `k` of videos are all watched videos by people with the shortest path  **exactly**  equal to `k` with you. Given your `id` and the `level` of videos, return the list of videos ordered by their frequencies (increasing). For videos with the same frequency order them alphabetically from least to greatest. 

 

 **Example 1:** 

```
Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 1
Output: ["B","C"] 
Explanation: 
You have id = 0 (green color in the figure) and your friends are (yellow color in the figure):
Person with id = 1 -> watchedVideos = ["C"] 
Person with id = 2 -> watchedVideos = ["B","C"] 
The frequencies of watchedVideos by your friends are: 
B -> 1 
C -> 2

```

 **Example 2:** 

```
Input: watchedVideos = [["A","B"],["C"],["B","C"],["D"]], friends = [[1,2],[0,3],[0,3],[1,2]], id = 0, level = 2
Output: ["D"]
Explanation: 
You have id = 0 (green color in the figure) and the only friend of your friends is the person with id = 3 (yellow color in the figure).

```

 

 **Constraints:** 

- n == watchedVideos.length == friends.length
- 2 <= n <= 100
- 1 <= watchedVideos[i].length <= 100
- 1 <= watchedVideos[i][j].length <= 8
- 0 <= friends[i].length < n
- 0 <= friends[i][j] < n
- 0 <= id < n
- 1 <= level < n
- if friends[i] contains j, then friends[j] contains i

## Solution

**Language:** Java  
**Runtime:** 36 ms (beats 63.90%)  
**Memory:** 47.7 MB (beats 59.61%)  
**Submitted:** 2026-08-14T17:17:22.428Z  

```java
import java.util.*;

class Solution {
    public List<String> watchedVideosByFriends(
            List<List<String>> watchedVideos,
            int[][] friends,
            int id,
            int level) {

        int n = friends.length;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(id);
        visited[id] = true;

        int currentLevel = 0;

        // BFS until we reach the required level
        while (!queue.isEmpty() && currentLevel < level) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int person = queue.poll();

                for (int friend : friends[person]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }

            currentLevel++;
        }

        // Count videos of people at the required level
        Map<String, Integer> frequency = new HashMap<>();

        while (!queue.isEmpty()) {
            int person = queue.poll();

            for (String video : watchedVideos.get(person)) {
                frequency.put(
                    video,
                    frequency.getOrDefault(video, 0) + 1
                );
            }
        }

        // Sort by frequency, then alphabetically
        List<String> result = new ArrayList<>(frequency.keySet());

        result.sort((a, b) -> {
            int freqCompare =
                    Integer.compare(frequency.get(a), frequency.get(b));

            if (freqCompare != 0) {
                return freqCompare;
            }

            return a.compareTo(b);
        });

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/get-watched-videos-by-your-friends/)