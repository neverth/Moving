package fun.neverth;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        int[] a = {1, 3, 4, 2, 2};
        String[] b = {"0000"};

        Common.TreeNode n1 = new Common.TreeNode(3);
        Common.TreeNode n2 = new Common.TreeNode(9);
        Common.TreeNode n3 = new Common.TreeNode(20);
        Common.TreeNode n4 = new Common.TreeNode(15);
        Common.TreeNode n5 = new Common.TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.println(part2.checkInclusion("adc", "edacr"));

    }
}
