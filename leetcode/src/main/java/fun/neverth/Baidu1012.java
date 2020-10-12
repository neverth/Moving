package fun.neverth;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 19:04 2020/10/12
 */
public class Baidu1012 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        String arr = scanner.nextLine();
        final char[] chars = arr.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) != null){
                map.put(chars[i], i);
            }else{
                map.put(chars[i], -1);
            }
        }

        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            int a = map.get(chars[i]);
            if (a != -1 && (i + 1 != chars.length)){
                int b = map.get(chars[i + 1]);
                if (b != -1){
                    if (a < b){
                    }else{
                        i = a;
                    }
                }else{
                    i = a;
                }
            }

            res++;
        }
        System.out.println(res);
    }
}
