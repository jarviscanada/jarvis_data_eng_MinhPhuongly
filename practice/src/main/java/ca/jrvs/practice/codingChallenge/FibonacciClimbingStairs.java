package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-1a0af4733081426a8faa68048ee904d8
 */
public class FibonacciClimbingStairs {
    /**
     * Big-O:
     *      time: O(2^n)
     *      space: O(n)
     * Justification:
     *      time: T(n) = T(n-1) + T(n-2) = T(n-2) + T(n-3) + T(n-3) + T(n-4) ... =2^n-1 = O(2^n)-O(1)=O(2^n)
     *      space: while waiting for the preceding values, it takes up to an n amount of space
     * @param n
     * @return
     */
    public static int fib(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        return fib(n-1)+fib(n-2);
    }

    /**
     * Big-O:
     *      Time: O(n)
     *      Space: O(1)
     * Justification:
     *      Time: loop n amount of time
     *      Space: no dynamic space required
     * @param n
     * @return
     */
    public static int fibDP(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;

        int preOne= 0;
        int preTwo=1;
        int result=1;

        for(int i=2;i<=n;i++){
            result = preOne + preTwo;
            preOne=preTwo;
            preTwo=result;
        }
        return result;
    }

    public static int stairsClimbing(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;

        int preOne= 0;
        int preTwo=1;
        int result=1;

        for(int i=2;i<=n;i++){
            result = preOne + preTwo;
            preOne=preTwo;
            preTwo=result;
        }
        return result;
    }
}
