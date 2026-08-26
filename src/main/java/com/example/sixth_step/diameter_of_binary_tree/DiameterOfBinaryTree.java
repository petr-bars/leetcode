package com.example.sixth_step.diameter_of_binary_tree;

import com.example.sixth_step.TreeNode;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(diameterOfBinaryTree(root));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = {0};
        height(root, maxDiameter);
        return maxDiameter[0];
    }

    public static int height(TreeNode node, int[] maxDiameter) {
        if (node == null) {
            return 0;
        }
        int leftH = height(node.left, maxDiameter);
        int rightH = height(node.right, maxDiameter);
        int candidate = leftH + rightH;
        maxDiameter[0] = Math.max(maxDiameter[0], candidate);
        return 1 + Math.max(leftH, rightH);
    }
}
