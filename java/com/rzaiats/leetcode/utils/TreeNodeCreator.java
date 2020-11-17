package com.rzaiats.leetcode.utils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class TreeNodeCreator {
    public static TreeNode create(List<Integer> listRepresentation) {
        if (listRepresentation.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Queue<TreeNode> queue = new ArrayDeque<>();

        TreeNode rootNode = new TreeNode(listRepresentation.get(0));
        queue.add(rootNode);

        for (int i = 1; i < listRepresentation.size(); i = i + 2) {
            Integer left = listRepresentation.get(i);
            Integer right = listRepresentation.get(i + 1);

            TreeNode currentNode = queue.poll();

            if (left != null) {
                TreeNode leftNode = new TreeNode(left);
                currentNode.left = leftNode;
                queue.add(leftNode);
            }

            if (right != null) {
                TreeNode rightNode = new TreeNode(right);
                currentNode.right = rightNode;
                queue.add(rightNode);

            }
        }

        return rootNode;
    }
}
