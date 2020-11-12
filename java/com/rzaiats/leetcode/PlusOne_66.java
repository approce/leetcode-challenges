package com.rzaiats.leetcode;

import java.util.Arrays;

//Given a non-empty array of decimal digits representing a non-negative integer,
// increment one to the integer.
//
// The digits are stored such that the most significant digit is at the head of
//the list, and each element in the array contains a single digit.
//
// You may assume the integer does not contain any leading zero, except the numb
//er 0 itself.
//
//
// Example 1:
//
//
//Input: digits = [1,2,3]
//Output: [1,2,4]
//Explanation: The array represents the integer 123.
//
//
// Example 2:
//
//
//Input: digits = [4,3,2,1]
//Output: [4,3,2,2]
//Explanation: The array represents the integer 4321.
//
//
// Example 3:
//
//
//Input: digits = [0]
//Output: [1]
//
//
//
// Constraints:
//
//
// 1 <= digits.length <= 100
// 0 <= digits[i] <= 9
//
// Related Topics Array
// 👍 1875 👎 2710


public class PlusOne_66 {

    public static void main(String[] args) {
        int[] nums1 = {9, 9, 9, 9};

        System.out.println(Arrays.toString(new Solution().plusOne(nums1)));
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            int carry = 1;

            int index = 0;

            int[] resultReversed = new int[digits.length];

            do {
                int digit = digits[digits.length - 1 - index];

                int result = digit + carry;

                if (result > 9) {
                    result = result % 10;
                    carry = 1;
                } else {
                    carry = 0;
                }

                resultReversed[index] = result;

                index++;

            } while (index < digits.length);


            for (int i = 0; i < resultReversed.length / 2; i++) {
                int buf = resultReversed[i];
                resultReversed[i] = resultReversed[resultReversed.length - 1 - i];
                resultReversed[resultReversed.length - 1 - i] = buf;
            }

            if (carry != 0) {
                int[] overflowedResult = new int[digits.length + 1];

                overflowedResult[0] = 1;

                for (int i = 0; i < resultReversed.length; i++) {
                    overflowedResult[i + 1] = resultReversed[i];
                }

                return overflowedResult;
            } else {
                return resultReversed;
            }
        }
    }
}
