package fun.neverth;

import java.util.*;

/**
 * @author NeverTh
 * @date 19:59 2020/9/20
 */
public class Duxiaoman0920 {

    static char[][] arr;
    static int n;
    static int m;
    static int min;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            arr = new char[n][m];
            min = Integer.MAX_VALUE;
            scanner.nextLine();
            for (int j = 0; j < n; j++) {
                String str = scanner.nextLine();
                for (int k = 0; k < m; k++) {
                    arr[j][k] = str.charAt(k);
                }
            }
            action();
        }
    }

    public static void action() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == '@') {
                    dfs(i, j, 0);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void dfs(int i, int j, int len) {
        if (i < 0 || j < 0 || i >= n || j >= m) {
            min = Math.min(min, len);
            return;
        }
        if (arr[i][j] == '#' || len >= min) {
            return;
        } else if (arr[i][j] == '*') {
            len++;
        }
        // 防止走回头路
        char tmp = arr[i][j];
        arr[i][j] = '#';
        dfs(i + 1, j, len);
        dfs(i - 1, j, len);
        dfs(i, j + 1, len);
        dfs(i, j - 1, len);
        arr[i][j] = tmp;
    }
}
