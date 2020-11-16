package com.rzaiats.leetcode;

import java.util.Stack;

public class MergeIntervals_56 {

    public static void main(String[] args) {
        System.out.println(new Solution().decodeString("asd3[4[w]]"));
    }

    static class Solution {

        public String decodeString(String s) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);

                if (character == ']') {

                    StringBuilder stringBuilder = new StringBuilder();

                    while (!stack.isEmpty() && stack.peek() != '[') {
                        stringBuilder.insert(0, stack.pop());
                    }

                    //flush '[':
                    stack.pop();

                    StringBuilder integerBuilder = new StringBuilder();

                    while (!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9') {
                        integerBuilder.insert(0, stack.pop());
                    }

                    int times = Integer.parseInt(integerBuilder.toString());

                    String buf = stringBuilder.toString();

                    for (int j = 1; j < times; j++) {
                        stringBuilder.append(buf);
                    }

                    for (char c : stringBuilder.toString().toCharArray()) {
                        stack.push(c);
                    }

                } else {
                    stack.push(character);
                }
            }

            StringBuilder result = new StringBuilder();
            while (!stack.isEmpty()) {
                result.insert(0, stack.pop());
            }

            return result.toString();
        }
    }
}
