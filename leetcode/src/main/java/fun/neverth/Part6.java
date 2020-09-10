package fun.neverth;

import java.util.*;

/**
 * @author neverth.li
 * @date 2020/9/9 17:43
 */
public class Part6 {
    static class MaxHeap {

        private final int[] data;

        public MaxHeap(int[] data) {
            this.data = data;
            buildHeap();
        }

        // 获取对中的最大的元素，根元素
        public int getRoot() {
            return data[0];
        }

        // 替换根元素，并重新堆调整
        public void setRoot(int root) {
            data[0] = root;
            heapify(0);
        }

        // 将数组转换成最大堆
        private void buildHeap() {
            // 根据性质，
            // 完全二叉树只有数组下标小于或等于 (data.length) / 2 - 1 的元素有孩子结点
            // 对有孩子结点的元素堆调整，从下往上调整
            for (int i = (data.length) / 2 - 1; i >= 0; i--) {
                heapify(i);
            }
        }

        private void heapify(int i) {
            // 获取左右结点的数组下标
            int l = ((i + 1) << 1) - 1;
            int r = (i + 1) << 1;
            // 临时变量，表示 跟结点、左结点、右结点中最大的值的结点的下标
            int max = i;
            // 左结点的值大于根结点的值
            if (l < data.length && data[l] > data[i]) {
                max = l;
            }
            // 右结点的值大于以上比较的较大值
            if (r < data.length && data[r] > data[max]) {
                max = r;
            }
            // 左右结点的值都小于根节点，直接return，不做任何操作
            if (i == max) {
                return;
            }
            // 交换根节点和左右结点中最大的那个值，把根节点的值替换下去
            swap(i, max);
            // 由于替换后左右子树会被影响，所以要对受影响的子树再进行堆调整
            heapify(max);
        }

        // 交换元素位置
        private void swap(int i, int j) {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] topArr = new int[k];
        // 构造长度为 K 的最大堆
        if (k >= 0) System.arraycopy(arr, 0, topArr, 0, k);
        MaxHeap maxHeap = new MaxHeap(topArr);
        // 遍历元素小于最大堆的时候替换元素并调整堆
        for (int i = k; i < arr.length; i++) {
            if (maxHeap.getRoot() > arr[i]) {
                maxHeap.setRoot(arr[i]);
            }
        }
        return topArr;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * <p>
     * 库函数
     */
    public int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        int v[] = new int[k];
        // 最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int j : arr) {
            pq.offer(j);
        }
        for (int i = 0; i < k; i++) {
            v[i] = pq.poll();
        }
        return v;
    }

    /**
     * 剑指 Offer 40. 最小的k个数
     * <p>
     * 快排
     */
    private void quickSearch(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        // 设置最左边的元素为key
        int key = nums[l];
        // 中间值
        int i = l, j = r;
        // 循环分类，左小右大
        while (i < j) {
            // 从右边找到一个比 key 小的数
            while (nums[j] >= key && i < j) {
                j--;
            }
            // 从左边找到一个比 key 大的数
            while (nums[i] <= key && i < j) {
                i++;
            }
            // 把比key小的数放左边，大的放右边
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        // 到这代表分类完成，将key插过去
        nums[l] = nums[i];
        nums[i] = key;
        // 已经找到以 k 中间值的数组，直接返回
        if (i == k) {
            return;
        }
        quickSearch(nums, l, i - 1, k);
        quickSearch(nums, i + 1, r, k);
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        quickSearch(arr, 0, arr.length - 1, k - 1);
        return Arrays.copyOf(arr, k);
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 找到target的右边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 如果数组中不存在对应target
        if (right >= 0 && nums[right] != target) {
            return 0;
        }
        int RIGHT = left;
        // 重新初始化
        left = 0;
        right = nums.length - 1;
        // 找到target的左边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int LEFT = right;
        return RIGHT - LEFT - 1;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     */
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 相等就去右区间中找
            if (nums[mid] == mid) {
                left = mid + 1;
            }
            // 不相等就去左区间中找
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
     */
    public int lengthOfLongestSubstring(String s) {
        // 用map保存当前窗口中存在的元素和对应出现的次数
        HashMap<Character, Integer> map = new HashMap<>();
        // 左指针，  右指针，  最大值
        int i = 0, j = 0, max = 0;
        // 当窗口没有到底时
        while (j < s.length()) {
            // 判断当前字符是否已经在窗口中存在
            // 存在次数++，不存在初始为1
            char c = s.charAt(j++);
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
            // 当字符存在次数大于一的时候，
            // 调整窗口左边界，直到元素出现次数为1
            while (map.get(c) > 1) {
                char c1 = s.charAt(i++);
                map.put(c1, map.get(c1) - 1);
            }
            // 更新最大值
            max = Math.max(max, j - i);
        }
        return max;
    }

    /**
     * [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/)
     */
    public void reverseString(char[] s) {
        // 双指针
        int i = 0, j = s.length - 1;
        // 两两交换
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i++;
            j--;
        }
    }

    /**
     * 64. 最小路径和
     */
    public int minPathSum(int[][] grid) {
        // 代表直到走到 (i,j)的最小路径和。
        // 可以共用grid，次吃是为了方便理解
        int[][] dp = new int[grid.length][grid[0].length];
        // 遍历路径
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 00的时候初始dp
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                }
                // 0*的时候只有一种路径
                else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                // *0的时候只有一种路径
                else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                }
                // 其他的ij才有可能有多中路径
                // 向下或者是向右走，选择最小值
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 434. 字符串中的单词数
     */
    public int countSegments(String s) {
        char[] chars = s.toCharArray();
        int len = 0;
        // 匹配第一个有效的字符
        boolean newWord = true;
        // 遍历
        for (char c : chars) {
            // 空格之后可能存在单词
            // 过滤掉连续的空格
            if (c == ' ') {
                newWord = true;
            }
            // 不是空格
            else {
                // 有效字符且之前是空格
                if (newWord) {
                    len += 1;
                    newWord = false;
                }
            }
        }
        return len;
    }

    /**
     * 39. 组合总和
     */
    List<List<Integer>> res39 = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, combine, 0);
        return res39;
    }

    private void dfs(int[] candidates, int target, List<Integer> combine, int idx) {
        // 到这数组尾且不符合条件
        if (idx == candidates.length) {
            return;
        }
        // 符合条件并记录结果
        if (target == 0) {
            res39.add(new ArrayList<>(combine));
            return;
        }
        // 选择不使用这个idx
        dfs(candidates, target, combine, idx + 1);
        // 选择使用这个idx，但是要满足题意
        if (target - candidates[idx] >= 0) {
            // 加上组合
            combine.add(candidates[idx]);
            // 递归搜索，因为可以选重复值，所以还是idx
            dfs(candidates, target - candidates[idx], combine, idx);
            // 取消组合
            combine.remove(combine.size() - 1);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 543. 二叉树的直径
     */
    int ans543;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans543;
    }

    private int dfs(TreeNode root) {
        // 已经到达叶子节点
        if (root.left == null && root.right == null) {
            return 0;
        }
        // 递归左节点并计算left最大深度
        int l = root.left == null ? 0 : dfs(root.left) + 1;
        // 递归右节点并计算right最大深度
        int r = root.right == null ? 0 : dfs(root.right) + 1;
        // 最大直径出现在一个节点的左右子树，所以将他们相加
        ans543 = Math.max(ans543, l + r);
        // 返回最长的深度
        return Math.max(l, r);
    }

    /**
     * 20. 有效的括号
     */
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        // 方便匹配
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        map.put('?', '?');
        // 不包含有效左字符
        if (!map.containsKey(s.charAt(0))) {
            return false;
        }
        LinkedList<Character> stack = new LinkedList<>();
        try {
            for (Character c : s.toCharArray()) {
                // 左字符直接入栈
                if (map.containsKey(c)) {
                    stack.push(c);
                }
                // 右字符需要跟栈顶成对
                else if (map.get(stack.pop()) != c) {
                    return false;
                }
            }
        } catch (Exception e) {
            // stack下标超出直接返回false
            return false;
        }
        return stack.size() == 0;
    }

    /**
     * 215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        quickSearch1(nums, 0, nums.length - 1, k - 1);
        return nums[k - 1];
    }

    private void quickSearch1(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int key = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (nums[j] <= key && i < j) {
                j--;
            }
            while (nums[i] >= key && i < j) {
                i++;
            }
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        nums[l] = nums[i];
        nums[i] = key;
        if (i == k) {
            return;
        } else if (i < k) {
            quickSearch1(nums, i + 1, r, k);
        } else {
            quickSearch1(nums, l, i - 1, k);
        }
    }

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        int[] a = {2, 3, 6, 7};
        char[] b = {'1', '2', '3', '4'};
        part6.isValid("(){}}{");
    }
}
