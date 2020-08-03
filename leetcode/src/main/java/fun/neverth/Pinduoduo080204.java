package fun.neverth;

public class Pinduoduo080204 {

    static int N = 5;
    static int M = 1;
    static int T = 9;

    public static void main(String[] args) {
        int[][] ns = {{0, 0}, {9, 1}, {4, 9}, {3, 1}, {2, 3}, {6, 5}};
        int[][] ms = {{0, 0}, {9, 8}};

        int[][][] dp = new int[N + 1][M + 1][T + 1];



        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= N; j++) {

                int t = T - ns[j][1];

                if (t <= 0){
                    dp[j][0][t] = ns[j][0];
                }

                for (int k = 1; k <= M; k++) {

                    dp[j][k][t] = dp[j][0][t] + ms[k][0];

                    if (t <= 0){
                        dp[i][j][T] = dp[i][j][0] + ns[i][0];

                    }else{
                        dp[i][j][T] = dp[i][j][T - ns[i][1]] + ns[i][0];
                    }

                }
            }
        }

        for (int i = 1; i <= N; i++) {
            dp[i][0][T] = dp[i][0][T - ns[i][1]] + ns[i][0];

        }

    }
}
