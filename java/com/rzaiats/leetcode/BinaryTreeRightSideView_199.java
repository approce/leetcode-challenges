package com.rzaiats.leetcode;
//Given a binary tree, imagine yourself standing on the right side of it, return
// the values of the nodes you can see ordered from top to bottom.
//
// Example:
//
//
//Input: [1,2,3,null,5,null,4]
//Output: [1, 3, 4]
//Explanation:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// Related Topics Tree Depth-first Search Breadth-first Search
// 👍 2945 👎 165


import com.rzaiats.leetcode.utils.TreeNode;
import com.rzaiats.leetcode.utils.TreeNodeCreator;

import java.util.*;

public class BinaryTreeRightSideView_199 {

    public static void main(String[] args) {
        System.out.println(new DFSRecursiveSolution().rightSideView(TreeNodeCreator.create(Arrays.asList(1, 2, 3, null, 5, null, 4))));
    }


    /**
     * BFS Solution with additional queue which specifies node level
     */
    static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }

            LinkedList<TreeNode> nodesQueue = new LinkedList<>();
            LinkedList<Integer> levelsQueue = new LinkedList<>();
            List<Integer> result = new ArrayList<>();

            nodesQueue.add(root);
            levelsQueue.add(0);

            while (!nodesQueue.isEmpty()) {
                TreeNode currentNode = nodesQueue.pop();
                Integer currentLevel = levelsQueue.pop();

                if (currentLevel >= result.size()) {
                    result.add(currentNode.val);
                } else {
                    result.set(currentLevel, currentNode.val);
                }

                if (currentNode.left != null) {
                    nodesQueue.add(currentNode.left);
                    levelsQueue.add(currentLevel + 1);
                }

                if (currentNode.right != null) {
                    nodesQueue.add(currentNode.right);
                    levelsQueue.add(currentLevel + 1);
                }
            }


            return result;
        }
    }

    /**
     * BFS Solution with one queue & sentinel(null) as a delimiter between levels
     */
    static class SentinelSolution {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }

            LinkedList<TreeNode> queue = new LinkedList<TreeNode>() {{
                add(root);
                add(null);
            }};

            LinkedList<Integer> result = new LinkedList<>();

            while (!queue.isEmpty() && queue.peek() != null) {
                TreeNode node = queue.pop();

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }

                //end of level case:
                if (queue.peek() == null) {
                    result.add(node.val);
                    queue.pop();
                    queue.add(null);
                }

            }

            return result;
        }
    }

    /**
     * BFS Instead of using sentinel use level length instead
     */
    static class LengthSolution {
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return Collections.emptyList();
            }

            LinkedList<TreeNode> queue = new LinkedList<TreeNode>() {{
                add(root);
            }};

            LinkedList<Integer> result = new LinkedList<>();

            while (!queue.isEmpty()) {

                int currentLevelLength = queue.size();

                for (int i = 0; i < currentLevelLength; i++) {
                    TreeNode node = queue.pop();


                    if (i == currentLevelLength - 1) {
                        result.add(node.val);
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }

            return result;
        }
    }


    static class DFSRecursiveSolution {
        List<Integer> result = new LinkedList<>();

        void helper(TreeNode node, int level) {
            if (result.size() == level) {
                result.add(node.val);
            }

            if (node.right != null) {
                helper(node.right, level + 1);
            }

            if (node.left != null) {
                helper(node.left, level + 1);
            }
        }

        public List<Integer> rightSideView(TreeNode root) {
            helper(root, 0);

            return result;
        }
    }
}
