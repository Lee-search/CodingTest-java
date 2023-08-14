package w0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2630 {
	
	static int N, whites, greens;
	static int[][] plain;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		plain = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				plain[i][j] = stoi(st.nextToken());
			}
		} // end of init
		
		divide(0, 0, N);
		System.out.println(whites);
		System.out.println(greens);
		
	} // end of main
	
	public static void divide(int r, int c, int size) {
		
		int sum = 0;
		
		// 분할 부분이 (r, c) ~ (r + size, c + size) 임에 유의
		for(int i = r; i < r + size; i++) {
			for(int j = c; j < c + size; j++) {
				sum += plain[i][j];
			}
		}
		
		if(sum == 0) {
			whites += 1;
		}
		else if(sum == size * size) {
			greens += 1;
		}
		else {	// 어느 한 종이가 0, 1로 통일되지 않는 경우
			int half = size / 2;
			divide(r, c, half);
			divide(r, c + half, half);
			divide(r + half, c, half);
			divide(r + half, c + half, half);
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
