package fun.neverth;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 2020/6/29 15:26
 */
public class DaNei {

    public int[][] merge(int[][] intervals) {
        // 由于已经给定⼀个按开始时间从⼩到⼤排序的时间区间集合
        // 因此不需要对 intervals 进行排序。

        // 创建保存结果的数据
        int[][] res = new int[intervals.length][2];

        // 保存结果数组的index，从-1开始。
        int index = -1;

        for (int[] interval : intervals) {
            /*
             * 如果结果
             * 1. 数组为空
             * 2. 或者当前遍历区间的起始位置值 > 结果数组中最后区间终点位置的值
             * 则不合并，直接将当前区间加入结果数组。
             *
             * 反之将当前遍历区间终点位置值选择最大合并至结果数组的最后区间的终点位置
             */
            if (index == -1 || interval[0] > res[index][1]) {
                // 先自增再赋值
                res[++index] = interval;

            } else {

                res[index][1] = Math.max(res[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, index + 1);
    }

    public boolean valid(String word, String abbr) {
        int m = word.length(), n = abbr.length();

        //双指针，i 是指源字符串， j 是指缩写字符串
        int i = 0, j = 0;

        while (i < m && j < n) {
            // 如果缩写字符串的 j 位置是数字
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {

                // 如果缩写字符串的 j 位置为0则直接返回false
                if (abbr.charAt(j) == '0') {
                    return false;
                }

                // 计算数字的大小
                int value = 0;
                while (j < n && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    value = value * 10 + abbr.charAt(j++) - '0';

                }

                // 跳过源字符串中的 value 个字符
                i += value;

            } else {
                // 不相等，直接返回false
                if (word.charAt(i++) != abbr.charAt(j++)) {
                    return false;

                }
            }
        }

        return i == m && j == n;
    }

    @Test
    public void mergeTest() {

        int[][] input1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] output1 = {{1, 6}, {8, 10}, {15, 18}};
        assertEquals(output1, new DaNei().merge(input1));

        int[][] input2 = {{1, 4}, {4, 5}};
        int[][] output2 = {{1, 5}};
        assertEquals(output2, new DaNei().merge(input2));

    }

    @Test
    public void validTest() {

        assertTrue(new DaNei().valid("internationalization", "i12iz4n"));

        assertFalse(new DaNei().valid("apple", "a2e"));

    }

}
