package fun.neverth;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 9:51 2020/9/13
 */
public class Meituan091301 {

    public void a() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
//        int n = 4;
//        int m = 3;
//        int[][] arr = new int[][]{
//                {1, 0, 1},
//                {2, 0, 1},
//                {2, 0, 1},
//                {1, 0, 1}
//        };
        if (n % 2 == 0) {
            int g = n;
            while(g / 2 != 0){
                if (Arrays.equals(arr[g / 2], arr[g / 2 - 1])){
                    g /= 2;
                }else{
                    break;
                }
            }
            for (int i = 0; i < g; i++) {
                for (int i1 = 0; i1 < arr[i].length; i1++) {
                    System.out.print(arr[i][i1] + " ");
                }
                System.out.print("\n");
            }
        } else {
            for (int i = 0; i < n; i++) {
                for (int i1 = 0; i1 < m; i1++) {
                    System.out.print(arr[i][i1] + " ");
                }
                System.out.print("\n");
            }
        }
    }

    public void b() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
//        int n = 8;
//        int m = 2;
//        int k = 5;
//        int[] arr = new int[]{5, 5, 5, 4, 5, 5, 5, 5};
        int left = 0, right = 0;
        int res = 0;
        boolean flag = true;
        for (int i = 0; i < m; i++) {
            right++;
            if (arr[i] < k) {
                flag = false;
            }
        }
        if (flag) {
            res++;
        }
        for (int i = m; i < n; i++) {
            left++;
            right++;
            flag = true;
            for (int j = left; j < right; j++) {
                if (arr[j] < k) {
                    flag = false;
                }
            }
            if (flag) {
                res++;
            }
        }
        System.out.println(res);
    }
    static int res = 0;
    static int k = 2;
    static public void sum(int target, int left, int right){
        if (target <= 2){
            return;
        }
        int i = left, j = right;
        while (i <= j) {
            if (i + j == target) {
                if (j != i){
                    res += 2;
                }else{
                    res += 1;
                }
                i++;
                j--;
            } else if (i + j > target) {
                j--;
            } else {
                i++;
            }
        }
        for (int k = 1; k <= right; k++) {
            sum(target - k, left, right);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int k = 3;
        int d = 2;

        sum(n, 1, 3);
        System.out.println();
    }

    public void c(){
        int n = 5;
        int k = 3;
        int d = 2;

        sum(n, 0, 3);
        System.out.println();
    }


}
