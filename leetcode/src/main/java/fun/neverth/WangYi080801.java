package fun.neverth;

import java.util.*;

/**
 * todo
 *
 * @author NeverTh
 * @date 14:53 2020/8/8
 */
public class WangYi080801 {

    private static List<Integer> arrS = new ArrayList<>();
//    private static HashMap<Integer, Boolean> map = new HashMap<>(10 * 5);

    private static void getSC(int n){

        for (int i = 2; i <= n; i++) {

            int flag = 0;
            for (int j = 2; j <= Math.sqrt(i); j++) {

                if (i % j == 0 || j % 3 == 0 || j % 5 == 0){
                    flag = 1;
                    break;
                }
            }
            if (flag == 0){
                arrS.add(i);
//                map.put(i, true);
            }else{
//                map.put(i, false);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = Arrays.stream(arr).max().getAsInt();

        getSC(max);

        int dp[] = new int[max + 1];

        for (int i = 2; i <= max; i++) {
            dp[i] = 1 + dp[i - 2];
        }

        int res = 0;
        for (int i : arr) {
            res += dp[i];
        }

        System.out.println(res);
    }
}
