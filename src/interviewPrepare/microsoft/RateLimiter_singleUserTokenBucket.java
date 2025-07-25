package interviewPrepare.microsoft;

public class RateLimiter_singleUserTokenBucket {

    //Design a class RateLimiter that limits the number of requests that can be processed over time using the Token Bucket algorithm.
    //
    //You are given the following parameters:
    //
    //capacity: the maximum number of tokens the bucket can hold.
    //
    //refillRate: the number of tokens added to the bucket per second.
    //
    //Each request consumes one token. If no tokens are available when a request arrives, the request is rejected.
    static class singleUserRateLimiterToken{

        long curToken;
        int capacity;
        int refillRate;
        int lastRefillTime;
        public singleUserRateLimiterToken(int capacity, int refillRate) {
            // Initializes the limiter with a given token bucket capacity and refill rate.
            this.capacity = capacity;
            this.refillRate = refillRate;
            curToken = 0L;
            lastRefillTime = 0;
        }

        public boolean allowRequest(int timestamp) {
            // Returns true if a request at the given timestamp (in seconds) can be allowed.
            // Returns false if the bucket is empty (no tokens available).
            if(timestamp>lastRefillTime){
                curToken = Math.min(capacity, curToken + (long)(refillRate)*(timestamp-lastRefillTime));
                lastRefillTime = timestamp;
            }

            if(curToken > 0) {
                curToken--;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
        singleUserRateLimiterToken limiter = new singleUserRateLimiterToken(3, 1);  // capacity = 3, refill 1 token per second

        System.out.println(
        limiter.allowRequest(1)); // true (bucket has 3 → 2)
        System.out.println(limiter.curToken);
        System.out.println(
        limiter.allowRequest(2)); // true (1 token refilled → bucket = 2 → 1)
        System.out.println(limiter.curToken);
        System.out.println(
        limiter.allowRequest(3)); // true (1 token refilled → bucket = 2 → 1)
        System.out.println(limiter.curToken);
        System.out.println(
        limiter.allowRequest(3)); // true (bucket = 1 → 0)
        System.out.println(limiter.curToken);
        System.out.println(
        limiter.allowRequest(3)); // false (bucket empty)
        System.out.println(limiter.curToken);
        System.out.println(
        limiter.allowRequest(6)); // true (3 seconds passed → bucket = 3 → 2)
        System.out.println(limiter.curToken);
        System.out.println(
                limiter.allowRequest(6)); // true (3 seconds passed → bucket = 3 → 2)
        System.out.println(limiter.curToken);

    }
}
