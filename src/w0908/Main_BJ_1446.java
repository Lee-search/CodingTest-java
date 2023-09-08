package w0908;

/**
 * 지름길 : https://www.acmicpc.net/problem/1446
 * DP, D가 최대 10_000, 40_000bytes -> 40KB이므로 DP풀이 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_1446 {

    static final int INF = Integer.MAX_VALUE;
    static int N, D;
    static int[] dp;
    static List<int[]> routes;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        D = stoi(st.nextToken());

        routes = new ArrayList<>();  // 갈 수 있는 지름길
        dp = new int[D + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int cost = stoi(st.nextToken());
            if(b > D) continue;     // 역주행 불가능하므로 D보다 멀리가는 선택지는 배제
            routes.add(new int[]{a, b, cost});  // 출발지, 도착지, 비용
        } // end of init

        for (int i = 0; i < D; i++) {
            // 지름길이 있으면 해당 지름길을 거쳐감
            for (int j = 0; j < routes.size(); j++) {
                if(routes.get(j)[0] == i) {

                    int dest = routes.get(j)[1];
                    int cost = routes.get(j)[2];
                    dp[dest] = Math.min(dp[dest], dp[i] + cost);
                }
            }

            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
        }

        System.out.println(dp[D]);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
