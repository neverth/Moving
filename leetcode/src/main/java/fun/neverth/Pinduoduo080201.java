package fun.neverth;


import java.util.Scanner;

public class Pinduoduo080201 {

    static private int K = 10;
    static private int N = 4;
    static private int TIMES = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line1 = in.nextLine();

        K = Integer.parseInt(line1.split(" ")[0]);
        N = Integer.parseInt(line1.split(" ")[1]);
        int[] arr = new int[N];

        String[] line2 = in.nextLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line2[i]);
        }

        for (int i = 0; i < N; i++) {
            if(change(arr[i]) == 0 && i != N - 1){
                System.out.println("paradox");
                return;
            }
        }
        System.out.println(K + " " + TIMES);
    }
    static public int change(int a){
        K -= a;
        if (K == 0){
            return 0;
        }else if(K < 0){
            TIMES++;
            K = Math.abs(K);
        }

        return -1;
    }
}
