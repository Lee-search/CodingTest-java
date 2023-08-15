package w0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1074 {
	
	static int N, R, C, count;
	static int[] dr = {0, 0, 1, 1};
	static int[] dc = {0, 1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		count = 0;
		
		Z(0, 0, (int)Math.pow(2, N));
	} // end of main
	
	public static void Z(int r, int c, int n_pow) {
		
		if(n_pow == 2) {
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr == R && nc == C) {
					System.out.println(count);
					System.exit(0);
				}
				count += 1;
			} return;
		} // end of basis
			
		// 제곱 승 -= 1
		n_pow /= 2;
		
		// 범위에 포함되면 부분 탐색, 그 외에는 count 만 수행
		for(int nr : new int[] {r, r + n_pow}) {
			for(int nc : new int[] {c, c + n_pow}) {
				if(!isCombine(nr, nc, n_pow)) {
					count += n_pow * n_pow;
					continue;
				}
				Z(nr, nc, n_pow);
			}
		}
	} // end of func
	
	public static boolean isCombine(int r, int c, int n_pow) {
		return (r <= R && R < r + n_pow && c <= C && C < c + n_pow);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
