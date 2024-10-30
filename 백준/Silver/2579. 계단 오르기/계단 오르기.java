import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N + 1];
        // dp[i][j]: j개의 계단을 연속해서 밟은 경우, i번째 계단까지의 최대값
        int[][] dp = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }
        
        if(N == 1) {    // stair[2] 접근시 에러 발생
            System.out.println(stair[1]);
            return;
        }

        dp[1][1] = stair[1];
//        dp[1][2] = 0;
        dp[2][1] = stair[2];
        dp[2][2] = stair[1] + stair[2];

        for (int i = 3; i <= N; i++) {
            // j=1이면 i-2에서 온 경우임
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + stair[i];
            // j=2이면 i-1에서 온 경우임
            dp[i][2] = dp[i - 1][1] + stair[i];
        }

        System.out.println(Math.max(dp[N][1], dp[N][2]));
    }
}
