package com.example.sixth_step.binary_tree_level_order_traversal;

import com.example.sixth_step.TreeNode;

import java.util.*;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{3, 9, 20, null, null, 15, 7});

        System.out.println(levelOrder(root));
        System.out.println(levelOrderRec(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelValues = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode node = queue.poll();
                levelValues.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(levelValues);
        }
        return result;
    }

    public static List<List<Integer>> levelOrderRec(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, 0, result);
        return result;
    }

    private static void dfs(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        dfs(node.left, level + 1, result);
        dfs(node.right, level + 1, result);
    }
}
