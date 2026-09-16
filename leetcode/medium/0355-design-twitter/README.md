# Q2. Design Twitter

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the `10` most recent tweets in the user's news feed.

Implement the `Twitter` class:

- Twitter() Initializes your twitter object.
- void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
- List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
- void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
- void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.

 

 **Example 1:** 

```
Input
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
Output
[null, null, [5], null, null, [6, 5], null, [5]]

Explanation
Twitter twitter = new Twitter();
twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2);    // User 1 follows user 2.
twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2);  // User 1 unfollows user 2.
twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.

```

 

 **Constraints:** 

- 1 <= userId, followerId, followeeId <= 500
- 0 <= tweetId <= 104
- All the tweets have unique IDs.
- At most 3 * 104 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
- A user cannot follow himself.

## Solution

**Language:** Java  
**Runtime:** 29 ms (beats 91.83%)  
**Memory:** 56.3 MB (beats 72.89%)  
**Submitted:** 2026-09-16T02:52:04.403Z  

```java
import java.util.*;

class Twitter {

    private static class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    private final Map<Integer, List<Tweet>> tweets;
    private final Map<Integer, Set<Integer>> following;
    private int time;

    public Twitter() {
        tweets = new HashMap<>();
        following = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>())
              .add(new Tweet(tweetId, time++));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();

        PriorityQueue<Tweet> maxHeap =
            new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

        // User's own tweets
        addRecentTweets(userId, maxHeap);

        // Tweets from followed users
        Set<Integer> followees = following.get(userId);

        if (followees != null) {
            for (int followeeId : followees) {
                addRecentTweets(followeeId, maxHeap);
            }
        }

        // Get at most 10 most recent tweets
        while (!maxHeap.isEmpty() && result.size() < 10) {
            result.add(maxHeap.poll().tweetId);
        }

        return result;
    }

    private void addRecentTweets(int userId, PriorityQueue<Tweet> maxHeap) {
        List<Tweet> userTweets = tweets.get(userId);

        if (userTweets == null) {
            return;
        }

        /*
         * Only the 10 most recent tweets from each user are needed.
         * Any tweet older than the newest 10 tweets of the same user
         * cannot be among the global top 10.
         */
        int start = Math.max(0, userTweets.size() - 10);

        for (int i = start; i < userTweets.size(); i++) {
            maxHeap.offer(userTweets.get(i));
        }
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }

        following.computeIfAbsent(followerId, k -> new HashSet<>())
                 .add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followees = following.get(followerId);

        if (followees != null) {
            followees.remove(followeeId);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/design-twitter/)