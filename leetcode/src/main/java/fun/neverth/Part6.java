package fun.neverth;

import java.util.Arrays;
import java.util.PriorityQueue;

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

    public static void main(String[] args) {
        Part6 part6 = new Part6();
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        part6.missingNumber(a);
    }
}
