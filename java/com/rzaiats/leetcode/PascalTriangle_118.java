package com.rzaiats.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Given a non-negative integer numRows, generate the first numRows of Pascal's t
//riangle.
//
//
//In Pascal's triangle, each number is the sum of the two numbers directly above
// it.
//
// Example:
//
//
//Input: 5
//Output:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
//
// Related Topics Array
// 👍 1917 👎 122

public class PascalTriangle_118 {

    public static void main(String[] args) {
        System.out.println(new Solution().generate(5));
    }

    static class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<List<Integer>>(numRows);

            if (numRows == 0) {
                return Collections.emptyList();
            }

            result.add(Collections.singletonList(1));

            if (numRows == 1) {
                return result;
            }

            result.add(Arrays.asList(1, 1));

            if (numRows == 2) {
                return result;
            }

            for (int rowIndex = 2; rowIndex < numRows; rowIndex++) {
                List<Integer> previousRow = result.get(rowIndex - 1);

                ArrayList<Integer> row = new ArrayList<Integer>(numRows);
                result.add(row);
                row.add(1);

                for (int itemIndex = 1; itemIndex < rowIndex; itemIndex++) {
                    int value = previousRow.get(itemIndex - 1) + previousRow.get(itemIndex);
                    row.add(value);
                }

                row.add(1);
            }

            return result;
        }
    }
}

