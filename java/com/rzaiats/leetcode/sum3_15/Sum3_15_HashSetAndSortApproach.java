package com.rzaiats.leetcode.sum3_15;

import java.util.*;

public class Sum3_15_HashSetAndSortApproach {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] params = {-1, 0, 1, 2, -1, -4};

        System.out.println(solution.threeSum(params));
    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {

            if (nums.length < 3) {
                return Collections.emptyList();
            }

            Arrays.sort(nums);

            Set<List<Integer>> result = new HashSet<>();

            for (int i = 0; i < nums.length; i++) {
                int singleNumber = nums[i];

                //avoid duplicates
                if (i > 0 && singleNumber == nums[i - 1]) {
                    continue;
                }

                //Do not go through positive numbers since it won't make a zero in sum
                if (singleNumber > 0) {
                    break;
                }

                List<List<Integer>> matches = twoSum(nums, i + 1, -singleNumber);

                matches.forEach(singleMatch -> {
                    List<Integer> list = new ArrayList<>(singleMatch);
                    list.add(singleNumber);

                    Collections.sort(list);

                    result.add(list);
                });
            }

            return new ArrayList<>(result);
        }

        public List<List<Integer>> twoSum(int[] nums, int startIndex, int sum) {
            Set<List<Integer>> result = new HashSet<>();

            Set<Integer> complementaryValues = new HashSet<>();

            for (int i = startIndex; i < nums.length; i++) {
                int singleNumber = nums[i];
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
