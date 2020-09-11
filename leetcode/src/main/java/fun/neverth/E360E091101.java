package fun.neverth;

import java.util.ArrayList;

/**
 * @author NeverTh
 * @date 19:54 2020/9/11
 */
public class E360E091101 {
//    public boolean valid(String str) {
//        Scanner scanner = new Scanner(System.in);
//        ArrayList<String > strings = new ArrayList<>();
//        while (scanner.hasNextLine()){
//            strings.add(scanner.nextLine());
//        }
//        strings.forEach(str -> {
//            boolean nums = str.matches(".*\\d+.*");
//            boolean dx = str.matches(".*[A-Z]+.*");
//            boolean xx = str.matches(".*[a-z]+.*");
//            boolean ts = str.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*");
//            if (nums && dx && xx && ts && str.length() >= 8){
//                System.out.println("Ok");
//            }else{
//                System.out.println("Irregular password");
//            }
//        });
//    }

    public static void main(String[] args) {

        int n = 3, m = 2;
        int[][] arr = new int[][]{{1, 1}, {2, 0}};
//        HashMap<Integer, int[]> map = new HashMap<>();
//        map.put(1, new int[]{1, 0});
//        map.put(2, new int[]{0, 1});
        ArrayList<Integer> res = new ArrayList<>();

        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            for (int i1 = 0; i1 < arr.length; i1++) {
                if (i == arr[i1][0]){
                    if (arr[i1][1] == 0){
                        if (i1 == arr.length - 1){
                            res.add(i);
                        }else{
                            break;
                        }
                    }else{
                        if (i1 == 0){
                            res.add(i);
                        }else{
                            break;
                        }
                    }
                }
            }

        }

    }
}
