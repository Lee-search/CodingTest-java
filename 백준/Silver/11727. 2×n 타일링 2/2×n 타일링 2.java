import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[] dp = new int[1001];

    /** initialize */
    public static void initial() throws Exception {
        n = Integer.parseInt(br.readLine());
        dp[1] = 1;
        dp[2] = 3;
    }

    /** dp */
    public static void fillDp() {
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }
    }

    public static void main(String[] args) throws Exception {
        initial();
        fillDp();
        System.out.println(dp[n]);
    }
}
