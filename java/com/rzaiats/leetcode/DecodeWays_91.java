package com.rzaiats.leetcode;

//A message containing letters from A-Z is being encoded to numbers using the fo
//llowing mapping:
//
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//
//
// Given a non-empty string containing only digits, determine the total number o
//f ways to decode it.
//
// The answer is guaranteed to fit in a 32-bit integer.
//
//
// Example 1:
//
//
//Input: s = "12"
//Output: 2
//Explanation: It could be decoded as "AB" (1 2) or "L" (12).
//
//
// Example 2:
//
//
//Input: s = "226"
//Output: 3
//Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
//.
//
//
// Example 3:
//
//
//Input: s = "0"
//Output: 0
//Explanation: There is no character that is mapped to a number starting with '0
//'. We cannot ignore a zero when we face it while decoding. So, each '0' should b
//e part of "10" --> 'J' or "20" --> 'T'.
//
//
// Example 4:
//
//
//Input: s = "1"
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 100
// s contains only digits and may contain leading zero(s).
//
// Related Topics String Dynamic Programming
// 👍 3423 👎 3219


public class DecodeWays_91 {

    public static void main(String[] args) {
        System.out.println(new Solution().numDecodings("1010"));
    }

    static class Solution {
        public int numDecodings(String s) {
            if (s == null || s.startsWith("0")) {
                return 0;
            }

            if (s.equals("10") || s.equals("20")) {
                return 1;
            }

            int prevSum = 1;
            int currentSum = 1;

            for (int i = 1; i < s.length(); i++) {
                int previousNumber = s.charAt(i - 1) - '0';
                int currentNumber = s.charAt(i) - '0';

                if (currentNumber == 0) {
                    if (previousNumber == 0 || previousNumber > 2) {
                        return 0;
                    }
                }

                if (currentNumber == 0) {
                    if (prevSum != currentSum) {
                        currentSum = prevSum;
                    }
                    continue;
                }

                boolean isLegibleTwoDigitEncodedCharacter = previousNumber == 1 || (previousNumber == 2 && currentNumber < 7);

                if (previousNumber == 0) {
                    currentSum = prevSum;
                } else if (isLegibleTwoDigitEncodedCharacter) {
                    //new combination found:
                    int buf = prevSum;
                    prevSum = currentSum;
                    currentSum = currentSum + buf;
                } else {
                    prevSum = currentSum;
                }
            }

            return currentSum;
        }
    }
}
