package com.rzaiats.leetcode;
//We are given a binary tree (with root node root), a target node, and an intege
//r value K.
//
// Return a list of the values of all nodes that have a distance K from the targ
//et node. The answer can be returned in any order.
//
//
//
//
//
//
//
// Example 1:
//
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//
//Output: [7,4,1]
//
//Explanation:
//The nodes that are a distance 2 from the target node (with value 5)
//have values 7, 4, and 1.
//
//
//
//Note that the inputs "root" and "target" are actually TreeNodes.
//The descriptions of the inputs above are just serializations of these objects.
//
//
//
//
//
// Note:
//
//
// The given tree is non-empty.
// Each node in the tree has unique values 0 <= node.val <= 500.
// The target node is a node in the tree.
// 0 <= K <= 1000.
//
//
// Related Topics Tree Depth-first Search Breadth-first Search
// 👍 2775 👎 56

import com.rzaiats.leetcode.utils.TreeNode;
import com.rzaiats.leetcode.utils.TreeNodeCreator;

import java.util.*;

public class AllNodesDistance_863 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNodeCreator.create(Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4));

        System.out.println(new Solution().distanceK(treeNode, new TreeNode(5), 1));
    }

    /**
     * Using sentinel(null) as a delimiter between depths
     */
    static class Solution {

        public List<Integer> distanceK(TreeNode root, TreeNode target, int depth) {
            if (depth == 0) {
                return Collections.singletonList(target.val);

            }
            Map<Integer, TreeNode> parentNodeMap = new HashMap<>();
            LinkedList<TreeNode> nodesQueue = new LinkedList<>();
            Set<Integer> seenNodes = new HashSet<>();
            Set<Integer> result = new HashSet<>();

            nodesQueue.add(root);

            TreeNode foundTargetNodeInTree = null;

            while (!nodesQueue.isEmpty()) {
                TreeNode node = nodesQueue.pop();

                if (node.val == target.val) {
                    foundTargetNodeInTree = node;
                }

                if (node.left != null) {
                    nodesQueue.add(node.left);
                    parentNodeMap.put(node.left.val, node);
                }
                if (node.right != null) {
                    nodesQueue.add(node.right);
                    parentNodeMap.put(node.right.val, node);
                }
            }

            seenNodes.add(foundTargetNodeInTree.val);

            TreeNode targetParentNode = parentNodeMap.get(foundTargetNodeInTree.val);

            if (targetParentNode != null) {
                nodesQueue.add(targetParentNode);
                seenNodes.add(targetParentNode.val);
            }

            if (foundTargetNodeInTree.left != null) {
                nodesQueue.add(foundTargetNodeInTree.left);
                seenNodes.add(foundTargetNodeInTree.left.val);
            }
            if (foundTargetNodeInTree.right != null) {
                nodesQueue.add(foundTargetNodeInTree.right);
                seenNodes.add(foundTargetNodeInTree.right.val);
            }

            nodesQueue.add(null);

            int currentDepth = 1;

            while (!nodesQueue.isEmpty() && nodesQueue.peek() != null) {
                TreeNode node = nodesQueue.pop();
                TreeNode parentNode = parentNodeMap.get(node.val);

                if (currentDepth == depth) {
                    result.add(node.val);
                    continue;
                }

                if (parentNode != null && !seenNodes.contains(parentNode.val)) {
                    nodesQueue.add(parentNode);
                    seenNodes.add(parentNode.val);
                }

                if (node.left != null && !seenNodes.contains(node.left.val)) {
                    nodesQueue.add(node.left);
                    seenNodes.add(node.left.val);
                }

                if (node.right != null && !seenNodes.contains(node.right.val)) {
                    nodesQueue.add(node.right);
                    seenNodes.add(node.right.val);
                }

                if (nodesQueue.peek() == null) {
                    currentDepth++;
                    //set sentinel to limit new discovered level:
                    nodesQueue.pop();
                    nodesQueue.add(null);
                }
            }

            return new ArrayList<>(result);
        }
    }
}
