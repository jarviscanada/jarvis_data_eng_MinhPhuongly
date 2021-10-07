package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-21c0a20c35eb4dff83811153968a1933
 */
public class EvenOddNumber {
    /**
     * Big-O: O(1)
     * Justification:it's a mathematical solution.
     * @param i
     * @return
     */
    public static String moduloChecker(int i){
        return i%2 == 0 ? "even" : "odd";
    }

    /**
     * Big-O: O(1)
     * Justification: using bitwise and OR operator
     * E.g. 4 in binary code = 100. 1 in binary code = 1
     *      When comparing them with OR operator. We have 101 as a result which equal 5
     *      This means the number is even because odd number will remain the same bitwise
     * @param i
     * @return
     */
    public static String bitwaseChecker(int i){
        return (i|1)>i? "even" : "odd";
    }
}
