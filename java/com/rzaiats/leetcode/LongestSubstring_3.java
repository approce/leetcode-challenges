package com.rzaiats.leetcode;

import java.util.HashMap;
import java.util.Map;

//Given a string s, find the length of the longest substring without repeating c
//haracters.
//
//
// Example 1:
//
//
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
//
//
// Example 2:
//
//
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
//
//
// Example 3:
//
//
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a
//substring.
//
//
// Example 4:
//
//
//Input: s = ""
//Output: 0
//
//
//
// Constraints:
//
//
// 0 <= s.length <= 5 * 104
// s consists of English letters, digits, symbols and spaces.
//
// Related Topics Hash Table Two Pointers String Sliding Window
// 👍 11616 👎 633
public class LongestSubstring_3 {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("dvdf"));
    }

    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int longestStreak = 0;

            char[] chars = s.toCharArray();
            Map<Character, Integer> map = new HashMap<Character, Integer>();

            int pointer1 = 0;
            for (int pointer2 = 0; pointer2 < chars.length; pointer2++) {

                char singleChar = chars[pointer2];
                Integer index = map.get(singleChar);

                if (index != null && index >= pointer1) {


                    int currentStreak = pointer2 - pointer1;

                    if (currentStreak > longestStreak) {
                        longestStreak = currentStreak;
                    }

                    pointer1 = index + 1;

                    map.remove(singleChar);
                }

                map.put(singleChar, pointer2);
            }


            int currentStreak = chars.length - pointer1;

            if (currentStreak > longestStreak) {
                return currentStreak;
            } else {
                return longestStreak;
            }
        }
    }
}
