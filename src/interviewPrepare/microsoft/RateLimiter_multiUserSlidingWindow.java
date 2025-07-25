package interviewPrepare.microsoft;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RateLimiter_multiUserSlidingWindow {
//    Design a class MultiUserRateLimiter that enforces a rate limit for multiple users using the Sliding Window algorithm.
//
//    Each user is identified by a unique string userId. Each request from a user has a timestamp (in seconds).
//    You should allow a request only if the number of requests made by that user in the past windowSize seconds is less than maxRequests.
//
//    Implement a method allowRequest(userId, timestamp) which returns:
//
//          true if the request is allowed
//
//          false if the user has reached the limit

    static class MultiUserRateLimiter {

        static class Node{
            int t;
            int c;
            public Node(int timestamp){
                t=timestamp;
                c = 1;
            }

            public void increase(){
                c++;
            }
        }
        Map<String, Deque<Node>> userHistory;
        Map<String, Integer> userCount;
        int window;
        int limit;
        public MultiUserRateLimiter(int windowSize, int maxRequests) {
            // Initializes the rate limiter with a sliding window of 'windowSize' seconds
            // and maximum 'maxRequests' allowed per user within that window.
            userHistory = new HashMap<>();
            userCount = new HashMap<>();
            window = windowSize;
            limit = maxRequests;
        }

        public boolean allowRequest(String userId, int timestamp) {
            // Returns true if the request from 'userId' at 'timestamp' (in seconds) is allowed.
            if(!userHistory.containsKey(userId)){
                userHistory.put(userId, new LinkedList<>());
                userHistory.get(userId).add(new Node(timestamp));
                userCount.put(userId, 1);
                return true;
            }else {
                Deque<Node> hist = userHistory.get(userId);
                int curCnt = userCount.get(userId);
                while (!hist.isEmpty() && hist.peekFirst().t < timestamp - window) {
                    Node drop = hist.pollFirst();
                    curCnt -= drop.c;
                }

                if (curCnt >= limit) {
                    userCount.put(userId, curCnt);
                    return false;
                }
                curCnt++;
                userCount.put(userId, curCnt);
                if(!hist.isEmpty() && hist.peekLast().t==timestamp){
                    hist.peekLast().increase();
                }else{
                    hist.add(new Node(timestamp));
                }
            }
            return true;
        }
    }

    public static void main(String[] args){
        MultiUserRateLimiter limiter = new MultiUserRateLimiter(60, 3); // 60s window, max 3 requests per user
        System.out.println(
        limiter.allowRequest("alice", 1));  // true

        System.out.println(
        limiter.allowRequest("alice", 10)); // true

        System.out.println(
        limiter.allowRequest("alice", 20)); // true

        System.out.println(
        limiter.allowRequest("alice", 30)); // false (already 3 requests in [1,30])

        System.out.println(
        limiter.allowRequest("alice", 70)); // true (only one request in [10,70])

        System.out.println(
        limiter.allowRequest("bob", 5));    // true (bob is independent from alice)


    }

}
