package fun.neverth;

import java.util.*;

/**
 * 题目描述：
 * 我们希望一个序列中的元素是各不相同的，但是理想和现实往往是有差距的。现在给出一个序列A，其中难免有些相同的元素，现在提供了一种变化方式，使得经过若干次操作后一定可以得到一个元素各不相同的序列。
 * 这个操作是这样的，令x为序列中最小的有重复的数字，你需要删除序列左数第一个x，并把第二个x替换为2*x。
 * 请你输出最终的序列。
 * 例如原序列是[2,2,1,1,1],一次变换后变为[2,2,2,1]，两次变换后变为[4,2,1]，变换结束
 *
 * 输入描述
 * 输入第一行包含一个正整数n，表示序列的长度为n。(1<=n<=50000)  第二行有n个整数，初始序列中的元素。(1<=a_i<=10^8)  输出描述
 * 输出包含若干个整数，即最终变换之后的结果。
 *
 * @author NeverTh
 * @date 2020/7/29 21:58
 */
public class Guanglianda072902 {

    public static void main(String[] args) {

        int[] src = new int[50010];
        int[] ans = new int[50010];

        PriorityQueue<Integer> nums = new PriorityQueue<>();

        ArrayList<PriorityQueue<Integer>> q = new ArrayList<>();
        for (int i = 0; i < 50010; i++) {
            q.add(new PriorityQueue<>());
        }

        HashMap<Integer, Integer> ids = new HashMap<>();
        int index = 0;

        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        String[] s1 = scanner.nextLine().split(" ");

        for (int i = 1; i <= Integer.parseInt(s[0]); i++) {
            src[i] = Integer.parseInt(s1[i - 1]);

            if (ids.get(src[i]) == null){
                ids.put(src[i], ++index);
                nums.add(src[i]);
            }
            q.get(ids.get(src[i])).add(i);
        }

        while (nums.size() > 0){
            int x = nums.poll();
            while(q.get(ids.get(x)).size() >= 2){
                q.get(ids.get(x)).poll();

                if (ids.get(x * 2) == null){
                    ids.put(x * 2, ++index);
                    nums.add(x * 2);
                }
                q.get(ids.get(x * 2)).add(q.get(ids.get(x)).peek());
                q.get(ids.get(x)).poll();

            }

            if (q.get(ids.get(x)).size() > 0){
                ans[q.get(ids.get(x)).peek()] = x;
            }
        }
        for (int i = 1; i <= Integer.parseInt(s[0]); i++) {
            if (ans[i] != 0){
                System.out.print(ans[i] + " ");
            }
        }

    }
}
