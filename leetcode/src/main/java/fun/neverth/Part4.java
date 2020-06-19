package fun.neverth;

import java.util.Arrays;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/19 13:56
 */
public class Part4 {

    /**
     * 复习 673. 最长递增子序列的个数
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];
        int[] combination = new int[len];

        Arrays.fill(dp, 1);
        Arrays.fill(combination, 1);

        int max = 1;

        for (int i = 0; i < len; i++){
            for (int j = 0; j < i; j++){
                if (nums[j] < nums[i]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        combination[i] = combination[j];

                    }else if (dp[j] + 1 == dp[i]){
                        combination[i] += combination[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++){
            if (dp[i] == max){
                res += combination[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();

        int[] a = {1,3,5,4,7};
        int[] a1 = {9, 15, 7, 20, 3};
        String[] b = {"0000"};

        int[][] c = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30},
        };


        Common.TreeNode n1 = new Common.TreeNode(3);
        Common.TreeNode n2 = new Common.TreeNode(9);
        Common.TreeNode n3 = new Common.TreeNode(20);
        Common.TreeNode n4 = new Common.TreeNode(15);
        Common.TreeNode n5 = new Common.TreeNode(7);

        Part2.ListNode l1 = new Part2.ListNode(1);
        Part2.ListNode l2 = new Part2.ListNode(2);
        Part2.ListNode l3 = new Part2.ListNode(3);
        Part2.ListNode l4 = new Part2.ListNode(4);
        Part2.ListNode l5 = new Part2.ListNode(3);

        l1.next = null;
        l2.next = null;
        l3.next = null;
        l4.next = null;

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(part4.findNumberOfLIS(a));

    }
}
