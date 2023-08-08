package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	
	public static int N;
	public static char[][] plain;
	public static boolean[][] visited;
	public static int[][] mapper;	// 탐색 끝난 부분 표기
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream(new File("./src/w0808/input_4.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			plain = new char[N][N];
			visited = new boolean[N][N];
			mapper = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				for(int j = 0; j < N; j++) {
					plain[i][j] = line.charAt(j);
					if(plain[i][j] == '*') {
						mapper[i][j] = -1;	// 지뢰는 -1로 표기
					}
				}
			} // end of input
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 클릭할 수 있는 곳이고, 기존에 밝혀지지 않은 위치라면 -> 탐색
					if(plain[r][c] == '.' && mapper[r][c] == 0) {
						DFS(r, c, 1);
					}
				}
			}
			
			
			
			
			
			sb.append("#").append(testCase).append(" ");
		} // end of tc
		
	} // end of main
	

	public static void DFS(int r, int c, int cnt) {
		
		
		
	}
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
