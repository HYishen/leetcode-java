package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/。
 * </pre>
 *
 * @author Yishen 844147804@qq.com
 * @version 1.00.00
 */
public class ZhongJianErChaShuLcof {

    /**
     * 前序遍历数组内容 = [root, [left tree], [right tree]]
     * 中序序遍历数组内容 = [[left tree], root, [right tree]]
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        Map<Integer, Integer> inOrderIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndex.put(inorder[i], i);
        }

        return doBuildTree(preorder, inorder, inOrderIndex, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode doBuildTree(int[] preorder, int[] inorder, Map<Integer, Integer> inOrderIndex, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }

        int rootValue = preorder[preLeft];

        TreeNode root = new TreeNode(rootValue);

        int inOrderRootIndex = inOrderIndex.get(rootValue);
        int leftTreeLength = inOrderRootIndex - inLeft;

        root.left = doBuildTree(preorder, inorder, inOrderIndex, preLeft + 1, preLeft + leftTreeLength, inLeft, inOrderRootIndex - 1);
        root.right = doBuildTree(preorder, inorder, inOrderIndex, preLeft + leftTreeLength + 1, preRight, inOrderRootIndex + 1, inRight);

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
