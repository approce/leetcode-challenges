package com.rzaiats.leetcode.design;

import java.util.Stack;

public class StackUsingQueues_232 {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
    }

    static class MyQueue {
        Stack<Integer> stack         = new Stack<>();
        Stack<Integer> reversedStack = new Stack<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            reverseStack();

            return reversedStack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            reverseStack();
            return reversedStack.peek();
        }

        private void reverseStack() {
            if (reversedStack.isEmpty()) {
                while (!stack.isEmpty()) {
                    reversedStack.push(stack.pop());
                }
            }
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return reversedStack.isEmpty() && stack.isEmpty();
        }
    }
}
