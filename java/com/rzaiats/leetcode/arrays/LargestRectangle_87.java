package com.rzaiats.leetcode.arrays;

import java.util.Stack;

public class LargestRectangle_87 {
    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{2, 0, 0, 6, 2, 3}));
    }

    static class Solution {
        public int largestRectangleArea(int[] heights) {
            Stack<Integer> stack = new Stack<>();

            int maxRange = 0;

            for (int i = 0; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    int height = heights[stack.pop()];

                    int currentRange;
                    if (!stack.isEmpty()) {
                        currentRange = height * (i - stack.peek() - 1);
                    } else {
                        currentRange = height * (i);
                    }

                    maxRange = Integer.max(maxRange, currentRange);
                }

                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer index = stack.pop();
                int height = heights[index];

                int currentRange;
                if (!stack.isEmpty()) {
                    currentRange = height * (heights.length - stack.peek() - 1);
                } else {
                    currentRange = height * (heights.length);
                }

                maxRange = Integer.max(maxRange, currentRange);
            }

            return maxRange;
        }
    }
}
