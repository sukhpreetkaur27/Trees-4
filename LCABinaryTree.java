import java.util.ArrayList;
import java.util.List;

// LC 236
public class LCABinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Find the path from root to p and q.
     * Compare both the paths to find the last common node which is the LCA.
     * <p>
     * TC: O(2N + H)
     * SC: O(3H)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        dfs(root, p, pathP);
        List<TreeNode> pathQ = new ArrayList<>();
        dfs(root, q, pathQ);
        return lca(pathP, pathQ);
    }

    private TreeNode lca(List<TreeNode> pathP, List<TreeNode> pathQ) {
        TreeNode lca = null;
        int len = Math.min(pathP.size(), pathQ.size());
        for (int i = 0; i < len; i++) {
            if (pathP.get(i) == pathQ.get(i)) {
                lca = pathP.get(i);
            } else {
                break;
            }
        }
        return lca;
    }

    private boolean dfs(TreeNode root, TreeNode p, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        int size = path.size();
        path.add(root);
        if (root == p) {
            return true;
        }
        boolean left = dfs(root.left, p, path);
        if (left) {
            return true;
        }
        boolean right = dfs(root.right, p, path);
        if (!right) {
            path.remove(size);
        }
        return right;
    }

    /**
     * Given that p and q exist in the tree.
     * There can be 2 scenarios:
     * a. root (any tree) can be the LCA, if left and right arms lead us to p and q
     * b. root can be p or q, and left or right arm can lead us to p or q --> root == LCA
     * NOTE: in this case, there is no need to proceed with Left or Right arms
     * <p>
     * c. if neither the root nor the left or right arms lead us to p and q --> LCA = null
     * <p>
     * So, if root == p || root == q --> return lca of current sub tree as root
     * <p>
     * now, if left lca == p && right lca == q --> return lca of current sub tree as root
     * <p>
     * TC: O(n)
     * SC: O(H) -> h == n for skewed trees
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lca_optimised(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lca_optimised(root.left, p, q);
        TreeNode right = lca_optimised(root.right, p, q);

        TreeNode lca = null;
        if (left != null && right != null) {
            lca = root;
        } else if (left != null) {
            lca = left;
        } else if (right != null) {
            lca = right;
        }
        return lca;
    }
}
