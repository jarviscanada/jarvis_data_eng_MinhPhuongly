package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;

/**
 * Ticket: https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-bc1556658783400680ae692a34679a34
 * @param <T>
 */
public class ImplementStackUsingQueue <T>{
    private LinkedList<T> stack = new LinkedList<>();
    private T top;

    /**
     * Big-O: O(n)
     * Justification: remove the head and add back to tail to maintain Stack structure
     * @param value
     */
    public void push(T value){
        top = value;
        int length = stack.size();
        stack.add(top);
        while(length>0){
            stack.add(stack.remove());
            length--;
        }
    }

    /**
     * Big-O: O(1)
     * Justification: direct access
     * @return
     */
    public T top(){
        return top;
    }
    /**
     * Big-O: O(1)
     * Justification: direct access
     * @return
     */
    public T pop(){
        T temp = stack.poll();
        top = stack.peek();
        return temp;
    }
    /**
     * Big-O: O(1)
     * Justification: direct access
     * @return
     */
    public boolean isEmpty(){
        return stack.isEmpty();
    }
}
