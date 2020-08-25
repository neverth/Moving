# Moving
学习总结

## leetCode刷题总结
**淦**！！！他瞄的太难受了，🤬🤬🤬自己，敲过几遍的题目面试的还是还是还是敲不出来。

为了代码简洁，省略了一些异常处理。

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



### JZ 03. 数组中重复的数字

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

### JZ 04. 二维数组中的查找

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

### JZ 05. 替换空格

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

### JZ 06. 从尾到头打印链表

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

递归到链表末端，回溯时依次将节点值加入列表。 

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

### JZ 07. 重建二叉树

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

先序遍历的第一个节点就是根节点，在中序遍历中通过根节点区分左右子树树，根节点的左右节点代表着范围更小的对应先序遍历和中序遍历的递归过程。

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

先序遍历的第一个节点就是根节点，直接创建根节点，然后在中序遍历中通过根节点区分左右子树树，根的左右节点就分别等于缩小范围的跟的左节点与左子树和跟的右节点与右子树的递归调用，从下往上的构建过程。

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

* 后一个节点是前一个节点的左节点，直接赋值。因为前序遍历和后续遍历相对应不等
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

### JZ 09. 用两个栈实现队列

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

### JZ 10- I. 斐波那契数列

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

### [JZ 10- II. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

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

### [JZ 11. 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

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

### [JZ 12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

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

### JZ 13. [机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

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

### [JZ 14. 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

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

### [JZ 15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

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

### [JZ 16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

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


### [JZ 20. 表示数值的字符串](https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

#### 解法一

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

### [JZ 21. 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

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

### [JZ 22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

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

### [JZ 24. 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

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

### [JZ 25. 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

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
    // 头结点为空，用哦过与辅助
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

### [JZ 26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

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

### [JZ 27. 二叉树的镜像](https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/)

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

#### 解法二（BFS）

无论是BFS还是DFS都会访问到每一个节点，访问每个节点的时候交换他的左右子节点，直到所有的节点都访问完为止，代码如下

```java
public TreeNode mirrorTree1(TreeNode root) {
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

