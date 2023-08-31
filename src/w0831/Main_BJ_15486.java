package w0831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_15486 {

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

        dp = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            // 선택하지 않음
            dp[i] = Math.max(dp[i], dp[i - 1]);

            if(i + cost[i][0] <= N) { // 선택함
                dp[i + cost[i][0]] = Math.max(dp[i + cost[i][0]], dp[i] + cost[i][1]);
            }
            // 마지막날은 cost[i][0]이 1인 경우에만 일을 할 수 있음
            else if(i + cost[i][0] == N + 1) {
                dp[i] = Math.max(dp[i], dp[i] + cost[i][1]);
            }
        }

        System.out.println(dp[N]);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
