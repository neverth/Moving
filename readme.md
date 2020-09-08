## MOVING

* 剑指offer刷题总结
* leetCode刷题总结
* 笔试题目总结

## 套路总结

### 二分查找套路

通过找到一个中值，每次结果都会在范围的左边或者是右边，一次就将范围缩小为一半，然后再将范围缩小为一半的一半，直到找到结果。

```java
// 时间复杂度O(logn)
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;

    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}
```

### 回溯算法、深度优先算法（DFS）套路

可以理解为暴力走所有路径。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。

```java
result = []
void dfsTaoLu() {
    dfs(路径， 选择列表);
}
void dfs(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        dfs(路径, 选择列表)
        撤销选择
```

### 广度优先算法（BFS）套路

原理是遍历队列里面的每个节点并再将遍历节点周围的结点入队，这样一圈一圈的处理就是广度优先遍历。

BFS 相对 DFS 的最主要的区别是：BFS 找到的路径一定是最短的，但代价就是空间复杂度比 DFS 大很多，因为要使用队列。

```java
int BFS(Node start, Node target) {
    // 创建一个队列用于保存一个节点周围的所有节点
    Queue<Node> q; 
	// 将起点加入队列
    q.offer(start); 
    while (!q.isEmpyt()) {
        Node cur = q.poll();
        // 将节点周围的节点入队
        q.offer(x);
    }
}
```

### 动态规划套路

```java

```



## 剑指offer刷题总结

**淦**！！！他瞄的太难受了，🤬🤬🤬自己，敲过几遍的题目面试的还是还是还是敲不出来。

为了代码简洁，省略了一些异常处理。



### 03. 数组中重复的数字

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```

限制：

```
2 <= n <= 100000
```

#### 解法一（排序）

直接排序，之后遍历前一个元素跟后一个元素是否相等，相等直接返回元素

```java
// 时间复杂度 O(nlogn)
public int findRepeatNumber(int[] nums) {
    Arrays.sort(nums);
    for(int i = 0; i < nums.length - 1; i++){
        if(nums[i] == nums[i + 1]){
            return nums[i];
        }
    }
    return -1;
}
```

#### 解法二（HASH）

把元素一个个的加入`hashSet`集合中，加入有重复则直接返回

```java
// 时间复杂度O(n)
public int findRepeatNumber(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    for (int num : nums) {
        if (!set.add(num)) {
            return num;
        }
    }
    return -1;
}
```

#### 解法三（原地置换）

因为长度为 `n` 且所有数字都在 `0 到 n - 1`之间，所以如果没有重复数字，那么正常排序后，数字就在对应下标的地方。因此遍历数组，如果不对应则交换到对应的位置，如果对应位置已经有对应的数字则发生重复。

```java
// 时间复杂度O(n)，空间复杂度O(1)
public int findRepeatNumber(int[] nums) {
    for(int i = 0; i < nums.length; i++){
        // 不相等则一直交换，知道出现重复或者相等。
        while(nums[i] != i){
            if(nums[i] == nums[nums[i]]){
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
    }
    return -1;
}
```

### 04. 二维数组中的查找

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

限制：

```
0 <= n <= 1000
0 <= m <= 1000
```

#### 解法一 （线性查找）

从数组**右上角**开始查找数组，如果元素大于目标值，`j--`，如果小于 `i++`，依次循环直至找到这个数。

```java
// 时间复杂度O(m + n)
public boolean find(int[][] matrix, int target) {
    int i = 0, j = matrix[0].length - 1;
    
    while(i < matrix.length && j >= 0){
        if(matrix[i][j] > target){
            j--;
        }else if(matrix[i][j] < target){
            i++;
        }else{
            return true;
        }
    }
    return false;
}
```

### 05. 替换空格

请实现一个函数，把字符串 `s` 中的每个空格替换成`"%20"`。

示例 1：

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

**限制：**

```
0 <= s 的长度 <= 10000
```

#### 解法一（常规）

直接StringBuilder就OK。

时间复杂度O(n)

#### 解法二（逆向思维）

先遍历一般数组记录空格的个数，然后创建对应长度的字符数组

时间复杂度O(n)

### 06. 从尾到头打印链表

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：

```
输入：head = [1,3,2]
输出：[2,3,1]
```

限制：

```
0 <= 链表长度 <= 10000
```

#### 解法一（递归法）

递归到链表末端，回溯时依次将节点值加入结果列表中。 

```java
// 时间复杂度O(n)
ArrayList<Integer> res = new ArrayList<>();

public int[] reversePrint(ListNode head) {
    reverse(head);
    int[] a = new int[res.size()];
    for(int i = 0; i < a.length; i++)
        a[i] = res.get(i);
    return a;
}
public void reverse(ListNode head){
    if(head != null){
        // 先递归再输出
        reverse(head.next);
        res.add(head.val);
    }
}
```

#### 解法二（辅助栈）

从头到尾遍历链表，并用一个栈存储每个结点的值，之后出栈输出值即可。

```java
// 时间复杂度O(n)
public int[] reversePrint(ListNode head) {
    LinkedList<Integer> stack = new LinkedList<>();
    while (head != null) {
        stack.addLast(head.val);
        head = head.next;
    }
    int[] res = new int[stack.size()];
    for (int i = 0; i < res.length; i++)
        res[i] = stack.removeLast();
    return res;
}
```

### 07. 重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。从下往上的构建过程。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```

**限制：**

```
0 <= 节点个数 <= 5000
```

#### 解法一（递归）

先序遍历的第一个节点就是根节点，在中序遍历中通过根节点区分左右子树，根节点的左右节点代表着范围更小的对应先序遍历和中序遍历的递归过程。

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) {
        return null;
    }
    // 前序遍历的第一个位置就是根节点
    TreeNode root = new TreeNode(preorder[0]);
    // TODO: 这里为了简洁，可以通过HASH
    for (int i = 0; i < preorder.length; i++) {
        // 在中序遍历中找到与根节点对应的位置
        if (preorder[0] == inorder[i]) {
            root.left = buildTree(
                // copyOfRange对应的范围 [a, b)
                Arrays.copyOfRange(preorder, 1, i + 1),
                Arrays.copyOfRange(inorder, 0, i)
            );
            root.right = buildTree(
                // copyOfRange对应的范围 [a, b)
                Arrays.copyOfRange(preorder, i + 1, preorder.length),
                Arrays.copyOfRange(inorder, i + 1, inorder.length)
            );
            break;
        }
    }
    return root;
}
```

先序遍历的第一个节点就是根节点，直接创建根节点，然后在中序遍历中通过根节点区分左右子树树，根的左右节点就分别等于缩小范围的根的左节点与左子树和根的右节点与右子树的递归调用，从下往上的构建过程。

```java
// 时间复杂度O(n)
// hash从中序遍历中找到对应值的下标，下标左右为对应的左右子树
HashMap<Integer, Integer> map = new HashMap<>();

// 先序遍历，方便在递归中进行访问
int[] preorder;

public TreeNode buildTree(int[] preorder, int[] inorder) {
    this.preorder = preorder;
    for (int i = 0; i < preorder.length; i++) {
        // 添加值和对应的下标
        map.put(inorder[i], i);
    }
    return recursive(0, 0, inorder.length - 1);
}

public TreeNode recursive(
    int pre_root_idx, // 根节点对应的下标
    int in_left_idx,  // 中序遍历对应范围的左下标
    int in_right_idx  // 中序遍历对应范围的右下标
) {
    // 中序遍历范围小于0代表已完成
    if (in_right_idx - in_left_idx < 0) {
        return null;
    }
    // 根据根节点下标创建根节点
    TreeNode root = new TreeNode(preorder[pre_root_idx]);
    // 根据根节点的值从HashMap中找到对应在中序遍历中的下标
    int idx = map.get(preorder[pre_root_idx]);
    // 根节点的左子树就是根的左节点与中序遍历缩小范围的递归过程
    root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);
    // 根节点的右子树就是根的右节点与中序遍历缩小范围的递归过程
    root.right = recursive(
        // 根节点的右节点 = 根节点的下一个节点 + 左子树的长度
        pre_root_idx + 1 + (idx - in_left_idx), 
        idx + 1, in_right_idx
    );
	// 返回已经构建好的根节点
    return root;
}
```

#### 解法二（迭代）

使用栈保存**右节点还没有赋值**的节点，通过前序遍历两个相邻节点的关系来判断，其存在两种关系

* 后一个节点是前一个节点的左节点，直接赋值左节点。因为前序遍历和后续遍历对应不相等
* 前节点不存在左节点，后节点是**已经入栈节点的右节点**，但是不知道是哪一个节点的右节点。所以将已经入栈的节点出栈与中序遍历节点比较，如果相等对应了中序遍历节点没有右节点的过程，跳过，不相等则代表前序遍历后节点就是这个出栈节点的右节点。

就这样循环遍历前序遍历两两相邻节点，根据中序遍历匹配两种不同的情况就可以重建二叉树。从上往下的构建过程。

```java
// 时间复杂度O(n)
public TreeNode buildTree1(int[] preorder, int[] inorder) {
    // 根节点就是前序节点的第一个
    TreeNode root = new TreeNode(preorder[0]);
    // 用栈维护右节点还没有赋值的节点
    // 左子树可以立即赋值，通过判断中序遍历的第一个结点是否等于根节点即可
    Stack<TreeNode> stack = new Stack<>();
    // 根节点入栈
    stack.push(root);
    // 中序遍历下标
    int inorderIndex = 0;
    // 从前序遍历下一个相邻节点开始判断，判断其是在根节点的左 or 右子树
    for (int i = 1; i < preorder.length; i++) {
        // peek一下栈顶(右节点还没有赋值的节点)，并不会出栈
        TreeNode node = stack.peek();
        // 前序遍历和中序遍历对应下标不等，代表节点存在左节点
        if (node.val != inorder[inorderIndex]) {
            // 前序遍历下一个相邻节点是前节点的左节点，赋值
            node.left = new TreeNode(preorder[i]);
            // node.left的右节点还没有赋值，入栈
            stack.push(node.left);
        }
        // 前序遍历和中序遍历对应下标相等，即节点不存在左节点，
        else {
            // 前序遍历下一个相邻节点是已经入栈节点的右节点，但是不知道具体是哪一个节点
            while (!stack.isEmpty()
                   && stack.peek().val == inorder[inorderIndex]
                  ) {
                // 所以在这里进行过滤，从前序遍历已经遍历的节点逆序(对应出栈)开始和
                // 中序遍历比较，相等就代表该节点不存在右子节点直接跳过
                node = stack.pop();
                inorderIndex++;
            }
            // 直到不相等，代表前序遍历下一个相邻节点就是这个节点的右节点
            node.right = new TreeNode(preorder[i]);
            // 其不存在右节点，入栈
            stack.push(node.right);
        }
    }
    return root;
}
```

### 09. 用两个栈实现队列

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 `appendTail` 和 `deleteHead` ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，`deleteHead` 操作返回 -1 )

示例 1：

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```

示例 2：

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```

**提示：**

- `1 <= values <= 10000`
- `最多会对 appendTail、deleteHead 进行 10000 次调用`

#### 解法一（常规）

维护两个栈，入栈支持插入操作，出栈支持删除操作。入队直接压入入栈。出队时，直接弹出出栈栈顶，但是存在出栈中不存在元素，此时将入栈中的所有元素压入出栈中，然后再弹出出栈栈顶，这样顺序不会乱掉。

```java
// 时间复杂度，入栈和出栈都是O(1)
class CQueue {
    //两个栈，一个入栈
    private Stack<Integer> stack1;
    // 一个出栈
    private Stack<Integer> stack2;
    
    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void appendTail(int value) {
        stack1.push(value);
    }
    
    public int deleteHead() {
        // 只要出栈中存在元素，栈顶代表是对头
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            // 出栈中没有元素，就将入栈中的所有元素压入出栈中
            // 这样顺序就不会乱了
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.isEmpty() ? -1 : stack2.pop();
        }
    }
}
```

### 10- I. 斐波那契数列

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：

```
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

```
输入：n = 2
输出：1
```

示例 2：

```
输入：n = 5
输出：5
```

 提示：

- `0 <= n <= 100`

#### 解法一（动态规划）

1. 创建一个dp数组，下标为n的元素的值代表对应斐波那契数列的第n项的值
2. 已知第0项和第1项值
3. 对应状态转移方程已给出，F(N) = F(N - 1) + F(N - 2)

自底向上即可推出全部值。

```java
// 时间复杂度O(n)
public int fib(int n) {
    if (n == 0) return 0;
    // dp数组，下标为n的元素的值代表对应斐波那契数列的第n项的值
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
        dp[i] %= 1000000007;
    }
    return dp[n];
}
```

### [10- II. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

```
输入：n = 2
输出：2
```

示例 2：

```
输入：n = 7
输出：21
```

示例 3：

```
输入：n = 0
输出：1
```

提示：

- `0 <= n <= 100`

#### 解法一（动态规划）

1. 创建一个dp数组，下标为n的元素的值代表对应青蛙跳上一个n级台阶的跳法
2. 已知上0级、1级、2级的跳法，分别为0、1、2
3. 状态转移方程`第n次的跳法 = 第n - 1次的跳法（对应跳一步） + 第n - 2次的跳法（对应跳两步）  `

自底向上即可推出全部值。

```java
// 时间复杂度O(n)
public int numWays(int n) {
    if(n == 0 || n == 1) return 1;
    // 下标为n的元素的值代表对应青蛙跳上一个n级台阶的跳法，
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;
    // 从跳3级开始推导
    for(int i = 3; i <= n; i++){
        // 可以跳一级或者两级，两种跳法相加
        dp[i] = dp[i - 1] + dp[i - 2];
        dp[i] = dp[i] % 1000000007;
    }
    return dp[n];
}
```

### [11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。**输入一个递增排序的数组的一个旋转**，输出旋转数组的最小元素。例如，数组 `[3,4,5,1,2]` 为 `[1,2,3,4,5]` 的一个旋转，该数组的最小值为1。 

示例 1：

```
输入：[3,4,5,1,2]
输出：1
```

示例 2：

```
输入：[2,2,2,0,1]
输出：0
```

#### 解法一 （二分查找）

由于是递增数组的旋转，即数组的左边大于右边，中间的交接值即为最小的元素，可以用二分查找找到这个元素。

二分查找中间元素大于右边，代表最小值范围在中间元素的右边，向右缩小范围；中间元素小于右边，代表最小值范围就在包括中间元素之内的左边，向左缩小范围；相等的话，缩小右边的范围，避免死循环。

```java
// 时间复杂度O(logn)
public int minArray(int[] numbers) {
    int left = 0, right = numbers.length - 1;
    while(left < right) {
        int mid = left + (right - left) / 2;
        // 因为有可能不旋转，跟右边判断能匹配这种情况
        // 中间元素大于右边，代表最小值范围在中间元素的右边
        if (numbers[mid] > numbers[right]) {
            left = mid + 1;
        } else if (numbers[mid] < numbers[right]) {
            // 中间元素小于右边，代表最小值范围就在包括中间元素之内的左边
            right = mid;
        } else {
            // 相等的话，缩小右边的范围，避免死循环
            right--;
        }
    }
    return numbers[left];
}
```

### [12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

请设计一个函数，用来判断在**一个矩阵中是否存在一条包含某字符串所有字符的路径**。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

```
[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]
```

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

示例 1：

```
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
```

示例 2：

```
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false
```

**提示：**

- `1 <= board.length <= 200`
- `1 <= board[i].length <= 200`

#### 解法一 （DFS）

DFS模板，遍历每个路径的起点，如果格子上的字符跟对应字符相等，就可以进行三个方向的尝试，直至暴力尝试完所有的路径或者中途找到路径退出。

```java
// 方便访问
char[] word;
char[][] board;

public boolean exist(char[][] board, String word) {
    this.word = word.toCharArray();
    this.board = board;
    // 可以从矩阵的任意一个点开始，所以可以在这里进行遍历
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            if (dfs(i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

public boolean dfs(
    int i,      // 下标
    int j,      // 下标
    int len     // 已经匹配的长度
) {
    // 超过边界或者对应位置字符不匹配
    if (i < 0 || j < 0
        || i > board.length - 1
        || j > board[0].length - 1
        || word[len] != board[i][j]) {
        return false;
    }
    // 代表已经找到对应的路径
    if (len == word.length - 1) {
        return true;
    }
    // 防止走回头路，回头路跟将要匹配的字符相等就会出错
    char tmp = board[i][j];
    board[i][j] = '/';
    // 分别对应四种情况
    boolean res = dfs(i + 1, j, len + 1) || dfs(i - 1, j, len + 1)
        	   || dfs(i, j + 1, len + 1) || dfs(i, j - 1, len + 1);
    board[i][j] = tmp;
    return res;
}
```

### 13. [机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

地上有一个m行n列的方格，从坐标 `[0,0]` 到坐标 `[m-1,n-1]` 。一个机器人从坐标 `[0, 0] `的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

示例 1：

```
输入：m = 2, n = 3, k = 1
输出：3
```

示例 2：

```
输入：m = 3, n = 1, k = 0
输出：1
```

提示：

- `1 <= n,m <= 100`
- `0 <= k <= 20`

#### 解法一（DFS）

DFS模板，从0, 0开始，如果该位置有效，就可以尝试四个方向，直至暴力尝试完所有有效的格子。

```java
// 时间复杂度O(MN)
// 方便访问
int limit, m, n;
boolean[][] visited;
public int movingCount(int m, int n, int k) {
    this.limit = k;
    this.m = m;
    this.n = n;
    // 用于记录已经访问的格子
    this.visited = new boolean[m][n];
    return dfs(0, 0);
}
public int dfs(int i, int j){
    // 计算行坐标和列坐标的数位之和
    int k = 0, i1 = i, j1 = j;
    while(i1 != 0){
        k += (i1 % 10);
        i1 /= 10;
    }
    while(j1 != 0){
        k += (j1 % 10);
        j1 /= 10;
    }
    // 下标出界或者是已经访问
    if(k > limit || i < 0 || j < 0 || i >= m || j >= n || visited[i][j]){
        return 0;
    }
    visited[i][j] = true;
    // 对应四种情况，其实通过向右和向下移动，访问所有可达解。
    return dfs(i + 1, j) + dfs(i - 1, j) + dfs(i, j + 1) + dfs(i, j - 1) + 1;
}
```

#### 解法二（BFS）

使用BFS广度优先算法套模板即可完成。原理是遍历队列里面的每个节点并再将遍历节点周围的结点入队，这样一圈一圈的处理就是广度优先遍历。

```java
// 时间复杂度O(MN)
public int movingCount(int m, int n, int k) {
    // 用于记录已经访问的格子
    boolean[][] visited = new boolean[m][n];
    int res = 0;
    // 创建一个队列用于保存一个节点周围的所有节点
    // 原理是遍历队列里面的每个节点并再将遍历节点周围的结点入队
    // 这样一圈一圈的处理就是广度优先遍历
    Queue<int[]> queue = new LinkedList<>();
    // 初始化加入第一个结点
    queue.add(new int[]{0, 0});
    while (queue.size() > 0) {
        // 开始处理第一个节点
        int[] x = queue.poll();
        // 计算行坐标和列坐标的数位之和
        int i = x[0], j = x[1];
        int k1 = 0, i1 = i, j1 = j;
        while (i1 != 0) {
            k1 += (i1 % 10);
            i1 /= 10;
        }
        while (j1 != 0) {
            k1 += (j1 % 10);
            j1 /= 10;
        }
        // 不满足条件，跳过这个结点
        if (i >= m || j >= n || i < 0 || j < 0 || k < k1 || visited[i][j]) continue;
        visited[i][j] = true;
        res++;
        // 将节点周围的一圈的结点加入队列
        queue.add(new int[]{i + 1, j});
        queue.add(new int[]{i - 1, j});
        queue.add(new int[]{i, j + 1});
        queue.add(new int[]{i, j - 1});
    }
    return res;
}
```

### [14. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

给你一根长度为 `n` 的绳子，请把绳子剪成整数长度的 `m` 段（m、n都是整数，n>1并且m>1），每段绳子的长度记`k[0],k[1]...k[m-1]` 。请问 `k[0]*k[1]*...*k[m-1]` 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

示例 1：

```
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1
```

示例 2:

```
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
```

**提示：**

- `2 <= n <= 58`

#### 解法一（动态规划）

1. 创建一个dp数组，下标为n的元素的值代表对应长度绳子分解之后的最大乘积
2. 已知第2项和第3项值
3. 状态转移方程也容易推出。这里需要注意的是因为dp[x]有可能小于x，比如dp[3] = 2 < 3，所以就有下面代码中的判断操作。

自底向上即可推出全部值。

```java
// 时间复杂度O(n * n)
public int cuttingRope(int n) {
    // dp数组，下标为n的元素的值代表对应长度绳子分解之后的最大乘积
    int[] dp = new int[n + 1];
    dp[2] = 1;
    if(n > 2)
        dp[3] = 2;
    for(int i = 4; i <= n; i++){
        // 开始剪绳子，尝试每一个长度
        for(int j = 2; j < i; j++){
            // 他的最乘积就是他
            // 被剪之后的最大乘积和减掉的长度相乘
            // 和被剪之后的长度和减掉的长度相乘中的最大值
            // 因为dp[x]有可能小于x，比如dp[3] = 2 < 3
            dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
        }
    }
    return dp[n];
}
```

#### 解法二（贪心算法）

找规律之后我们可以发现**为使乘积最大，只有长度为 2 和 3 的绳子不应再切分，且 3 比 2 更优**，现将n转为3的倍数，然后在进行循环处理。

所谓贪心算法是指，在对问题求解时，总是做出在当前看来是最好的选择

```java
// 时间复杂度O(n)
// 为使乘积最大，只有长度为 2 和 3 的绳子不应再切分，且 3 比 2 更优
public int cuttingRope(int n) {
    int res = 1;
    // 将n转为3的倍数，方便后面处理
    // 比3的倍数大1，
    // 此时要将n转为3的倍数并让乘积最大
    // 最好的办法是减掉4,4的最大乘积为4
    if(n % 3 == 1){
        res = 4;
        n -= 4;
    }
    // // 比3的倍数大2，直接减2并乘2
    else if(n % 3 == 2){
        res = 2;
        n -= 2;
    }
    // 此时是3的倍数
    while(n > 0){
        res *= 3;
        n -= 3;
    }
    return res;
}
```

### [15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

示例 2：

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

示例 3：

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

#### 解法一（位运算）

跟 1 进行与运算，并记录结果。

```java
public int hammingWeight(int n) {
    int res = 0;
    while(n != 0){
        // 进行与运算
        res += n & 1;
        // 无符号右移一位
        n >>>= 1;
    }
    return res;
}
```

### [16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。

示例 1:

```
输入: 2.00000, 10
输出: 1024.00000
```

示例 2:

```
输入: 2.10000, 3
输出: 9.26100
```

示例 3:

```
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
```

说明:

- -100.0 < *x* < 100.0
- *n* 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。


### [20. 表示数值的字符串](https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

#### 解法一（常规）

判断四种符号的各个情况，从前往后开始开始判断。

```java
public boolean isNumber(String s) {
    if (s == null || s.length() == 0) {
        return false;
    }
    // 标记是否遇到数位、小数点、E，因为正负号最好判断，只有两种情况
    // 所以也不需要额外定义变量
    boolean hasNum = false, hasDot = false, hasE = false;
    // leetcode给的case中存在空格，去掉
    char[] str = s.trim().toCharArray();
    // 遍历字符串，跟已经遍历的情况比较
    for (int i = 0; i < str.length; i++) {
        // 因为正负号最好判断，只有两种情况，所以也不需要额外定义变量
        if (str[i] == '-' || str[i] == '+') {
            // 1. 正负号只可能出现在第一个位置 2. 出现在 e 的后面一个位置
            if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                return false;
            }
        }
        // 判断数字
        else if (str[i] >= '0' && str[i] <= '9') {
            hasNum = true;
        }
        // 判断小数点
        else if (str[i] == '.') {
            // 两种情况 1. 小数点只能有一个 2. e的后面不能有小数点
            if (hasDot || hasE) {
                return false;
            }
            // 标记已经遇到一个小数点
            hasDot = true;
        }
        // 判断e
        else if (str[i] == 'e' || str[i] == 'E') {
            // 三种情况 1. e只能有一个 2. e前面必须有整数 3. e的后面也必须是一个整数
            if (!hasNum || hasE) {
                return false;
            }
            // 标记已经遇到一个e
            hasE = true;
            // 重置isNum，因为e之后也必须接上整数
            hasNum = false;
        }
        // 其它情况均为不合法字符
        else {
            return false;
        }
    }
    // 结尾必须为数字
    return hasNum;
}
```

#### 解法二（状态机）

自动机驱动的编程，可以被看做一种暴力枚举方法的延伸。

* 先定义状态集合，一个常用的技巧是，用`当前处理到字符串的哪个部分`当作状态的表述。
* 再找出初始状态和接受状态
* 最后定义转移规则

```java
public boolean isNumber(String s) {
    Map[] states = {
        // 定义状态集合和转移规则
        new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
        new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
        new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
        new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
        new HashMap<>() {{ put('d', 3); }},                                        // 4.
        new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
        new HashMap<>() {{ put('d', 7); }},                                        // 6.
        new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
        new HashMap<>() {{ put(' ', 8); }}                                         // 8.
    };
    // 初始状态
    int p = 0;
    char t;
    for(char c : s.toCharArray()) {
        if(c >= '0' && c <= '9') t = 'd';
        else if(c == '+' || c == '-') t = 's';
        else if(c == 'e' || c == 'E') t = 'e';
        else if(c == '.' || c == ' ') t = c;
        else t = '?';
        if(!states[p].containsKey(t)) return false;
        p = (int)states[p].get(t);
    }
    // 接受状态
    return p == 2 || p == 3 || p == 7 || p == 8;
}
```

### [21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

**提示：**

1. `1 <= nums.length <= 50000`
2. `1 <= nums[i] <= 10000`

#### 解法一（双指针）

在头和尾分别设置一个指针，头指针指向奇数则后移，尾指针指向偶数则前移，交换。

```java
public int[] exchange(int[] nums) {
    // 双指针
    int i = 0, j = nums.length - 1;
    while (i < j) {
        // 找到一个偶数
        while (i < j && nums[i] % 2 == 1) {
            i++;
        }
        // 找到一个奇数
        while (i < j && nums[j] % 2 == 0) {
            j--;
        }
        // 交换
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    return nums;
}
```

### [22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

**示例：**

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

#### 解法一（双指针）

使用快慢指针，第一个指针先走 k 步，然后两个一起走，等快指针到结尾的时候慢指针指向倒数第k个结点。

```java
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
```

### [24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

示例:

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

限制：

```
0 <= 节点个数 <= 5000
```

#### 解法一（双指针）

定义两个指针一个在前一个在后，再定义一个临时指针用于保存后指针的下一个节点，接下来后指针指向前指针反转，再利用临时指针找到下一个节点，循环。

```java

public ListNode reverseList(ListNode head) {
    // 定义两个指针一个在前一个在后
    ListNode p1 = null, p2 = head;
    while(p2 != null){
        // 临时指针，保存后指针的下一个节点
        // 不保存的话交换之后就找不到下一个节点了
        ListNode tmp = p2.next;
        // 反转
        p2.next = p1;
        // 前进
        p1 = p2;
        // 前进
        p2 = tmp;
    }
    return p1;
}
```

#### 解法二（递归）

递归其实就是栈，利用入栈顺序，出栈逆序即可交换，入栈保存一个相邻的节点，出栈的时候利用那个相邻的节点和入参节点即可完成反转。

```java
ListNode newHead;

public ListNode reverseList2(ListNode head) {
    // 链表为空直接返回
    if (head == null) {
        return head;
    }
    // 内部反转
    re(head);
    // 反转之后的尾的指针不会反转
    // 会构成环，手动帮他置为空
    head.next = null;
    return newHead;
}

public void re(ListNode head) {
    if (head.next == null) {
        // 代表到达链表尾，设置为新的头
        newHead = head;
        return;
    }
    // 入栈是顺序的，出栈就是反序
    // 要交换，肯定要两个值，已经有一个head，
    // 下一个值head.next最方便
    ListNode tmp = head.next;
    // 入栈
    reverseList(head.next);
    // 出栈，将指针交换
    tmp.next = head;
}
```

### [25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

限制：

```
0 <= 链表长度 <= 1000
```

#### 解法一（双指针）

因此容易想到使用双指针遍历两链表，根据值大小关系确定节点添加顺序，两节点指针交替前进，直至遍历完毕

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 创建一个链表
    ListNode head = new ListNode();
    ListNode p = head;
    while(l1 != null && l2 != null){
        // 根据大小，选择相应的插入到新链表
        if(l1.val < l2.val){
            p.next = l1;
            l1 = l1.next;
        }else{
            p.next = l2;
            l2 = l2.next;
        }
        // 前进！
        p = p.next;
    }
    // 添加未比较完成的
    p.next = (l1 == null ? l2 : l1);
    // 头结点为空，用来辅助运算
    return head.next;
}
```

#### 解法二（递归）

递归就是栈，利用栈。出栈的时候开始构建链表，从尾向前。

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // 他完了不代表其他人完了
    if (l1 == null) {
        return l2;
    }
    // 他完了不代表其他人完了
    if (l2 == null) {
        return l1;
    }
    // 出栈的时候开始构建链表，从尾向前
    if (l1.val < l2.val) {
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else {
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

### [26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

```
   3
  / \
  4  5
 / \
1   2
```

给定的树 B：

```
  4 
 /
1
```

返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

示例 1：

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

示例 2：

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```

限制：

```
0 <= 节点个数 <= 10000
```

#### 解法一（递归）

先序遍历树A中的每个节点Na作为相对根节点，判断树A中以Na为根节点的子树是否包含B树，每次判断的时候使用dfs算法将会遍历到树底，递归比较其左右子树是否对应相等。

```java
public boolean isSubStructure(TreeNode A, TreeNode B) {
    // 过滤特殊格式
    if(A == null || B == null){
        return false;
    }
    // 首先在相对根节点判断一次，然后分别递归遍历其左右再继续判断
    return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
}
boolean dfs(TreeNode A, TreeNode B) {
    // 子结构已经匹配完成，返回true
    if(B == null) return true;
    // 子结构没有完成，但是树已经遍历完成了
    if(A == null) return false;
    // 判断其值是否相等，在递归判断其子对应左右是否能匹配上
    return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
}
```

### [27. 二叉树的镜像](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/)

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

镜像输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

示例 1：

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

限制：

```
0 <= 节点个数 <= 1000
```

#### 解法一（递归）

 利用树的后序遍历并将每次的访问左节点和右节点的值保存下来，方便后序遍历之后对根访问的时候左右交换。

```java
public TreeNode mirrorTree(TreeNode root) {
    if(root == null){
        return null;
    }
    // 递归遍历完左边并保存每次递归左边的值
    TreeNode tmpRight =  mirrorTree(root.left);
    // 递归遍历完右边并保存每次递归右边的值
    TreeNode tmpLeft = mirrorTree(root.right); 
    // 后序遍历，从下往上构建
    // 在树的最底层都是相对局部交换，最后树也可以看成局部，左边右边，一次交换。
    root.right = tmpRight;
    root.left = tmpLeft;
    return root;
}
```

#### 解法二（DFS）

无论是BFS还是DFS都会访问到每一个节点，访问每个节点的时候交换他的左右子节点，直到所有的节点都访问完为止，代码如下

```java
public TreeNode mirrorTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    // 栈
    Stack<TreeNode> stack = new Stack<>();
    // 入栈
    stack.add(root);
    // 其实就是树的深度优先遍历，dfs
    // 在这里是沿着右子树走走到底。
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        // 将其左右子树都加入栈
        if (node.left != null) {
            stack.add(node.left);
        }
        if (node.right != null) {
            stack.add(node.right);
        }
        // 交换遍历到的每一个节点
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
    return root;
}
```

### [28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \ 
   3    3
```

示例 1：

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

示例 2：

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

限制：

```
0 <= 节点个数 <= 1000
```

#### 解法一（递归）

前序遍历，之间判断左右是否相同，需要注意的是二叉树一个节点有两个节点，两个节点要分别进行比较

```java
public boolean isSymmetric(TreeNode root) {
        return root == null || recur(root.left, root.right);
}
public boolean recur(TreeNode left, TreeNode right){
    // 都为空，代表遍历完成
    if(left == null && right == null){
        return true;
    }
    // 判断左右是否相等
    if(left == null || right == null || left.val != right.val){
        return false;
    }
    // 二叉树一个节点有两个节点，两个节点要分别进行比较
    return recur(left.left, right.right) && recur(left.right, right.left);
}
```

#### 解法二（BFS）

广度优先算法，一层一层的进行比较。注意都为空，代表的是这一个子树遍历完成，并不是整个树。

```java
public boolean isSymmetric1(TreeNode root) {
    if(root == null){
        return true;
    }
    // bfs广度优先算法
    Queue<TreeNode> queue = new LinkedList<>();
    // 入队
    queue.offer(root.left);
    queue.offer(root.right);
    while(!queue.isEmpty()){
        TreeNode left = queue.poll();
        TreeNode right = queue.poll();
        // 都为空，代表这一个子树遍历完成
        if(left == null && right == null){
            continue;
        }
        // 任何一个为空代表不匹配
        else if (left == null || right == null){
            return false;
        }
        // 值要相等
        if (left.val != right.val){
            return false;
        }
        // 将下一层的节点入队
        // 二叉树一个节点有两个节点，两个节点要分别进行比较
        queue.offer(left.left);
        queue.offer(right.right);
        queue.offer(left.right);
        queue.offer(right.left);
    }
    return true;
}
```

### [29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

示例 1：

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
```

示例 2：

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

限制：

- `0 <= matrix.length <= 100`
- `0 <= matrix[i].length <= 100`

#### 解法一（常规）

因为是顺时针，所以会有四个遍历方向，创建四个变量分别代表上下左右，这四个变量所围起来的范围就是有效的范围，循环在边界进行遍历。

```java
public int[] spiralOrder(int[][] matrix) {
    if (matrix.length == 0) {
        return new int[0];
    }
    // 上下左右四条边各自的位置 和 结果数组当前结果的下标
    int l = 0, t = 0, r = matrix[0].length - 1, b = matrix.length - 1, len = 0;
    int[] res = new int[(r + 1) * (b + 1)];
    // 一直循环遍历
    while (true) {
        // 从左往右
        for (int i = l; i <= r; i++) {
            res[len++] = matrix[t][i];
        }
        // 判断其是否超过边界，超过边界直接遍历结束
        if (++t > b) {
            break;
        }
        for (int i = t; i <= b; i++) {
            res[len++] = matrix[i][r];
        }
        if (--r < l) {
            break;
        }
        for (int i = r; i >= l; i--) {
            res[len++] = matrix[b][i];
        }
        if (t > --b) {
            break;
        }
        for (int i = b; i >= t; i--) {
            res[len++] = matrix[i][l];
        }
        if (++l > r) {
            break;
        }
    }
    return res;
}
```

#### 解法二（DFS）

顺时针遍历矩阵只有一条路径，所以按固定方向dfs搜索，dfs按固定的右、下、左、上方向走迷宫，不能走就换方向，直到路径填满退出。

```java
// 简易版机器人的运动范围
```

### [30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

示例:

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();   --> 返回 0.
minStack.min();   --> 返回 -2.
```

提示：

1. 各函数的调用总次数不超过 20000 次

#### 解法一（链表）

考虑用链表实现栈，每个node都保存当前在栈中的所有元素的最小值。在入栈的的时候判断当前栈中所有元素的最小值。

```java
class MinStack {
    // 头结点
    Node head;
    // 内部静态Node类
    static class Node{
        Node next;
        int val;
        // 在每个实例node上都保存当前最小值
        int min;

        public Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }
    public MinStack() {
        head = new Node(0, Integer.MAX_VALUE);
    }  
    public void push(int x) {
        // 当前值与栈顶最小值比较，找出最小的值
        int min = x;
        if(head.next != null){
            min = Math.min(min, head.next.min);
        }
        Node node = new Node(x, min);
        // 头插法，head.next即为栈顶
        node.next = head.next;
        head.next = node;
    } 
    public void pop() {
        head.next = head.next.next;
    }  
    public int top() {
        return head.next.val;
    }   
    public int min() {
        // 直接返回栈顶最小值
        return head.next.min;
    }
}
```

### [31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

示例 1：

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```

**示例 2：**

```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

提示：

1. 0 <= pushed.length == popped.length <= 1000
2. 0 <= pushed[i], popped[i] < 1000
3. pushed 是 popped 的排列。

#### 解法一（模拟栈）

把入栈数组的元素逐个压栈，当栈顶元素等于弹出序列数组中对应元素的时候，就让栈顶元素出栈、下标自增，循环，最后判断栈是否为空即可。

```java
public boolean validateStackSequences(int[] pushed, int[] popped) {
    // 创建一个辅助栈
    Stack<Integer> stack = new Stack<>();
    // 弹出序列下标
    int j = 0;
    // 遍历压入序列
    for (int k : pushed) {
        // 模拟入栈
        stack.push(k);
        // 如果栈顶出栈的话，判断能否跟弹出序列对应下标匹配
        while (!stack.empty() && stack.peek() == popped[j]) {
            stack.pop();
            j++;
        }
    }
    return stack.empty();
}
```

### [32. 从上到下打印二叉树](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)

对应三个题目，分别如下

* 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
* 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
* 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

#### 题一（BFS）

只需要一个层序遍历就可以完成。

```java
// BFS怎么着也需要默写下来吧
public int[] levelOrder(TreeNode root) {
    if (root == null) return null;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    ArrayList<Integer> ans = new ArrayList<>();
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        ans.add(node.val);
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
    int[] res = new int[ans.size()];
    for (int i = 0; i < ans.size(); i++)
        res[i] = ans.get(i);
    return res;
}
```

#### 题二（BFS）

只需要一个层序遍历就可以完成。

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if(root != null){
        queue.offer(root); 
    }
    while (!queue.isEmpty()) {
        ArrayList<Integer> ans = new ArrayList<>();
        // 或者这一层所有节点的个数，并一次性遍历完
        int size = queue.size();
        for(int i = 0; i < size; i++){
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        res.add(ans);
    }
    return res;
}
```

#### 题三（BFS）

只需要一个层序遍历就可以完成，根据层数的奇偶选择不同的插入顺序。

```java
public List<List<Integer>> levelOrder1(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> res = new ArrayList<>();
    if (root != null) {
        queue.offer(root);
    }
    int depth = 0;
    while (!queue.isEmpty()) {
        LinkedList<Integer> ans = new LinkedList<>();
        // 或者这一层所有节点的个数，并一次性遍历完
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            // 根据奇偶插入到结果的不同位置
            if (depth % 2 == 0) {
                ans.addLast(node.val);
            } else {
                ans.addFirst(node.val);
            }
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        res.add(ans);
        depth++;
    }
    return res;
}
```

### [33. 二叉搜索树的后序遍历序列](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

输入一个整数数组，判断该数组是不是**某二叉搜索树**的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

参考以下这颗二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

示例 1：

```
输入: [1,6,3,2,5]
输出: false
```

示例 2：

```
输入: [1,3,2,6,5]
输出: true
```

#### 解法一（）

```java

```

### [39. 数组中出现次数超过一半的数字](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:

```
输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2
```

#### 解法一（排序）

直接排序，排序之后中间的那个即为超过一般的数组，时间复杂度nlog(n)

#### 解法二（HASH）

遍历数组，用 HashMap 统计各数字的数量，最终超过数组长度一半的数字则为求值。此方法时间和空间复杂度均为 O(N)、O(N) 。

#### 解法三（摩尔投票法）

利用数量优势，一对一抵消，最后的那个即为求值。设置一个votes变量，遍历数组，当votes为0时即当前遍历的值为众数，在进行遍历，和众数相同votes++，否则--，到0就重复刚刚选的过程。时间、空间复杂度分别问O(n)、O(1)

```java
public int majorityElement(int[] nums) {
    // 众数    投票数
    int x = 0, votes = 0;
    // 遍历
    for(int num : nums){
        // 为0是选众数
        if(votes == 0) x = num;
        // ++ 或 --
        votes += num == x ? 1 : -1;
    }
    return x;
}
```

### [40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

**示例 1：**

```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```

**示例 2：**

```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

#### 解法一（最小堆）

top k问题常用最大/小堆来解决

```java

```



#### 解法二（快排变形）





### [63. 股票的最大利润](https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/)

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

示例 1:

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

示例 2:

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

#### 解法一（动态规划）

总是在股票价格最小的时候买入，之后找到一个卖出时的最大利润。

```java
public int maxProfit(int[] prices) {
    // 股票价格最小值             最大利润
    int min = Integer.MAX_VALUE, res = 0;
    for(int v : prices){
        // 更新股票价格最小值
        min = Math.min(v, min);
        // 找到当前价格与最小值卖出的最大利润
        res = Math.max(res, v - min);
    }
    return res;
}
```



## leetCode刷题总结

### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

示例:

```
给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

#### 解法一（暴力法）

```java
// 跳过
```

#### 解法二（HASH）

用hash保存值和下标，再用目标值去map中找就ok

```java
public int[] twoSum(int[] nums, int target) {
    // 用hash保存各个值
    Map<Integer, Integer> map = new HashMap<>();
    // 一直插入
    for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        // 从map中找到符合的值并返回下标
        if (map.containsKey(complement) && map.get(complement) != i) {
            return new int[] { i, map.get(complement) };
        }
    }
    return null;
}
```

#### 解法三（双指针）

如果数组已经排序的话就可以使用双之前一个在前，一个在尾解决这个问题

```java
public int[] twoSum(int[] nums, int target) {
    // nums必须已经升序
    // 首尾指针
    int left = 0, right = nums.length - 1;
    // 没有相交
    while (left < right) {
        // 求出双指针对应值
        int sum = nums[left] + nums[right];
        // 值相等就是结果
        if (sum == target) {
            return new int[]{left, right};
        }
        // 小于目标值则左指针++,让整个值增大
        else if (sum < target) {
            left++;
        }
        // 大于目标值则右指针--,让整个值减小
        else {
            right--;
        }
    }
    return new int[]{-1, -1};
}
```

### [15. 三数之和](https://leetcode-cn.com/problems/3sum/)

给你一个包含 *n* 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素*a，b，c ，*使得 *a + b + c =* 0 ？请你找出所有满足条件且不重复的三元组。 注意：答案中不可以包含重复的三元组。

示例：

```
给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

#### 解法一（双指针）

其本质就是两数求和，数组排序之后固定a，再对b和c进行两个数求和的双指针解法。注意需要跳过重复的三元组。

```java
public List<List<Integer>> threeSum(int[] nums) {
    // 结果列表
    List<List<Integer>> res = new ArrayList<>();
    // 特殊情况
    if (nums.length < 3) {
        return res;
    }
    // 排序
    Arrays.sort(nums);
    // 先固定 a
    for (int i = 0; i < nums.length - 2; i++) {
        // a 必须要小于 0，否者三树不可能为0
        if (nums[0] > 0) {
            return res;
        }
        // 因为不能包含重复的三元组，所有跳过
        if (i > 0 && (nums[i] == nums[i - 1])) {
            continue;
        }
        // 匹配为 0 的目标值
        int target = -nums[i];
        // 下面就是两数之和 排序双指针解法
        // 首尾指针
        int left = i + 1, right = nums.length - 1;
        // 首尾没有相交
        while (left < right) {
            // 首尾匹配目标值
            if ((nums[left] + nums[right]) == target) {
                // 添加结果
                res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                // 过滤重复值
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                // 过滤重复值
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                // 缩小范围，匹配下一个结果
                left++;
                right--;
            }
            // 大于，尾减
            else if ((nums[left] + nums[right] > target)) {
                right--;
            }
            // 小于，首加
            else {
                left++;
            }
        }
    }
    return res;
}
```

### [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

详情请点击链接跳转。

#### 解法一（DP）

```java

```

### [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/)

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

#### 解法一（快慢指针）

用一个快慢指针就可以完成，快慢指针相交就代表成环了

```java
public boolean hasCycle(ListNode head) {
    // 特殊值，不满足
    if (head == null || head.next == null) {
        return false;
    }
    // 快慢指针
    ListNode slow = head, fast = head;
    while(fast != null && fast.next != null){
        // 走一步
        slow = slow.next;
        // 走两步
        fast = fast.next.next;
        // 首尾相交，代表成环
        if(slow == fast){
            return true;
        }
    }
    // 代表到达链表尾部，且没有成环
    return false;
}
```

### [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。说明：不允许修改给定的链表。

示例 1：

```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

示例 2：

```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```

#### 解法一（）

```java

```





### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

说明:

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(*n2*) 。

进阶: 你能将算法的时间复杂度降低到 O(*n* log *n*) 吗?

#### 解法一（动态规划）

1. dp：下标为 i 的元素的值代表对应下标之前数组中最长的子序列长度
2. 初始值：所有项的初始值都为 1
3. 转移方程：用 i 遍历数组得到dp对应的最大值，但是由于子序列并不要求连续，因此用 j 遍历 i 的左边，j 小于 i 代表 i 可以接到 j 的后面，此时可以退出状态转移方程`dp[i] = Math.max(dp[i], dp[j] + 1)`找到 i 子序列中最大的上升长度。

```java
public int lengthOfLIS(int[] nums) {
    // 前i的数组中最长的子序列长度
    int[] dp = new int[nums.length];
    // 初始状态长度都为1
    Arrays.fill(dp, 1);
    // 开始遍历更新dp[i]的最大值
    for (int i = 0; i < nums.length; i++) {
        // 由于是子序列，并不要求连续
        // 所以此处要继续循环
        for (int j = 0; j < i; j++) {
            // 小于，代表i可以接到到j后面
            if (nums[i] > nums[j]) {
                // 更新dp[i]的最大值
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    // 找到最大的长度
    int res = 0;
    for (int i = 0; i < dp.length; i++) {
        res = Math.max(res, dp[i]);
    }
    return res;
}
```

#### 解法二（dp+二分）

```java
// TODO
```





## 笔试题目总结

### N 个骰子出现和为 M 的概率

#### 解法一（DFS）

这道题面试官提示说用动态规划，没写出来，其使用DFS可以更好解。可以不直接求概率，用DFS先求出总共可能的次数，最后在跟总的可能次数相除。

```java
// 求满足条件的个数
public int getNSumCount(int n, int m){
    // 不满足情况的条件
    if(n < 1 || m < n || m > 6 * n){
        return 0;
    }
    // 已经回溯到底且满足条件，res++
    if(n == 1){
        return 1;
    }
    // 回溯可能出现的6中情况
    int res = 0;
    for (int i = 1; i <= 6; i++) {
        res += getNSumCount(n - 1, m - i);
    }
    return res;
}
```

