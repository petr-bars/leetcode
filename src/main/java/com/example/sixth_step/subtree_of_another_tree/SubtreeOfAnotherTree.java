package com.example.sixth_step.subtree_of_another_tree;

import com.example.sixth_step.TreeNode;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the roots of two binary trees root and subRoot,
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * <p>
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree tree could also be considered as a subtree of itself.
 */
public class SubtreeOfAnotherTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{3, 4, 5, 1, 2});
        TreeNode subRoot = buildTree(new Integer[]{4, 1, 2});

        System.out.println(isSubtree(root, subRoot));
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        boolean rootLeft = isSubtree(root.left, subRoot);
        boolean rootRight = isSubtree(root.right, subRoot);
        return rootLeft || rootRight;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean sameLeftPQ = isSameTree(p.left, q.left);
        boolean sameRightPQ = isSameTree(p.right, q.right);
        return sameLeftPQ && sameRightPQ;
    }
}
