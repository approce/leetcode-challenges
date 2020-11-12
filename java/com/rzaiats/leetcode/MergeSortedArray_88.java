package com.rzaiats.leetcode;

import java.util.Arrays;

//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one
// sorted array.
//
// Note:
//
//
// The number of elements initialized in nums1 and nums2 are m and n respectivel
//y.
// You may assume that nums1 has enough space (size that is equal to m + n) to h
//old additional elements from nums2.
//
//
// Example:
//
//
//Input:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//Output: [1,2,2,3,5,6]
//
//
//
// Constraints:
//
//
// -10^9 <= nums1[i], nums2[i] <= 10^9
// nums1.length == m + n
// nums2.length == n
//
// Related Topics Array Two Pointers
// 👍 2835 👎 4611

public class MergeSortedArray_88 {
    public static void main(String[] args) {
        int[] nums1 = {0, 0, 3, 0, 0, 0, 0, 0, 0};
        int[] nums2 = {-1, 1, 1, 1, 2, 3};
        new SolutionFromBack().merge(nums1, 3, nums2, 6);

        System.out.println(Arrays.toString(nums1));
    }

    //with additional memory
    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] nums1_copy = new int[m];
            System.arraycopy(nums1, 0, nums1_copy, 0, m);

            int p1 = 0;
            int p2 = 0;

            while (p1 < m && p2 < n) {
                nums1[p1 + p2] = nums1_copy[p1] < nums2[p2] ? nums1_copy[p1++] : nums2[p2++];
            }

            while (p1 < m) {
                nums1[p1 + p2] = nums1_copy[p1++];
            }

            while (p2 < n) {
                nums1[p1 + p2] = nums2[p2++];
            }
        }
    }

    //no need for additional memory allocation. Optimal solution
    static class SolutionFromBack {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;
            int p = m + n - 1;

            while (p >= 0 && p1 >= 0 && p2 >= 0) {

                if (nums1[p1] > nums2[p2]) {
                    nums1[p--] = nums1[p1--];
                } else {
                    nums1[p--] = nums2[p2--];
                }
            }

            while (p1 >= 0) {
                nums1[p--] = nums1[p1--];
            }

            while (p2 >= 0) {
                nums1[p--] = nums2[p2--];
            }
        }
    }
}
