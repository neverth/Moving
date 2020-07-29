package fun.neverth;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 杰夫非常喜欢种草，他自己有一片草地，为了方便起见，我们把这片草地看成一行从左到右，并且第 i 个位置的草的高度是hi。
 * 杰夫在商店中购买了m瓶魔法药剂，每瓶魔法药剂可以让一株草长高x，杰夫希望每次都能有针对性的使用药剂，也就是让当前长得最矮的小草尽量高，现在杰夫想请你告诉他在使用了m瓶魔法药剂之后，最矮的小草在这种情况下最高能到多少。
 *
 * 输入描述
 * 第一行三个整数n, m, x分别表示小草的个数，魔法药剂的个数以及每瓶魔法药剂让小草长高的高度。(1≤n,m,x≤1e5)  第二行n个整数分别表示第i株小草的高度ai。(1≤ai≤1e9)  输出描述
 * 使用了m瓶药剂之后最矮的小草的最高高度。
 *
 * @author NeverTh
 * @date 2020/7/29 20:58
 */
public class Guanglianda072901 {

    static PriorityQueue<Integer> mixHead = new PriorityQueue<>();

    static public Integer maxRes(int n, int m, int x, int[] hight) {
        for (int i = 0; i < hight.length; i++) {
            mixHead.add(hight[i]);
        }

        for (int i = 0; i < m; i++) {
            Integer poll = mixHead.poll();
            mixHead.add(poll + x);
        }

        return mixHead.poll();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        String[] s1 = scanner.nextLine().split(" ");
        int[] s2 = new int[s1.length];

        for (int i = 0; i < s1.length; i++) {
            s2[i] = Integer.parseInt(s1[i]);
        }

        Integer integer = maxRes(
                Integer.parseInt(s[0]),
                Integer.parseInt(s[1]),
                Integer.parseInt(s[2]),
                s2
        );
        System.out.println(integer);
    }
}
