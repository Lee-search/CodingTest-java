import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] cost;
    static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        cost = new int[N + 1][2];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = stoi(st.nextToken());
            int p = stoi(st.nextToken());
            cost[i] = new int[] {t, p}; // 해당 일에 드는 시간과 얻을 수 있는 비용
        } // end of init

        dp = new int[N + 2];
        for(int i = 1; i <= N; i++) {

            int t = cost[i][0]; // 기간
            int p = cost[i][1]; // 금액

            // 선택하지 않음
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 선택함
            if(i + t <= N) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + p);
            }
            // 마지막날은 t가 1인 경우에만 일을 할 수 있음
            else if(i + t == N + 1) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + p);
            }
        }

        System.out.println(dp[N + 1]);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
