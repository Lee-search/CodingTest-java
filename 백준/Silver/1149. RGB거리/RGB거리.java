import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] cost;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		cost = new int[3 * N];
		dp = new int[3 * N];
		
		for(int i= 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
				cost[3 * i + j] = stoi(st.nextToken());
		} // end of init
		
		dp[0] = cost[0];
		dp[1] = cost[1];
		dp[2] = cost[2];
		
		for(int i = 3; i < N * 3; i++) {
			
			if(i % 3 == 0) dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
			else if(i % 3 == 1) dp[i] = Math.min(dp[i - 2], dp[i - 4]) + cost[i];
			else if(i % 3 == 2) dp[i] = Math.min(dp[i - 4], dp[i - 5]) + cost[i];
			
//			System.out.println(i + ", dp: " + Arrays.toString(dp));
		}
		
		System.out.println(Math.min(Math.min(dp[3 * N - 1], dp[3 * N - 2]), dp[3 * N - 3]));
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
