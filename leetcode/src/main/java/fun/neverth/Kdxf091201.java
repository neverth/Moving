package fun.neverth;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 19:00 2020/9/12
 */
public class Kdxf091201 {
    public void four() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = scanner.nextInt();
        int len = str.length();
        char[] chars = str.toCharArray();
        char[] newChars = new char[str.length()];
        for (int i = 0; i < chars.length; i++) {
            int j = i - (k % len);
            if (j < 0) {
                j = len + j;
            }
            newChars[j] = chars[i];
        }
        System.out.println(newChars);
    }

    public void two() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public void one() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    if (i == k) {
                        continue;
                    }
                    for (int l = 0; l < arr[0].length; l++) {
                        if (j == l) {
                            continue;
                        }
                        max = Math.max(max, arr[i][j] * arr[k][l]);
                    }
                }
            }
        }
        System.out.println(max);
    }

    public void three() {
        Scanner scanner = new Scanner(System.in);
        String s = Long.toBinaryString(scanner.nextLong());
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                res++;
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static public void quickSort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            while (array[j] >= base && i < j) {
                j--;
            }

            while (array[i] <= base && i < j) {
                i++;
            }

            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        array[left] = array[i];
        array[i] = base;

        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }
}
