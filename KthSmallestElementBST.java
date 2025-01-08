// LC 230

/**
 * NOTE: InOrder traversal of a BST == sorted order
 * Maintain a counter to count for the k-th element reached during this traversal.
 */
public class KthSmallestElementBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * TC: O(n)
     * SC: O(H)
     *
     * @param root
     * @param k
     * @param ans
     */
    private void kthSmallest_inorder_dfs(TreeNode root, int[] k, TreeNode[] ans) {
        if (root == null || k[0] == 0) {
            return;
        }
        kthSmallest_inorder_dfs(root.left, k, ans);
        k[0]--;
        if (k[0] == 0) {
            ans[0] = root;
        }
        kthSmallest_inorder_dfs(root.right, k, ans);
    }

    /**
     * TC: O(n)
     * SC: O(1)
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest_morrisTraversal(TreeNode root, int k) {
        TreeNode[] ans = new TreeNode[1];
        TreeNode curr = root;
        while (curr != null && k >= 0) {
            TreeNode left = curr.left;
            if (left != null) {
                TreeNode right = left;
                while (right.right != null && right.right != curr) {
                    right = right.right;
                }
                if (right.right == null) {
                    right.right = curr;
                    curr = left;
                } else {
                    k--;
                    if (k == 0) {
                        ans[0] = curr;
                    }
                    right.right = null;
                    curr = curr.right;
                }
            } else {
                k--;
                if (k == 0) {
                    ans[0] = curr;
                }
                curr = curr.right;
            }
        }
        return ans[0].val;
    }
}
