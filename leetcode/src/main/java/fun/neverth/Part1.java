package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/5 23:04
 */
public class Part1 {
    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        int length1 = t1.length;
        int length2 = t2.length;

        int[][] dp = new int[length1][length2];

        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }

                if (t1[i] == t2[j]) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }

                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

                }
            }
        }
        return dp[length1 - 1][length2 - 1];
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len];
        int[] combination = new int[len];

        Arrays.fill(dp, 1);
        Arrays.fill(combination, 1);

        int max = 1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        combination[i] = combination[j];

                    } else if (dp[j] + 1 == dp[i]) {
                        combination[i] += combination[j];
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        int res = 0;
        for (int value : combination) {
            if (value == max) {
                res += value;
            }
        }
        return res;
    }

    public int findLengthOfLCIS(int[] nums) {
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

    public int coinChange(int[] coins, int amount) {

        if (amount < 0 || coins.length == 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {

                if (i - coin < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public int change(int amount, int[] coins) {

        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int value : coins) {

            for (int i = 1; i <= amount; i++) {

                if (i - value >= 0) {
                    dp[i] = dp[i] + dp[i - value];
                }
            }
        }

        return dp[amount];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int len = nums.length;

        for (int value : nums) {
            sum += value;
        }

        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;

        boolean[][] dp = new boolean[len + 1][sum + 1];

        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];

                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i - 1]];
                }
            }
        }


        return dp[len][sum];

    }

    public void printDp(int[][] dp) {

        for (int[] ints : dp) {
            for (int anInt : ints) {

                System.out.print(anInt);
                System.out.print('\t');
            }
            System.out.println();
        }
        System.out.println("------------------");
    }

    public void printDp(int[] dp) {

        for (int ints : dp) {
            System.out.print(ints);
            System.out.print('\t');
        }
        System.out.println("------------------");
    }

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];

                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j - 1],
                            Math.min(
                                    dp[i][j - 1] + 1,
                                    dp[i - 1][j] + 1
                            )
                    );
                }
            }
        }
        return dp[len1][len2];
    }

    public int superEggDrop(int K, int N) {

        int[][] dp = new int[K + 1][N + 1];

        for (int[] t : dp) {
            Arrays.fill(t, N + 1);
        }

        for (int i = 0; i <= K; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }
        dp[0][1] = 0;

        for (int i = 0; i <= N; i++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }

        for (int i = 2; i <= N; i++) {

            for (int j = 2; j <= K; j++) {

//                for(int k = 1; k <= i; k++){
//
//                    dp[j][i] = Math.min(
//                            dp[j][i],
//                            Math.max(
//                                    dp[j - 1][k - 1],
//                                    dp[j][i - k]
//                            ) + 1
//                    );
//                }

                int left = 1;
                int right = i;

                while (left < right) {

                    int mid = left + (right - left + 1) / 2;

                    int breakCount = dp[j - 1][mid - 1];
                    int notBreakCount = dp[j][i - mid];

                    if (breakCount > notBreakCount) {

                        right = mid - 1;
                    } else {

                        left = mid;
                    }
                }
                dp[j][i] = Math.max(dp[j - 1][left - 1], dp[j][i - left]) + 1;
            }
        }

        return dp[K][N];

    }

    public int searchInsert(int[] nums, int target) {

        int len = nums.length;
        int left = 0, right = len - 1;
        int mid = -1;
        while (left <= right) {

            mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                return mid;

            } else if (nums[mid] > target) {

                right = mid - 1;

            } else if (nums[mid] < target) {

                left = mid + 1;
            }

        }

        return left;
    }


    public String[] permutation(String S) {
        char[] chars = S.toCharArray();

        List<Character> track = new ArrayList<>();

        List<String> res = new ArrayList<>();

        backTrack(res, chars, track);

        String[] newRes = new String[res.size()];

        for (int i = 0; i < res.size(); i++) {
            newRes[i] = res.get(i);
        }

        return newRes;

    }

    private void backTrack(List<String> res, char[] chars, List<Character> track) {

        if (chars.length == track.size()) {
            System.out.println(track);

            StringBuilder sb = new StringBuilder();
            for (char t : track) {
                sb.append(t);
            }

            res.add(sb.toString());
        }

        for (char aChar : chars) {

            if (track.contains(aChar)) {
                continue;
            }

            track.add(aChar);

            backTrack(res, chars, track);

            track.remove(track.size() - 1);

        }
    }

    public List<String> generateParenthesis(int n) {
        List<Character> track = new ArrayList<>();
        List<String> res = new ArrayList<>();

        backTrack(res, n, n, track);

        return res;
    }

    private void backTrack(List<String> res, int left, int right, List<Character> track) {

        if (left < 0 || right < 0) {
            return;
        }

        if (right < left) {
            return;
        }

        if (left == 0 && right == 0) {
            StringBuilder sb = new StringBuilder();
            for (char t : track) {
                sb.append(t);
            }
            res.add(sb.toString());
        }

        track.add('(');
        backTrack(res, left - 1, right, track);
        track.remove(track.size() - 1);

        track.add(')');
        backTrack(res, left, right - 1, track);
        track.remove(track.size() - 1);

    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();
        backTrack(res, nums, 0, track);
        return res;
    }


    private void backTrack(List<List<Integer>> res, int[] nums, int start, List<Integer> track) {

        res.add(new ArrayList<>(track));

        for (int i = start; i < nums.length; i++) {

            track.add(nums[i]);

            backTrack(res, nums, i + 1, track);

            track.remove(track.size() - 1);

        }

    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();
        backTrack(res, n, k, 1, track);
        return res;
    }

    private void backTrack(List<List<Integer>> res, int n, int k, int start, List<Integer> track) {

        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i <= n; i++) {

            track.add(i);

            backTrack(res, n, k, i + 1, track);

            track.remove(track.size() - 1);
        }

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> track = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);

        backTrack(res, nums, used, track);
        return res;
    }

    public void backTrack(List<List<Integer>> res, int[] nums, boolean[] used, List<Integer> track) {

        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }

            track.add(nums[i]);

            used[i] = true;

            backTrack(res, nums, used, track);

            track.remove(track.size() - 1);

            used[i] = false;

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

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {

                TreeNode cur = q.poll();

                if (cur.left != null) {
                    q.offer(cur.left);
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                }


            }
            depth++;
        }
        return depth;
    }

    public int openLock(String[] deadends, String target) {

        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9'){
            ch[j] = '0';

        }
        else{
            ch[j] += 1;

        }
        return new String(ch);
    }

    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0'){
            ch[j] = '9';

        }
        else{
            ch[j] -= 1;

        }
        return new String(ch);
    }

    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, len = nums.length, right = len - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {

                return new int[]{left, right};

            } else if (sum < target) {

                left++;
            } else if (sum > target) {

                right--;
            }
        }
        return new int[]{-1, -1};
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     *
     * @author neverth
     * @date 2020/8/24 21:27
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        // 双指针
        ListNode slow = head, fast = head;
        // 快指针先后倒数的k个
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // 快指针到达尾部之后，慢指针指的就是倒数第K个
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public int findDuplicate(int[] nums) {

        int slow = 0, fast = 0;

        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate1(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int c = 0;
            for (int value : nums) {
                if (value <= mid) {
                    c++;
                }
            }

            if (c > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public String minWindow(String s, String t) {

        int left, right, count, minLen = Integer.MAX_VALUE;
        int start = 0, end = 0;

        //needs存储t的<字符,出现次数>,windows存储<s中与t中字符相同的字符,出现次数>
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();

        //初始化needs
        for (int i = 0; i < t.length(); i++) {
            //needs.getOrDefault(t.charAt(i),0)+1 含义是：needs如果包含t.charAt(i)，
            //则取出值+1;不包含取0+1
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        left = right = count = 0;
        while (right < s.length()) {
            //获取字符
            char temp = s.charAt(right);
            //如果是t中字符，在windows里添加，累计出现次数
            if (needs.containsKey(temp)) {
                windows.put(temp, windows.getOrDefault(temp, 0) + 1);
                //注意：Integer不能用==比较，要用compareTo
                if (windows.get(temp).compareTo(needs.get(temp)) == 0) {
                    //字符temp出现次数符合要求，count代表符合要求的字符个数
                    count++;
                }
            }
            //优化到不满足情况，right继续前进找可行解
            right++;
            //符合要求的字符个数正好是t中所有字符，获得一个可行解
            while (count == needs.size()) {
                //更新结果
                if (right - left < minLen) {
                    start = left;
                    end = right;
                    minLen = end - left;
                }
                //开始进行优化，即缩小区间，删除s[left],
                char c = s.charAt(left);

                //当前删除的字符包含于t,更新Windows中对应出现的次数，如果更新后的次数<t中出现的次数，符合要求的字符数减1，下次判断count==needs.size()时不满足情况，
                if (needs.containsKey(c)) {
                    windows.put(c, windows.getOrDefault(c, 1) - 1);
                    if (windows.get(c) < needs.get(c)) {
                        count--;
                    }
                }
                left++;
            }

        }
        //返回覆盖的最小串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);

    }

    public String minWindow1(String s, String t) {
        Map<Character, Integer> windows = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        for (int i = 0; i < t.length(); i++){
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int count = 0;

        int start = 0, len = Integer.MAX_VALUE;

        while(right < s.length()){
            char c1 = s.charAt(right);

            if(needs.containsKey(c1)){

                windows.put(c1, windows.getOrDefault(c1, 0) + 1);

                if(windows.get(c1).compareTo(needs.get(c1)) == 0){
                    count++;
                }
            }

            right++;

            while(count == needs.size()){
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char c2 = s.charAt(left);

                if (needs.containsKey(c2)) {
                    windows.put(c2, windows.getOrDefault(c2, 1) - 1);
                    if (windows.get(c2).compareTo(needs.get(c2)) < 0) {
                        count--;
                    }
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        Part1 test1 = new Part1();
        int[] a = {1, 3, 4, 2, 2};
        String[] b = {"0000"};
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        System.out.println(test1.minWindow1("ADOBECODEBANC", "ABC"));
    }
}
