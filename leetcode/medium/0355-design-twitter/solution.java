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