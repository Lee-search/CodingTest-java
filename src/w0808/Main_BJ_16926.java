package w0808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16926 {
	
	public static int N, M, R;
	public static int[][] plain;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());	// 회전 수
		
		plain = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				plain[i][j] = stoi(st.nextToken());
			}
		} // end of input
		
		for(int i = 0; i < R; i++) {
			rotate();
		}
		
		print();
	} // end of main
	
	public static void rotate() {
		
		// 외곽 -> 내부 순으로 회전
		for(int i = 0; i < Math.min(N,  M) / 2; i++) {
			int start_r = i, start_c = i;
			int end_r = (N - 1) - i, end_c = (M - 1) - i;
			
			// 좌상단 시작 값 미리 저장
			int temp = plain[start_r][start_c];
			
			// (0, 0) <- (0, M - 1)
			for(int c = start_c; c < end_c; c++) {
				plain[start_r][c] = plain[start_r][c + 1];
			}
			
			// (0, M - 1) <- (N - 1, M - 1)
			for(int r = start_r; r < end_r; r++) {
				plain[r][end_c] = plain[r + 1][end_c];
			}
			
			// (N - 1, M - 1) <- (N - 1, 0)
			for(int c = end_c; c > start_c; c--) {
				plain[end_r][c] = plain[end_r][c - 1];
			}
			
			// (N - 1, 0) <- (0, 0)
			for(int r = end_r; r > start_r; r--) {
				plain[r][start_c] = plain[r - 1][start_c];
			}
			
			plain[start_r + 1][start_c] = temp;
		}
	} // end of func
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(plain[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
