import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] table;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			
			int n = Math.max(N, M);
			int r = Math.min(N, M);
			table = new int[n + 1][n + 1];
			
			for(int i = 0; i <= n; i++) {
				for(int j = 0; j <= i; j++) {
					if(j == 0 || i == j) table[i][j] = 1;
					else table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
				}
			}
			
			sb.append(table[n][r]).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
