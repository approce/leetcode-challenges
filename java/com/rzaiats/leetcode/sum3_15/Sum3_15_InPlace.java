package com.rzaiats.leetcode.sum3_15;

import java.util.*;

/*
    In place implementation:
    1. No mutated(sorted) array argument
    2. No additional allocated memory for copied array

    Time complexity: O(N^2)
 */
public class Sum3_15_InPlace {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] params = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};

        System.out.println(solution.threeSum(params));
    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            if (nums.length < 3) {
                return Collections.emptyList();
            }

            Set<List<Integer>> result = new HashSet<>();

            Set<Integer> usedNumbers = new HashSet<>();

            for (int i = 0; i < nums.length; i++) {
                int singleNumber = nums[i];

                if (usedNumbers.contains(singleNumber)) {
                    continue;
                }

                List<List<Integer>> matches = twoSum(nums, i + 1, -singleNumber, usedNumbers);

                matches.forEach(singleMatch -> {
                    List<Integer> list = new ArrayList<>(singleMatch);
                    list.add(singleNumber);

                    Collections.sort(list);

                    result.add(list);
                });

                usedNumbers.add(singleNumber);
            }

            return new ArrayList<>(result);
        }

        public List<List<Integer>> twoSum(int[] nums, int startIndex, int sum, Set<Integer> usedNumbers) {
            Set<List<Integer>> result = new HashSet<>();

            Set<Integer> complementaryValues = new HashSet<>();

            for (int i = startIndex; i < nums.length; i++) {
                int singleNumber = nums[i];

                if (usedNumbers.contains(singleNumber)) {
                    continue;
                }

                int neededValue = sum - singleNumber;

                if (complementaryValues.contains(neededValue)) {
                    List<Integer> integers = Arrays.asList(singleNumber, neededValue);
                    Collections.sort(integers);

                    result.add(integers);
                }

                complementaryValues.add(singleNumber);
            }

            return new ArrayList<>(result);
        }
    }
}
