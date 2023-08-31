package w0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파이프 옮기기 1 : https://www.acmicpc.net/problem/17070
 * 3차원 DP 테이블을 써서, 해당 위치까지 도달한 경우를
 * 가로(0), 대각선(1), 세로(2) 경로를 거쳐 온 3가지 경우의 합으로 표현
 * (idea: https://buddev.tistory.com/36)
 */

public class Main_BJ_17070_파이프옮기기1 {
	
	static int N, answer;
	static int[][] plain;
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		plain = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int n = stoi(st.nextToken());
				if(n != 0) plain[i][j] = n;
			}
		} // end of init
		
		dp = new int[N][N][3];
		dp[0][1][0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 2; j < N; j++) {
				// 벽이면 생략
				if(plain[i][j] == 1) continue;
				// 가로로 이동
				dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];

				// 첫 행에 있으면 가로 파이프만 놓을 수 있음
				if(i == 0) continue;
				// 세로로 이동
				dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];

				// 대각선의 경우 위쪽 헹과 왼쪽 열이 벽인지 확인해야함
				if(plain[i - 1][j] == 1 || plain[i][j - 1] == 1) continue;
				// 대각선으로 이동
				dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
			}
		}

//		for(int i = 0; i < 3; i++) {
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < N; c++) {
//					System.out.print(dp[r][c][i]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	} // end of main
	
	public static boolean check(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N) && plain[r][c] == 0;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
