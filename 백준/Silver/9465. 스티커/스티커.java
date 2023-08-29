import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] cost, dp;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = stoi(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {

            N = stoi(br.readLine());
            cost = new int[N * 2];
            dp = new int[N * 2];

            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    cost[i + 2 * j] = stoi(st.nextToken()); // 2 -> 1 차원축소
                }
            } // end of init

            if(N == 1) {
                System.out.println(Math.max(cost[0], cost[1]));
                continue;
            } // N이 1이면 두 경우중 큰 값 출력 후 다음 tc진행

            dp[0] = cost[0];
            dp[1] = cost[1];
            dp[2] = dp[1] + cost[2];
            dp[3] = dp[0] + cost[3];

            for(int i = 4; i < 2 * N; i++) {

                if(i % 2 == 0) dp[i] = Math.max(dp[i - 1], dp[i - 3]) + cost[i];
                else dp[i] = Math.max(dp[i - 3], dp[i - 5]) + cost[i];
            }

            System.out.println(Math.max(dp[2 * N - 1], dp[2 * N - 2]));
        } // end of tc

    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
