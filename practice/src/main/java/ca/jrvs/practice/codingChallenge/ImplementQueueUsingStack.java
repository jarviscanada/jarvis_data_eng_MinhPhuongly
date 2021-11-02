package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class ImplementQueueUsingStack <T>{
    private Stack<T> myQueue = new Stack<>();
    private Stack<T> temp = new Stack<>();
    private T tail;

    public void push(T value){
        if (myQueue.isEmpty()){
            tail = value;
        }
        myQueue.push(value);
    }

    public T pop(){
        if (temp.isEmpty()){
            while (!myQueue.isEmpty()){
                temp.push(myQueue.pop());
            }
        }
        return temp.pop();
    }

    public T peek(){
        if (!temp.isEmpty()){
            return temp.peek();
        }
        if (temp.isEmpty() && myQueue.isEmpty()){
            return null;
        }
        return tail;
    }

    public boolean isEmpty(){
        return myQueue.isEmpty() && temp.isEmpty();
    }
}
