package w0829;

import java.util.Arrays;
import java.util.Scanner;

public class Main_BJ_1463_1로만들기 {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		dp[1] = 0;
		
		for(int i = 2; i <= N; i++) {
			
			if(i % 3 == 0) {
				dp[i] = dp[i] == -1 ? dp[i / 3] + 1 : Math.min(dp[i], dp[i / 3] + 1);
			}
			if(i % 2 == 0) {
				dp[i] = dp[i] == -1 ? dp[i / 2] + 1 : Math.min(dp[i], dp[i / 2] + 1);
			}
			dp[i] = dp[i] == -1 ? dp[i - 1] + 1 : Math.min(dp[i], dp[i - 1] + 1);
		}
		
		System.out.println(dp[N]);
	}
}
