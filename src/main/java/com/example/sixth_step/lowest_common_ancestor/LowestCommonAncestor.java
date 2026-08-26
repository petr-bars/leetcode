package com.example.sixth_step.lowest_common_ancestor;

import com.example.sixth_step.TreeNode;

import static com.example.sixth_step.utils.Utils.buildTree;
import static com.example.sixth_step.utils.Utils.treeToString;

/**
 * Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.
 * <p>
 * Recall that:
 * <p>
 * The node of a binary tree is a leaf if and only if it has no children
 * The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
 * The lowest common ancestor of a set S of nodes,
 * is the node A with the largest depth such that every node in S is in the subtree with root A.
 */
public class LowestCommonAncestor {
    static class Result {
        int depth;
        TreeNode node;

        public Result() {
        }

        public Result(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});

        System.out.println(treeToString(lcaDeepestLeaves(root)));
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) {
            return null;
        }
        Result result = dfs(root);
        return result.node;
    }

    private static Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(0, null);
        }
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        if (left.depth == right.depth) {
            return new Result(left.depth + 1, node);
        }
        if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.node);
        }
        return new Result(right.depth + 1, right.node);
    }
}
