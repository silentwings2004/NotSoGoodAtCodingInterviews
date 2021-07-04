package S16_InterviewQuestionsI;
import java.util.*;
public class LargestSmallerBinarySearchTreeValue {
    /**
     * Largest Smaller Binary Search Tree Value
     * @param root
     * @param target
     * @return
     */
    public int findLargestSmaller(TreeNode root, double target) {
        // corner case
        if (root == null) throw new IllegalArgumentException();
        int closest = Integer.MIN_VALUE;
        while (root != null) {
            if (root.val < target) {
                if (root.val > closest) closest = root.val;
                root = root.right;
            } else root = root.left;
        }
        return closest;
    }
}
