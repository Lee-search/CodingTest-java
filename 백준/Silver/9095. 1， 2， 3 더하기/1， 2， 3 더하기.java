import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[12];     // 조건: 11보다 작음

        dp[1] = 1;  // 1
        dp[2] = 2;  // 1,1 / 2
        dp[3] = 4;  // 1,1,1 / 2,1 / 1,2 / 3

        for (int i = 4; i < 12; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            System.out.println(dp[Integer.parseInt(br.readLine())]);
        }
    }
}
