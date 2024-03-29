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
        // 只要出栈中存在元素，栈顶代表是队头
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

### 10.1. 斐波那契数列

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

### [10.2. 青蛙跳台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

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
        // 防止溢出
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

假如是在一个先增长后减小的数组中找到最大值

```	java
二分一次找到中点，如果中点Array[mid]比Array[mid-1]和Array[mid+1]都大，那么就返回mid。否则，如果Array[mid]>Array[mid-1], 则最大值在mid右边，如果

Array[mid]>Array[mid+1], 则最大值在mid左边。不断进行下去，直到找到为止。
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

### [13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

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
    // 比3的倍数大2，直接减2并乘2
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

#### 解法一（三指针）

定义**三个指针一个在前中后**，接下来中指针指向前指针反转，前指针指向中指针，中指针指向后指针，后指针指向下一个指针，循环。

```java

public ListNode reverseList(ListNode head) {
    // 定义两个指针一个在前一个在后
    ListNode p1 = null, p2 = head;
    while(p2 != null){
        // 临时指针，保存后指针的下一个节点
        // 不保存的话交换之后就找不到下一个节点了
        ListNode p3 = p2.next;
        // 反转
        p2.next = p1;
        // 前进
        p1 = p2;
        // 前进
        p2 = p3;
    }
    return p1;
}
```

#### 解法二（递归）

递归其实就是栈，利用入栈顺序，出栈逆序即可交换，**入栈保存一个相邻的节点，出栈的时候利用那个相邻的节点和入参节点即可完成反转**。

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
    ListNode head = new Li			stNode();
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

**前序遍历**树A中的每个节点Na作为相对根节点，判断树A中以Na为根节点的子树是否包含B树，每次判断的时候使用dfs算法将会遍历到树底，递归比较其左右子树是否对应相等。

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

**重点是后序遍历**，利用树的**后序遍历**并将每次的访问左节点和右节点的值保存下来，方便**后序遍历之后对根访问的时候左右交换**。

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

**前序遍历**，之后判断左右是否相同，需要注意的是二叉树一个节点有两个节点，两个节点要分别进行比较

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

因为是顺时针，所以会有四个遍历方向，**创建四个变量分别代表上下左右**，这四个变量所围起来的范围就是有效的范围，循环在边界进行遍历。

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

#### 解法一（递归分支）

根据二叉搜索树的定义，可以通过递归，判断所有子树的 **正确性** （即其后序遍历是否满足二叉搜索树的定义） ，若所有子树都正确，则此序列为二叉搜索树的后序遍历。

符合定义的后序遍历数组的最后一个为根节点，因此从左往右开始遍历，连续且小于根节点的子序列即为根节点的左子树，连续且大于根节点的为右子树，接下来根据左右子树进行递归判断其是否符合定义。

```java
public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
}

boolean recur(int[] postorder, int i, int j) {
    // 子序列只有一个元素或者不存在元素，代表符合定义。
    if (i >= j) return true;
    // 数组指针，从头开始遍历
    int p = i;
    // 正确的二叉搜索树的后序遍历序列的最后一个元素为根节点
    // 小于根节点的连续子序列为根的左子树
    while (postorder[p] < postorder[j]) {
        p++;
    }
    // 保存左子树下标
    int m = p;
    // 大于跟结点的连续子序列为跟的右子树
    while (postorder[p] > postorder[j]) {
        p++;
    }
    // 满足定义时，右子树下标必须等于根节点下标，再分别递归判断根节点左右子树的正确性
    return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
}
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

利用数量优势，一对一抵消，最后的那个即为求值。设置一个votes变量，遍历数组，**当votes为0时即当前遍历的值为众数**，在进行遍历，和众数相同votes++，否则--，到0就重复刚刚选的过程。时间、空间复杂度分别问O(n)、O(1)

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

#### 解法一（最大堆）

最大堆是一种数据结构，它首先是一颗完全二叉树，并且，它所有父节点的值大于或等于两个子节点的值（并不要求右节点大于左节点）。

一棵深度为 k 的有n个结点的二叉树，对树中的结点按从上至下、从左到右的顺序进行编号，如果编号为i（1≤i≤n）的结点与满二叉树中编号为i的结点在二叉树中的位置相同，则这棵二叉树称为完全二叉树。

top k问题常用最大/小堆来解决，在求最小的k个数时，可以构建长度为k的最大堆。遍历整个数组，当遍历值小于最大堆的根元素时交换元素并重新调整堆。

一列数字放入最大推，堆调整之后里面存放的元素是这一列数字中最小的子序列

```java
// 由于大根堆实时维护前 k 小值，所以插入删除都是O(logk)的时间复杂度，最坏情况下数组里 nn 个数都会插入，所以一共需要nO(logk)的时间复杂度。
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
        // 并不要求右节点大于左节点
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
```

#### 解法二（快排变形）

直接通过快排切分排好第 K 小的数（下标为 K-1），那么其 k - 1下标的左边就是求值。

```java
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
```

#### 解法三（库函数）

在java中PriorityQueue可以实现最大或者是最小堆。PriorityQueue默认是最小堆。

```java
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
```

### [53.1. 在排序数组中查找数字](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)

统计一个数字在排序数组中出现的次数。

示例 1:

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```

示例 2:

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```

#### 解法一（遍历）

直接遍历就可以

#### 解法二（二分查找）

用二分查找分别找到左边界和右边界，在相减即可得。

```java
// 时间复杂度O(logn)
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
    if (right >= 0 && nums[right] != target){
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
```

### [53.2. 0～n-1中缺失的数字](https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/)

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

示例 1:

```
输入: [0,1,3]
输出: 2
```

示例 2:

```
输入: [0,1,2,3,4,5,6,7,9]
输出: 8
```

#### 解法一（遍历）

直接遍历就可以

#### 解法二（二分法）

```java
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
```

### [59.1 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。

示例:

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 
  滑动窗口的位置                最大值
---------------               -----

[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

#### 解法一（单调队列）

单调增队列实现，使用双向链表，当入队的时候将前面比入队元素小的全部给清除掉，保证当前队列元素单调递增。**你可以想象，加入数字的大小代表人的体重，把前面体重不足的都压扁了，直到遇到更大的量级才停住**。

在本题中，因为滑动窗口需要删除队头的值，但是我们想删除的队头元素 n 可能已经被「压扁」了，这时候就不用删除了

```java
// 线性时间复杂度
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
        // 构造单调栈，将队列前面比元素小的全给清除掉，保证当前单调递增
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
        // 因为在构造单调栈的时候，相对小的元素已经被弹出
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
```



### [60. n个骰子的点数](https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/)

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

示例 1:

```
输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
```

示例 2:

```
输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
```

#### 解法一（动态规划）

```java
public double[] twoSum(int n) {
  double pre[]={1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};
  for(int i=2;i<=n;i++){
    double tmp[]=new double[5*i+1];
    for(int j=0;j<pre.length;j++)
      for(int x=0;x<6;x++)
        tmp[j+x]+=pre[j]/6;
    pre=tmp;
  }
  return pre;
}
```



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

总是**在股票价格最小的时候买入，之后找到一个卖出时的最大利润。**

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
        // 从map中找到符合的值并且并不是重复值，然后返回下标
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

### [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

#### 解法一（滑动窗口）

利用滑动窗口，调整窗口，在满足无重复字符的情况下窗口最大的长度就是所求值。

**用hashMap保存当前窗口中存在的元素和对应出现的次数**，根据字符出现的次数调整窗口，使其符合条件并更新最大值。

```java
// 时间复杂度O(n)
public int lengthOfLongestSubstring(String s) {
    // 用map保存当前窗口中存在的元素和对应出现的次数
    HashMap<Character, Integer> map = new HashMap<>();
    // 左指针，  右指针，  最大值
    int i = 0 , j = 0, max = 0;
    // 当窗口没有到底时
    while(j < s.length()){
        // 判断当前字符是否已经在窗口中存在
        // 存在次数++，不存在初始为1
        char c = s.charAt(j++);
        if (map.get(c) == null){
            map.put(c, 1);
        }else{
            map.put(c, map.get(c) + 1);
        }
        // 当字符存在次数大于一的时候，
        // 调整窗口左边界，直到元素出现次数为1
        while(map.get(c) > 1){
            char c1 = s.charAt(i++);
            map.put(c1, map.get(c1) - 1);
        }
        // 更新最大值
        max = Math.max(max, j - i);
    }
    return max;
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
        // a 必须要小于 0，否者三数不可能为0
        if (nums[0] > 0) {
            return res;
        }
        // 因为不能包含重复的三元组，所以跳过
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

### [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

注意空字符串可被认为是有效字符串。

#### 解法一（栈）

解决括号问题常用栈，创建一个对应匹配规则的map，遇到左入栈，右出栈并判断是否对应匹配，不匹配返回false。

```java
public boolean isValid(String s) {
    if (s.length() == 0) {
        return true;
    }
    // 方便匹配
    Map<Character, Character> map = new HashMap<>();
    map.put('{', '}');
    map.put('[', ']');
    map.put('(', ')');
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
```



### [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个**无重复元素**的数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的数字可以无限制重复被选取。

说明：

- 所有数字（包括 `target`）都是正整数。
- 解集不能包含重复的组合。 

示例 1：

```
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```

#### 解法一（DFS）

使用回溯法寻找可行解，但是需要注意可以不选择使用这个数并且这个数可以重复使用。

```java
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
```



### [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

详情请点击链接跳转。

#### 解法一（暴力）

直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。

```java
public int trap(int[] height) {
    int ans = 0;
    int size = height.length;
    for (int i = 1; i < size - 1; i++) {
        int max_left = 0, max_right = 0;
        // Search the left part for max bar size
        for (int j = i; j >= 0; j--) { 
            max_left = Math.max(max_left, height[j]);
        }
        // Search the right part for max bar size
        for (int j = i; j < size; j++) { 
            max_right = Math.max(max_right, height[j]);
        }
        ans += Math.min(max_left, max_right) - height[i];
    }
    return ans;
}
```

### [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

给定一个包含非负整数的 *m* x *n* 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```

#### 解法一（动态规划）

1. dp：代表直到走到 (i,j)的最小路径和。
2. 初始值：00、0*、\*0都只有固定路径
3. 转移方程：除开初始值之外，其他的ij都有两种走法，从上或者从左边来，选择较小的一个构成这个ij的最小路径。

遍历路径，求出dp

```java
public int minPathSum(int[][] grid) {
    // 代表直到走到 (i,j)的最小路径和。
    // 可以共用grid，这里是为了方便理解
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

#### 解法一（HashSet）

```java
// 时间复杂度O(n)
public ListNode detectCycle(ListNode head) {
    Set<ListNode> visited = new HashSet<ListNode>();
    ListNode node = head;
    while (node != null) {
        // 包含重复，代表入口
        if (visited.contains(node)) {
            return node;
        }
        visited.add(node);
        node = node.next;
    }
    return null;
}
```

#### 解法二（双指针）

分两个步骤，首先通过快慢指针的方法判断链表是否有环；

接下来找入口，具体的方法为，首先假定链表起点到入环的第一个节点A的长度为a【未知】，到快慢指针相遇的节点B的长度为（a + b）【这个长度是已知的】。现在我们想知道a的值，注意到快指针p2始终是慢指针p走过长度的2倍，所以慢指针p从B继续走（a + b）又能回到B点，如果只走a个长度就能回到节点A。但是a的值是不知道的，解决思路是曲线救国，注意到起点到A的长度是a，**那么可以用一个从起点开始的新指针q和从节点B开始的慢指针p同步走，相遇的地方必然是入环的第一个节点A。**

```java
public ListNode detectCycle(ListNode head) {
    ListNode p = head, p2 = head;
    boolean hasCycle = false;
    while (p2.next != null && p2.next.next != null) {
        p = p.next;
        p2 = p2.next.next;
        if (p == p2) {
            hasCycle = true;
            break;
        }
    }
    // 步骤二：若有环，找到入环开始的节点
    if (hasCycle) {
        ListNode q = head;
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return q;
    } else 
        return null;
}
```

### [146. LRU 缓存机制](https://leetcode-cn.com/problems/lru-cache/)

运用你所掌握的数据结构，设计和实现一个 [LRU (最近最少使用) 缓存机制](https://baike.baidu.com/item/LRU) 。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以正整数作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

**进阶**：你是否可以在 `O(1)` 时间复杂度内完成这两种操作？

#### 解法一

使用HashMap和双向链表实现，链表用来存放元素和具体顺序，map存放key和对应的Node，方便在O(1)获得该元素，典型的空间换时间。

get时，在map中获取这个元素并在双向链表中删除这个元素并将其放置到对头

put时，key不存在的话直接放置到对头并修改value，key存在时则创建Node放入map并放置到对头。

```java
// 
public class LRUCache {
  private Map<Integer, DoubleList> cache = new HashMap<>();
  private int size;
  private int capacity;
  private DoubleList head, tail;

  public LRUCache(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    // 使用伪头部和伪尾部节点
    head = new DoubleList(0, 0);
    tail = new DoubleList(0, 0);
    head.next = tail;
    tail.prev = head;
  }

  public int get(int key) {
    DoubleList node = cache.get(key);
    if (node == null) {
      return -1;
    }
    // 如果 key 存在，先通过哈希表定位，再移到头部
    moveToHead(node);
    return node.v;
  }

  public void put(int key, int value) {
    DoubleList node = cache.get(key);
    if (node == null) {
      // 如果 key 不存在，创建一个新的节点
      DoubleList newNode = new DoubleList(key, value);
      // 添加进哈希表
      cache.put(key, newNode);
      // 添加至双向链表的头部
      addToHead(newNode);
      ++size;
      if (size > capacity) {
        // 如果超出容量，删除双向链表的尾部节点
        DoubleList tail = removeTail();
        // 删除哈希表中对应的项
        cache.remove(tail.k);
        --size;
      }
    } else {
      // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
      node.v = value;
      moveToHead(node);
    }
  }

  // 头插法，修改4个指针
  private void addToHead(DoubleList node) {
    node.prev = head;
    node.next = head.next;
    head.next.prev = node;
    head.next = node;
  }

  // 删除两个指针
  private void removeNode(DoubleList node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void moveToHead(DoubleList node) {
    removeNode(node);
    addToHead(node);
  }

  private DoubleList removeTail() {
    DoubleList res = tail.prev;
    removeNode(res);
    return res;
  }

  class DoubleList {
    int k;
    int v;
    DoubleList prev;
    DoubleList next;

    public DoubleList(int k, int v) {
      k = k;
      v = v;
    }
  }
}
```



### [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

**示例 1:**

```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

#### 解法一（最大堆）

详情参考 剑指offer 最小的k个数

#### 解法二（快排变形）

详情参考 剑指offer 最小的k个数



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
3. 转移方程：用 i 遍历数组得到dp对应的最大值，但是由于子序列并不要求连续，因此用 j 遍历 i 的左边，j 小于 i 代表 i 可以接到 j 的后面，此时可以推出状态转移方程`dp[i] = Math.max(dp[i], dp[j] + 1)`找到 i 子序列中最大的上升长度。

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

### [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/)

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

示例 1：

```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```

#### 解法一（双指针）

首尾双指针两两交换就可以解决

```java
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
```

### [415. 字符串相加](https://leetcode-cn.com/problems/add-strings/)

给定两个字符串形式的非负整数 `num1` 和`num2` ，计算它们的和。

提示：

num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

#### 解法一（双指针）

模拟手写算数，利用双尾部指针，从尾开始计算，并跟手算一样，使用额外变量保存进位。讲每位的结果插入结果数组中，最后逆序输出即可求值。

```java
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
```

### [434. 字符串中的单词数](https://leetcode-cn.com/problems/number-of-segments-in-a-string/)

统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

请注意，你可以假定字符串里不包括任何不可打印的字符。

示例:

```
输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
```

####  解法一（遍历）

判断是一个单词其前面要存在空格。

```java
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
```

### [543. 二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树

```
          1
         / \
        2   3
       / \     
      4   5    
```

返回 **3**, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

#### 解法一（DFS）

知道其最大直径，通过分析题目可知，最大直径一定是一个节点的左右子树的最大深度相加。

```java
int ans543;

public int diameterOfBinaryTree(TreeNode root) {
    if (root == null){
        return 0;
    }
    dfs(root);
    return ans543;
}

private int dfs(TreeNode root){
    // 已经到达叶子节点
    if (root.left == null && root.right == null){
        return 0;
    }
    // 递归左节点并计算left最大深度
    int l = root.left == null ? 0 : dfs(root.left) + 1;
    // 递归右节点并计算right最大深度
    int r = root.right == null ? 0 : dfs(root.right) + 1;
    // 最大直径一定是一个节点的左右子树的最大深度相加
    ans543 = Math.max(ans543, l + r);
    // 返回最长的深度
    return Math.max(l, r);
}
```







## 笔试题目总结

### N 个骰子出现和为 M 的概率

#### 解法一（DFS）

这道题面试官提示说用动态规划，没写出来，其使用DFS可以更好理解。可以不直接求概率，用DFS先求出总共可能的次数，最后在跟总的可能次数相除。

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

### 字符串变形

对于一个给定的字符串，我们需要在线性(也就是O(n))的时间里对它做一些变形。首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把着个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。比如"Hello World"变形后就变成了"wORLD hELLO"。

#### 解法一（常规）

用空格分隔，插入栈中，再从栈中弹出并顺便转换大小写。

```java
public String trans(String string, int n) {
    Stack<String> stack = new Stack<>();
    Arrays.stream(string.split(" ")).forEach(stack::push);
    StringBuilder sb = new StringBuilder();
    while(!stack.isEmpty()){
        String pop = stack.pop();
        for (int i = 0; i < pop.length(); i++) {
            char c = pop.charAt(i);
            // 转换大小写
            if ('a' <= c && c <= 'z'){
                sb.append((char)(c - (char)32));
            }else{
                sb.append((char)(c + 32));
            }
        }
        sb.append(" ");
    }
    return sb.deleteCharAt(sb.length() - 1).toString();
}
```



