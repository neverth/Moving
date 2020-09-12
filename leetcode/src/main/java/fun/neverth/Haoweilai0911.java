package fun.neverth;

/**
 * @author NeverTh
 * @date 14:04 2020/9/12
 */
public class Haoweilai0911 {
    public int climbStairs (int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n >= 2) {
            dp[2] = 2;
        }
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Haoweilai0911 haoweilai0911 = new Haoweilai0911();

        int i = haoweilai0911.climbStairs(1);
    }
}
