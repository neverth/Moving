package fun.neverth;

import com.sun.java.swing.plaf.windows.resources.windows;
import sun.security.util.ArrayUtil;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/5 23:30
 */
public class Part2 {

    /**
     * 567. 字符串的排列 ①
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;

        while (right < s2.length()) {

            char c1 = s2.charAt(right++);

            map.put(c1, map.getOrDefault(c1, 0) - 1);

            while (left < right && map.get(c1) < 0) {

                char c2 = s2.charAt(left++);

                map.put(c2, map.getOrDefault(c2, 0) + 1);

                if(right - left == s1.length()){
                    return true;
                }
            }
        }

        return right - left == s1.length();
    }

    /**
     * 567. 字符串的排列 ②
     */
    public boolean checkInclusion1(String s1, String s2) {
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        for (int i = 0; i < s1.length(); i++){
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while(right < s2.length()){
            char c1 = s2.charAt(right++);

            if(needs.containsKey(c1)){
                windows.put(c1, windows.getOrDefault(c1, 0) + 1);

                if(windows.get(c1).compareTo(needs.get(c1)) == 0){
                    count++;
                }
            }

            while(right - left >= s1.length()){

                if(count == needs.size()){
                    return true;
                }

                char c2 = s2.charAt(left++);

                if(needs.containsKey(c2)){
                    if(windows.get(c2).compareTo(needs.get(c2)) == 0){
                        count--;
                    }
                    windows.put(c2, windows.getOrDefault(c2, 0) - 1);

                }
            }
        }
        return false;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     */
    public List<Integer> findAnagrams(String s, String p) {

        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < p.length(); i++){
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while(right < s.length()){

            char c1 = s.charAt(right++);

            if(needs.containsKey(c1)){

                windows.put(c1, windows.getOrDefault(c1, 0) + 1);
                if(needs.get(c1).compareTo(windows.get(c1)) == 0){
                    count++;
                }
            }

            while(count == needs.size()){

                if(right - left == p.length()
//                        && !s.substring(left, right).equals(p) 字母异位词指字母相同，但排列不同的字符串 ？？？
                ){
                    res.add(left);
                }
                char c2 = s.charAt(left++);

                if(needs.containsKey(c2)){
                    if(needs.get(c2).compareTo(windows.get(c2)) == 0){
                        count--;
                    }
                    windows.put(c2, windows.getOrDefault(c2, 0) - 1);
                }
            }
        }

        return res;
    }

    /**
     * 3. 无重复字符的最长子串
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> windows = new HashMap<>();

        int left = 0, right = 0;
        int maxlen = 0;

        while(right < s.length()){

            char c1 = s.charAt(right++);

            windows.put(c1, windows.getOrDefault(c1, 0) + 1);

            while(windows.get(c1) > 1){

                char c2 = s.charAt(left++);

                windows.put(c2, windows.get(c2) - 1);
            }

            maxlen = Math.max(maxlen, right - left);

        }
        return maxlen;
    }


    /**
     * 146. LRU缓存机制
     */
    static class LRUCache {

        class Node{

            private int key, val;

            private Node prev, next;

            public Node(int k , int v){
                this.key = k;
                this.val = v;
            }
        }

        class DoubleList{

            private Node head, tail;

            private int size;

            public DoubleList() {
                this.head = new Node(0, 0);
                this.tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
            }

            public void addFirst(Node a){
                a.next = head.next;
                a.prev = head;
                a.next.prev = a;
                head.next = a;
                size++;
            }

            public void remove(Node a){
                a.prev.next = a.next;
                a.next.prev = a.prev;
                size--;
            }

            public Node removeLast(){

                if (tail.prev == head){
                    return null;
                }

                Node realTail = tail.prev;

                remove(realTail);

                return realTail;
            }

        }

        private Map<Integer, Node> map;

        private DoubleList doubleList;

        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            doubleList = new DoubleList();

        }

        public int get(int key) {

            if (!map.containsKey(key)){
                return -1;
            }

            int val = map.get(key).val;

            put(key, val);

            return val;
        }

        public void put(int key, int value) {

            Node node = new Node(key, value);

            if (map.containsKey(key)){
                doubleList.remove(map.get(key));

            } else{
                if(capacity == doubleList.size){

                    Node realTail = doubleList.removeLast();
                    map.remove(realTail.key);
                }
            }
            doubleList.addFirst(node);
            map.put(key, node);
        }
    }

    public int strStr(String haystack, String needle) {

        if (needle.length() == 0){
            return 0;
        }

        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < needle.length(); i++){
            needs.put(needle.charAt(i), needs.getOrDefault(needle.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        while(right < haystack.length()){

            char c1 = haystack.charAt(right++);

            if(needs.containsKey(c1)){

                windows.put(c1, windows.getOrDefault(c1, 0) + 1);
                if(needs.get(c1).compareTo(windows.get(c1)) == 0){
                    count++;
                }
            }

            while(count == needs.size()){

                if(right - left == needle.length()
                        && haystack.substring(left, right).equals(needle)
                ){
                    return left;
                }
                char c2 = haystack.charAt(left++);

                if(needs.containsKey(c2)){
                    if(needs.get(c2).compareTo(windows.get(c2)) == 0){
                        count--;
                    }
                    windows.put(c2, windows.getOrDefault(c2, 0) - 1);
                }
            }
        }

        return 0;
    }

    public int strStr1(String haystack, String needle) {

        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i < m - n + 1; i++) {
            if (haystack.substring(i, i + n).equals(needle)){
                return i;
            }
        }

        return -1;
    }

    public int strStr2(String haystack, String needle) {

        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i < m - n + 1; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)){
                    break;
                }
            }

            if (j == n){
                return i;
            }
        }

        return -1;
    }

    public int strStr3(String haystack, String needle) {
        int m = needle.length();

        int[][] dp = new int[m][256];

        int X = 0;

        dp[0][needle.charAt(0)] = 1;

        for (int i = 1; i < m; i++) {

            for (int j = 0; j < 256; j++) {

                dp[i][j] = dp[X][j];
            }

            dp[i][needle.charAt(i)] = i + 1;

            X = dp[X][needle.charAt(i)];
        }

        int s = 0;

        for (int i = 0; i < haystack.length(); i++) {

            s = dp[s][haystack.charAt(i)];

            if (s == m) {
                return i - m + 1;

            }
        }

        return -1;
    }

    /**
     * 773. 滑动谜题
     * bfs
     */
    public String slidingPuzzleSwap(String target, int left, int right){
        char[] c = target.toCharArray();

        char t = c[left];
        c[left] = c[right];
        c[right] = t;

        return String.copyValueOf(c);

    }

    public int slidingPuzzle(int[][] board) {

        StringBuilder sb = new StringBuilder();
        for(int[] i: board){
            for(int j: i){
                sb.append(j);
            }
        }

        List<List<Integer>> neighbor = new ArrayList<>();

        neighbor.add(Arrays.asList(1, 3));
        neighbor.add(Arrays.asList(0 ,4, 2));
        neighbor.add(Arrays.asList(1, 5));
        neighbor.add(Arrays.asList(0, 4));
        neighbor.add(Arrays.asList(3, 1, 5));
        neighbor.add(Arrays.asList(4, 2));

        String target = "123450";
        String start = sb.toString();

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(start);
        visited.add(start);

        int step = 0;
        while(!q.isEmpty()){

            int sz = q.size();

            for(int i = 0; i < sz; i++){
                String cur = q.poll();

                if(cur.equals(target)){
                    return step;
                }

                int zeroIndex = 0;
                while(cur.charAt(zeroIndex) != '0'){
                    zeroIndex++;
                }

                for(int j: neighbor.get(zeroIndex)){
                    String curSwap = slidingPuzzleSwap(cur, zeroIndex, j);
                    if(!visited.contains(curSwap)){
                        q.offer(curSwap);
                        visited.add(curSwap);
                    }
                }

            }
            step++;
        }
        return -1;
    }

    /**
     * 204. 计数质数
     */
    public int countPrimes(int n) {

        if(n < 2){
            return 0;
        }

        int count = 0;

        for(int i = 2; i < n; i++){

            boolean flag = true;

            for(int j = 2; j * j <= i; j++){
                if(i % j == 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                count++;
            }

        }
        return count;
    }

    public int countPrimes1(int n) {

        if(n < 2){
            return 0;
        }

        boolean[] isPrimes = new boolean[n];

        Arrays.fill(isPrimes, true);

        for (int i = 2; i * i <= n; i++) {
            if (isPrimes[i]){

                for (int j = i * i; j < n; j += i) {
                    isPrimes[j] = false;
                }

            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes[i]){
                count ++;
            }
            
        }

        return count;
    }

    /**
     * 7. 整数反转
     */
    public int reverse(int x) {

        int res = 0;

        while (x != 0){
            int pop = x % 10;

            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)){
                return 0;
            }

            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;

            }

            res = res * 10 + pop;

            x = x / 10;
        }

        return res;
    }

    /**
     * 875. 爱吃香蕉的珂珂
     */
    public int minEatingSpeed(int[] piles, int H) {

        int max = 0;
        for(int i: piles){
            if(i > max){
                max = i;
            }
        }

        int left = 1, right = max;

        while(left <= right){

            int mid = left + (right - left) / 2;

            int needTime = 0;

            for (int i : piles) {

                if (i % mid == 0) {
                    needTime += i / mid;

                } else {
                    needTime += i / mid + 1;

                }
            }
            if (needTime == H){
                return mid;

            }else if(needTime < H){
                right = mid - 1;

            }else{
                left = mid + 1;

            }
        }
        return right + 1;
    }

    /**
     * 1011. 在 D 天内送达包裹的能力
     */
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        int min = 0;
        for (int weight : weights) {
            sum += weight;
            min = Math.max(min, weight);
        }

        int left = min, right = sum;

        while(left <= right){

            int tempSum = 0;
            int needTime = 0;

            int mid = left + (right - left) / 2;

            for (int j = 0; j < weights.length; j++) {

                if(tempSum + weights[j] <= mid){
                    tempSum += weights[j];
                    if (j == weights.length - 1){
                        needTime ++;
                    }

                } else{
                    j --;
                    needTime ++;
                    tempSum = 0;
                }
            }

            if (needTime <= D){
                right = mid - 1;

            }else {
                left = mid + 1;
            }

        }

        return left;
    }

    /**
     * 42. 接雨水
     */
    public int trap(int[] height) {

        int len = height.length;
        int res = 0;

        int[] left_max = new int[len];
        int[] right_max = new int[len];

        left_max[0] = height[0];
        right_max[len - 1] = height[len - 1];

        for(int i = 1; i < len; i++){
            left_max[i] = Math.max(left_max[i - 1], height[i]);
        }

        for(int i = len - 2; i >= 0; i--){
            right_max[i] = Math.max(right_max[i + 1], height[i]);
        }

        for (int i = 1; i < len - 1; i++){
            res += Math.min(left_max[i], right_max[i]) - height[i];
        }

        return res;
    }

    public int trap1(int[] height) {

        int len = height.length;
        if(len == 0){
            return 0;
        }
        int res = 0;

        int left = 0, right = len - 1;

        int left_max = height[0];
        int right_max = height[len - 1];

        while(left <= right){

            left_max = Math.max(left_max, height[left]);
            right_max = Math.max(right_max, height[right]);

            if (left_max < right_max){
                res += left_max - height[left];
                left++;


            } else{
                res += right_max - height[left];
                right--;

            }
        }

        return res;
    }

    /**
     * 26. 删除排序数组中的重复项
     *
     * 方法太低效了吧，还花了这么多时间，真菜！
     */
    public int removeDuplicates(int[] nums) {
        int left = 0, right = 1;

        int len = nums.length;

        if (len < 2){
            return len;
        }

        int remainLen = len;

        int realLen = len;

        int temp = nums[0];
        while(right < realLen){

            remainLen = realLen;

            if(temp == nums[right]){
                left = right;
                remainLen -= left;

                while(nums[right] == temp){
                    right++;
                    remainLen--;
                    realLen--;

                    if (remainLen == 0){
                        break;
                    }

                }
                System.arraycopy(nums, right, nums, left, remainLen);
                right = left;
            }
            temp = nums[right++];
        }
        return realLen;
    }

    public int removeDuplicates1(int[] nums) {
        int len = nums.length;

        if (len < 2){
            return len;
        }

        int slow = 0, fast = 1;

        while(fast < len){

            if (nums[slow] != nums[fast]){

                slow++;
                nums[slow] = nums[fast];

            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * 80. 删除排序数组中的重复项 II
     */
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;

        int slow = 0, fast = 1;
        boolean isTwo = false;

        while(fast < len){

            if(nums[slow] != nums[fast] ){
                slow++;
                nums[slow] = nums[fast];
                isTwo = false;

            }else{
                if(!isTwo){
                    slow++;
                    nums[slow] = nums[fast];
                    isTwo = true;
                }
            }
            fast++;

        }
        return slow + 1;
    }

    /**
     * 80. 删除排序数组中的重复项 II
     *
     * 牛逼
     */
    public int removeDuplicates3(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]){
                nums[i++] = n;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        int[] a = {1,1,1,1,2,2,3};
        String[] b = {"0000"};

        int[][] c = new int[][]{{1, 2, 3}, {5, 4, 0}};


        Common.TreeNode n1 = new Common.TreeNode(3);
        Common.TreeNode n2 = new Common.TreeNode(9);
        Common.TreeNode n3 = new Common.TreeNode(20);
        Common.TreeNode n4 = new Common.TreeNode(15);
        Common.TreeNode n5 = new Common.TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.println(part2.removeDuplicates3(a));

    }
}
