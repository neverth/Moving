package fun.neverth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author NeverTh
 * @date 20:20 2020/9/6
 */
public class Tx090602 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(scanner.nextLine());
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.merge(s, 1, Integer::sum);
        }
        ArrayList<Object[]> arrayList = new ArrayList<>();

        for (String s : map.keySet()) {
            Object[] t = new Object[]{s, map.get(s)};
            arrayList.add(t);
        }
        arrayList.sort((t1, t2) -> {
            if (t1[1] == t2[1]){
                String a = (String)t1[0];
                String b = (String)t2[0];
                for (int i = 0; i < a.length(); i++) {
                    if (i < b.length()){
                        if (a.charAt(i) > b.charAt(i)){
                            return 1;
                        }
                        else if(a.charAt(i) < b.charAt(i)){
                            return -1;
                        }else{
                            continue;
                        }
                    }else{
                        return -1;
                    }
                }
                return 0;

            }else{
                return (int)t2[1] - (int)t1[1];
            }
        });
        for (int i = 0; i < K; i++) {
            Object[] res = arrayList.get(i);
            System.out.println(res[0] + " " + res[1]);
        }

        arrayList.sort((t1, t2) -> {
            if (t1[1] == t2[1]){
                String a = (String)t1[0];
                String b = (String)t2[0];
                for (int i = 0; i < a.length(); i++) {
                    if (i < b.length()){
                        if (a.charAt(i) > b.charAt(i)){
                            return 1;
                        }
                        else if(a.charAt(i) < b.charAt(i)){
                            return -1;
                        }else{
                            continue;
                        }
                    }else{
                        return 1;
                    }
                }
                return 0;
            }else{
                return (int)t1[1] - (int)t2[1];
            }
        });

        for (int i = 0; i < K; i++) {
            Object[] res = arrayList.get(i);
            System.out.println(res[0] + " " + res[1]);
        }
    }
}

//        for (int i = K - 1; i >= 0; i--) {
//            Object[] res = arrayList.get(i);
//            System.out.println(res[0] + " " + res[1]);
//        }
//        PriorityQueue<>
//
//        Object[] valueSorted = map.values().stream().sorted().toArray();
//
//
//        for (Object o : valueSorted) {
//
//        }
//
//        for (int i = 0; i < K; i++) {
//
//        }
//
//        System.out.println();