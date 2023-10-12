package w1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기 2: https://www.acmicpc.net/problem/17069
 * DP문제, 3차원 배열로 이전에서 해당 위치로 올 수 있는 경우 (최대 3가지) 탐색
 */

public class Main_BJ_17069 {

    static int N;
    static int[][] plain;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];
        dp = new long[N][N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = stoi(st.nextToken());
                if(n != 0) plain[i][j] = n;
            }
        } // end of init

        dp[0][1][0] = 1;

        // 0: 가로배치, 1: 대각선배치, 2: 세로배치
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {

                // 이동하고자 하는 곳이 벽이면 제외
                if(plain[i][j] == 1) continue;

                // 가로로 놓아지는 경우
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

                // 첫번째 줄의 경우 가로 배치만 가능
                if(i == 0) continue;

                // 세로로 놓아지는 경우
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
                
                // 대각선으로 놓아지는 경우
                if(plain[i][j - 1] == 1 || plain[i - 1][j] == 1) continue;
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        } // end of for

        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
