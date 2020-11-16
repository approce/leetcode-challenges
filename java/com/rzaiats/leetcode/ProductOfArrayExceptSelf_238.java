package com.rzaiats.leetcode;

//Given an array nums of n integers where n > 1, return an array output such tha
//t output[i] is equal to the product of all the elements of nums except nums[i].
//
//
// Example:
//
//
//Input:  [1,2,3,4]
//Output: [24,12,8,6]
//
//
// Constraint: It's guaranteed that the product of the elements of any prefix or
// suffix of the array (including the whole array) fits in a 32 bit integer.
//
// Note: Please solve it without division and in O(n).
//
// Follow up:
//Could you solve it with constant space complexity? (The output array does not
//count as extra space for the purpose of space complexity analysis.)
// Related Topics Array
// 👍 6004 👎 481

import java.util.Arrays;

/*
    Compute two arrays:
    - one leftProducts array where: arr[i] = arr[i-1] * arr[i-2]
    - one rightProducts array where: arr[i] = arr[i+1] * arr[i+2]
    then:
    result[i] = leftProducts[i-1] * rightProducts[i+1]

    Below implementation has O(1) memory complexity (reused input array and used answer array as a storage for intermediate data)
    Time complexity: O(N)
 */

public class ProductOfArrayExceptSelf_238 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().productExceptSelf(new int[]{3, 2, 6, 0})));
    }

    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] result = new int[nums.length];

            if (nums.length == 0) {
                return result;
            }

            result[0] = nums[0];

            //left products array which contains product of numbers on left side:
            for (int i = 1; i < nums.length; i++) {
                result[i] = nums[i] * result[i - 1];
            }

            int productOnRightSide = 1;
            for (int i = nums.length - 1; i >= 0; i--) {

                if (i != 0) {
                    result[i] = result[i - 1] * productOnRightSide;
                } else {
                    result[i] = productOnRightSide;
                }
                productOnRightSide = productOnRightSide * nums[i];
            }

            return result;
        }
    }
}
