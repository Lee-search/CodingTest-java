package w0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
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
	
	public static void Z(int r, int c, int n) {
		
		System.out.println(r + " " + c + " " + n);
		
		if(n == 1) {
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr == R && nc == C) {
					System.out.println(count);
					System.exit(0);
				}
				count += 1;
			} // end of for
			
			return;
		} // end of 기저조건
		
		int nr = r / 2 + (n / 2);
		int nc = c / 2 + (n / 2);
		
		// 속해있으면 탐색하고 아니면 그냥 count만 계산
		if(!isCombine(r / 2, c / 2, n / 2)) count += n / 2;
		else Z(r / 2, c / 2, n / 2);
		System.out.println("count : " + count);
		
		if(!isCombine(r / 2, nc, n / 2)) count += n / 2;
		else Z(r / 2, nc, n / 2);
		System.out.println("count : " + count);
		
		if(!isCombine(nr, c / 2, n / 2)) count += n / 2;
		else Z(nr, c / 2, n / 2);
		System.out.println("count : " + count);
		
		if(!isCombine(nr, nc, n / 2)) count += n / 2;
		else Z(nr, nc, n / 2);
		System.out.println("count : " + count);
	} // end of func
	
	public static boolean isCombine(int r, int c, int n) {
		System.out.println(r + " <= R < " + (r + n) + " " + c + " <= C < " + (c + n));
		return (r <= R && R < r + n && c <= C && C < c + n);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
