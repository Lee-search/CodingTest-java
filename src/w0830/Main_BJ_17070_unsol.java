package w0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_17070_unsol {
	
	static int N, answer;
	static int[][] plain;
	
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
		
		
		System.out.println(answer);
	} // end of main
	
	
	
	public static boolean check(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N) && plain[r][c] == 0;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
