package com.rzaiats.leetcode;

//A string S of lowercase English letters is given. We want to partition this st
//ring into as many parts as possible so that each letter appears in at most one p
//art, and return a list of integers representing the size of these parts.
//
//
//
// Example 1:
//
//
//Input: S = "ababcbacadefegdehijhklij"
//Output: [9,7,8]
//Explanation:
//The partition is "ababcbaca", "defegde", "hijhklij".
//This is a partition so that each letter appears in at most one part.
//A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it split
//s S into less parts.
//
//
//
//
// Note:
//
//
// S will have length in range [1, 500].
// S will consist of lowercase English letters ('a' to 'z') only.
//
//
//
// Related Topics Two Pointers Greedy
// 👍 3586 👎 149


import sun.jvm.hotspot.utilities.Interval;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PartitionLabels_763 {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }

    static class Interval {
        int lowerBound;
        int upperBound;

        public Interval(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }

    static class Solution {
        public List<Integer> partitionLabels(String s) {
            LinkedList<Integer> result = new LinkedList<>();
            Map<Character, Interval> intervalsMap = new LinkedHashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);

                Interval interval = intervalsMap.get(character);

                if (interval != null) {
                    interval.upperBound = i;
                } else {
                    intervalsMap.put(character, new Interval(i, i));
                }
            }

            Interval currentInterval = null;

            for (Interval interval : intervalsMap.values()) {
                if (currentInterval == null) {
                    currentInterval = new Interval(interval.lowerBound, interval.upperBound);
                    continue;
                }

                if (interval.lowerBound > currentInterval.upperBound) {
                    result.add(currentInterval.upperBound - currentInterval.lowerBound + 1);

                    currentInterval = interval;
                } else if (interval.upperBound > currentInterval.upperBound) {
                    currentInterval.upperBound = interval.upperBound;
                }
            }

            if (currentInterval != null) {
                result.add(currentInterval.upperBound - currentInterval.lowerBound + 1);
            }


            return result;
        }
    }
}
