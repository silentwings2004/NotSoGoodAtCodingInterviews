package S0_Templates;

public class RecursionOfTree {
    public ResultType divideConquer(TreeNode root) {
        // base case
        if (root == null) return root;
    }

    // deal with left and right subtree
    ResultType leftResult = divideConquer(root.left);
    ResultType rightResult = divideConquer(root.right);

    // merge the leftResult and rightResult with root
    ResultType res = merge leftResult and rightResult;
    return res;
}
