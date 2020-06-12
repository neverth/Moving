package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/10 0:02
 */
public class Part3 {

    public static class ListNode {
        int val;
        Part2.ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 53. 最大子序和
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];

        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int value : dp) {
            res = Math.max(value, res);
        }
        return res;
    }

    /**
     * 面试题03. 剑指offer-数组中重复的数字-修改原数组
     */
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {

            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];

        nums[a] = nums[b];

        nums[b] = temp;
    }

    /**
     * 287. 寻找重复数 剑指offer-数组中重复的数字-不修改原数组
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;

        int left = 1, right = len - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int n = 0;
            for (int value : nums) {
                if (value <= mid) {
                    n++;
                }
            }

            if (n > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }

    /**
     * 442. 数组中重复的数据-技巧型
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                res.add(Math.abs(index + 1));
            }

            nums[index] = -nums[index];

        }

        return res;
    }

    /**
     * 217. 存在重复元素-哈希表解决,或者排序
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);

        for (int num : nums) {

            if (set.contains(num)) {
                return true;
            }
            set.add(num);

        }

        return false;
    }

    /**
     * 219. 存在重复元素 II-哈希表解决
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            if (map.get(nums[i]) != null && (i - map.get(nums[i])) <= k) {
                return true;
            }

            map.put(nums[i], i);
        }

        return false;
    }

    /**
     * 240. 搜索二维矩阵 II && 面试题04. 二维数组中的查找
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        int n = matrix.length;

        if (n == 0) {
            return false;
        }

        int m = matrix[0].length;

        int r = 0, c = m - 1;

        while (r < n && c >= 0) {

            if (matrix[r][c] == target) {
                return true;

            } else if (matrix[r][c] > target) {
                c--;

            } else {
                r++;
            }
        }

        return false;
    }


    /**
     * 面试题05. 替换空格
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 面试题 10.01. 合并排序的数组
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int k = m + n - 1, i = m - 1, j = n - 1;

        while (i >= 0 && j >= 0) {
            if (A[i] < B[j]) {
                A[k--] = B[j--];

            } else {
                A[k--] = A[i--];

            }
        }

        while (j >= 0) {
            A[k--] = B[j--];
        }
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();

        int[] a = {1, 3, 4, 2, 2};
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

        System.out.println(part3.searchMatrix(c, 5));

    }

}
