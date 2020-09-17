package fun.neverth;

import java.util.Scanner;

/**
 * @author NeverTh
 * @date 19:38 2020/9/17
 */
public class JD0917 {
    public void a(){
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int i = 0, j = 0;
        boolean cont = false;
        char[] arr = str.toCharArray();
        while(j < arr.length){
            if (Character.isDigit(arr[j])){
                if (!cont){
                    i = j;
                    cont = true;
                }
            }else{
                if (cont){
                    if (j - i == 4){
                        String substring = str.substring(i, j);
                        int i1 = Integer.parseInt(substring);
                        if (i1 >= 1000 && i1 <= 3999){
                            System.out.print(i1 + " ");
                        }
                    }
                    i = j;
                    cont = false;
                }
            }
            j++;
        }
        if (cont && j - i == 4){
            String substring = str.substring(i, j);
            int i1 = Integer.parseInt(substring);
            if (i1 >= 1000 && i1 <= 3999){
                System.out.println(i1);
            }
        }
    }
    // 定义在这是为了方便运算
    static int a;
    static int b;
    static char[][] arr;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < t; i++) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            scanner.nextLine();
            arr = new char[a][b];
            for (int j = 0; j < a; j++) {
                String s = scanner.nextLine();
                for (int k = 0; k < b; k++) {
                    arr[j][k] = s.charAt(k);
                }
            }
            action(arr);
        }
    }
    static public void action(char[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 'E'){
                    if (dfs(i, j)){
                        System.out.println("YES");
                    }else{
                        System.out.println("NO");
                    }
                }
            }
        }
    }
    static public boolean dfs(int i, int j){
        if (i < 0 || j < 0 || i >= a || j >= b || arr[i][j] == '#'){
            return false;
        }
        if (arr[i][j] == 'S'){
            return true;
        }
        // 防止走回头路
        char tmp = arr[i][j];
        arr[i][j] = '#';
        boolean res = dfs(i + 1, j) || dfs(i - 1, j) || dfs(i, j + 1) || dfs(i, j - 1);
        arr[i][j] = tmp;
        return res;
    }
}
