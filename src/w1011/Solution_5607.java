package w1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5607 {

	static int N, R, P = 1234567891;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			sb.append("#" + testCase + " " + nCr() + "\n");
		}
		
		System.out.println(sb.toString());
	} // end of main
	
	public static long nCr() {
		
		if(R == 0)
			return 1L;
		
		long[] fact = new long[N + 1];
		fact[0] = 1;
		
		for(int i = 1; i <= N; i++)
			fact[i] = fact[i - 1] * i % P;
		
		return (fact[N] * power(fact[N - R], P - 2) % P * power(fact[R], P - 2) % P) % P;
	}
	
	public static long power(long x, long y) {
		
		long res = 1L;
		
		x = x % P;
		while(y > 0) {
			if(y % 2 == 1) 
				res = (res * x) % P;
			y = y >> 1;
			x = (x * x) % P;
		}
		
		return res;
	}
}
