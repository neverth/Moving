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
     * 牛客 [编程题]字符串变形
     * <p>
     * TM什么垃圾case
     */
    public String trans(String string, int n) {
        Stack<String> stack = new Stack<>();
        Arrays.stream(string.split(" ")).forEach(stack::push);

        StringBuilder sb = new StringBuilder();

        for (int i = string.length() - 1; i >= 0; i--) {
            if (string.charAt(i) == ' ') {
                sb.append(" ");
            } else {
                break;
            }
        }
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            for (int i = 0; i < pop.length(); i++) {
                char c = pop.charAt(i);
                if ('a' <= c && c <= 'z') {
                    sb.append((char) (c - (char) 32));
                } else {
                    sb.append((char) (c + 32));
                }
            }
            sb.append(" ");
        }

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                sb.append(" ");
            } else {
                break;
            }
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * 415. 字符串相加
     *
     * 双指针倒叙
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        // 两个num的尾指针 和 此时相加的进位
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        // 转为char数组
        char[] char1 = num1.toCharArray();
        char[] char2 = num2.toCharArray();
        // 当有一个num没有处理完时
        while(i >= 0 || j >= 0){
            // 两数相加
            int n1 = i >= 0 ? char1[i] - '0' : 0;
            int n2 = j >= 0 ? char2[j] - '0' : 0;
            // 两数相加并加上进位
            int tmp = n1 + n2 + carry;
            // 保存当前相加的进位
            carry = tmp / 10;
            // 保存结果
            sb.append(tmp % 10);
            // 处理下一位
            i--;
            j--;
        }
        // 再次处理进位
        if (carry == 1){
            sb.append(1);
        }
        // 因为结果是倒叙存储，因此需要翻转
        return sb.reverse().toString();
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     *
     * 单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0){
            return new int[0];
        }
        // 单调队列要使用双向链表实现
        Deque<Integer> deque = new LinkedList<>();
        // 结果数组
        int[] res = new int[nums.length - k + 1];
        // 首先让长度为 K 的窗口充满元素
        for (int i = 0; i < k; i++) {
            // 构造单调栈，将队列前面比元素小的全给清除掉，保证当调调递增
            // 你可以想象，加入数字的大小代表人的体重，把前面体重不足的都压扁了，直到遇到更大的量级才停住。
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.removeLast();
            }
            deque.offer(nums[i]);
        }
        // 获取第一个值
        res[0] = deque.peek();
        // 形成窗口之后
        for (int i = k; i < nums.length; i++) {
            // 因为在够着单调栈的时候，相对小的元素已经被弹出
            // 我们想删除的队头元素 n 可能已经被「压扁」了，这时候就不用删除了
            if (deque.peek() == nums[i - k]){
                deque.poll();
            }
            // 构造单调栈，将队列前面比元素小的全给清除掉
            // 保证当调调递增
            while (!deque.isEmpty() && deque.peekLast() < nums[i]){
                deque.removeLast();
            }
            deque.offer(nums[i]);
            // 获取对应窗口最大值
            res[i - k + 1] = deque.peek();
        }
        return res;
    }

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        int[] a = {1,3,-1,-3,5,3,6,7};
        part6.maxSlidingWindow(a, 3);
    }
}
