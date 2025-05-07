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
    // Find kth closest value to target in Binary Search Tree LC 272




    public static TreeNode parse(Integer[] nums){
        TreeNode[] res = new TreeNode[nums.length];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != null){
                res[i] = new TreeNode(nums[i]);
            }
        }
        for(int i = 0; i < nums.length; i++){
            TreeNode cur = res[i];
            if(cur==null){
                continue;
            }
            TreeNode left = i*2+1< nums.length? res[i*2+1]:null;
            TreeNode right = i*2+2< nums.length? res[i*2+2]:null;
            cur.left = left;
            cur.right = right;
        }


        return res[0];
    }

    public static void main(String[] args){

    }
}
