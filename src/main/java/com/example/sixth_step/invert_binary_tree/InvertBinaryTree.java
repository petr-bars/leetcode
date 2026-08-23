package com.example.sixth_step.invert_binary_tree;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import static com.example.sixth_step.utils.Utils.buildTree;
import static com.example.sixth_step.utils.Utils.treeToString;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});

//        System.out.println(treeToString(invertTree(root)));
//        System.out.println(treeToString(invertTreeQueue(root)));
        System.out.println(treeToString(invertTreeStack(root)));
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public static TreeNode invertTreeQueue(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            TreeNode temp = item.left;
            item.left = item.right;
            item.right = temp;

            if (item.left != null) {
                queue.offer(item.left);
            }
            if (item.right != null) {
                queue.offer(item.right);
            }
        }
        return root;
    }

    public static TreeNode invertTreeStack(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return root;
    }
}