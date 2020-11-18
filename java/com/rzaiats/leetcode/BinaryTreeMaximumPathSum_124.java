package com.rzaiats.leetcode;
//Given a non-empty binary tree, find the maximum path sum.
//
// For this problem, a path is defined as any node sequence from some starting n
//ode to any node in the tree along the parent-child connections. The path must co
//ntain at least one node and does not need to go through the root.
//
//
// Example 1:
//
//
//Input: root = [1,2,3]
//Output: 6
//
//
// Example 2:
//
//
//Input: root = [-10,9,20,null,null,15,7]
//Output: 42
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [0, 3 * 104].
// -1000 <= Node.val <= 1000
//
// Related Topics Tree Depth-first Search
// 👍 4661 👎 337

import com.rzaiats.leetcode.utils.TreeNode;
import com.rzaiats.leetcode.utils.TreeNodeCreator;

import java.util.Arrays;

public class BinaryTreeMaximumPathSum_124 {


    public static void main(String[] args) {
        System.out.println(new SimplifiedSolution().maxPathSum(TreeNodeCreator.create(Arrays.asList(-1, null, 9, -6, 3, null, null, null, -2))));
    }

    static class Solution {

        static class BubbleDTO {
            public int branchSum;
            public int maxValue;

            public BubbleDTO(int branchSum, int maxValue) {
                this.branchSum = branchSum;
                this.maxValue = maxValue;
            }
        }

        public BubbleDTO getSum(TreeNode node) {
            if (node == null) {
                return new BubbleDTO(0, 0);
            }

            BubbleDTO leftSumDTO = node.right != null ? getSum(node.right) : null;
            BubbleDTO rightSuDTO = node.left != null ? getSum(node.left) : null;

            int totalNodeSum = node.val;
            int maxvalue = node.val;
            int maxBranchSum = node.val;

            if (leftSumDTO != null) {
                maxvalue = Math.max(leftSumDTO.maxValue, maxvalue);

                totalNodeSum += leftSumDTO.branchSum;

                maxBranchSum = Math.max(maxBranchSum, node.val + leftSumDTO.branchSum);
            }

            if (rightSuDTO != null) {
                maxvalue = Math.max(rightSuDTO.maxValue, maxvalue);

                totalNodeSum += rightSuDTO.branchSum;

                maxBranchSum = Math.max(maxBranchSum, node.val + rightSuDTO.branchSum);
            }

            maxvalue = Math.max(maxvalue, totalNodeSum);
            maxvalue = Math.max(maxvalue, maxBranchSum);

            return new BubbleDTO(maxBranchSum, maxvalue);
        }

        public int maxPathSum(TreeNode root) {
            BubbleDTO bubbleDTO = getSum(root);

            return Math.max(bubbleDTO.branchSum, bubbleDTO.maxValue);
        }
    }


    /**
     * Use class field to store max value and update that value from recursion function
     */
    static class SimplifiedSolution {
        int maxValue = Integer.MIN_VALUE;

        public int getGain(TreeNode node) {
            if (node == null) {
                return 0;
            }

            int leftGain = Math.max(getGain(node.left), 0);
            int rightGain = Math.max(getGain(node.right), 0);

            maxValue = Math.max(maxValue, node.val + leftGain + rightGain);

            return node.val + Math.max(leftGain, rightGain);
        }

        public int maxPathSum(TreeNode root) {
            getGain(root);

            return maxValue;
        }
    }
}
