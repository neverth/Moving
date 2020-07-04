package fun.neverth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 56. 合并区间
     */
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int index = -1;

        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (index == -1 || interval[0] > res[index][1]) {
                res[++index] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }
    /**
     * LeetCode US
     * 408. Valid Word Abbreviation
     */
    public boolean valid(String word, String abbr) {
        int m = word.length(), n = abbr.length();

        //双指针，i 是指源字符串， j 是指缩写字符串
        int i = 0, j = 0;

        while (i < m && j < n) {
            // 如果缩写字符串的 j 位置是数字
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {

                // 如果缩写字符串的 j 位置为0则直接返回false
                if (abbr.charAt(j) == '0') {
                    return false;
                }

                // 计算数字的大小
                int value = 0;
                while (j < n && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    value = value * 10 + abbr.charAt(j++) - '0';

                }

                // 跳过源字符串中的 value 个字符
                i += value;

            } else {
                // 不相等，直接返回false
                if (word.charAt(i++) != abbr.charAt(j++)) {
                    return false;

                }
            }
        }

        return i == m && j == n;
    }

    /**
     * 1. 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < len; i++){

            int b = target - nums[i];

            if(map.containsKey(b)){
                return new int[]{i, map.get(b)};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
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
