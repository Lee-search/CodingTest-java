package w0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_11660_구간합구하기5 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//		OutputStreamWriter osr = new OutputStreamWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] plain = new int[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= n; j++) {
				plain[i][j] = plain[i][j - 1] + Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(plain[i]));
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int st_r = Integer.parseInt(st.nextToken());
			int st_c = Integer.parseInt(st.nextToken());
			int end_r = Integer.parseInt(st.nextToken());
			int end_c = Integer.parseInt(st.nextToken());
			
			int answer = 0;
			for(int h = st_r; h <= end_r; h++) {
				answer += plain[h][end_c] - plain[h][st_c - 1];
			}
			sb.append(answer).append("\n");
//			osr.write(answer + "\n");
		}
		System.out.println(sb);
//		osr.flush();
	}
}
