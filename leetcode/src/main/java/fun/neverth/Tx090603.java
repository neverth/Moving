package fun.neverth;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 20:45 2020/9/6
 */
public class Tx090603 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr1 = Arrays.stream(arr).sorted().toArray();

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr1[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            Integer j = map.get(arr[i]);
            if(j < arr1.length / 2){
                System.out.println(arr1[arr1.length / 2]);
            }else{
                System.out.println(arr1[arr1.length / 2 - 1]);
            }
        }
    }
}
