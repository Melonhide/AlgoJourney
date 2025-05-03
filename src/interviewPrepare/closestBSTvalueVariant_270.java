package interviewPrepare;

public class closestBSTvalueVariant_270 {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    // Variant 1:
    // Only allow integer target(Too easy, pass);

    // Variant 2:
    // Find kth closest value to target in Binary Search Tree
    public static int kThClosestBSTValue(TreeNode root, int target, int k){
        return 0;
    }
}
