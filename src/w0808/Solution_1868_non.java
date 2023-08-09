package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1868_non {
	
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
			
			int count = 1;
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 클릭할 수 있는 곳이고, 기존에 밝혀지지 않은 위치라면 -> 탐색
					if(plain[r][c] == '.' && mapper[r][c] == 0) {
						DFS(r, c, count++);
					}
				}
			}
			
			
			for(int i = 0; i < mapper.length; i++) System.out.println(Arrays.toString(mapper[i]));
			
			
			sb.append("#").append(testCase).append(" ");
		} // end of tc
		
	} // end of main

	public static void DFS(int r, int c, int cnt) {
		
		if(plain[r][c] == '*') {
			return;
		}
		
		if(mapper[r][c] != 0) {
			return;
		}
		
		mapper[r][c] = cnt;
		plain[r][c] = 0;
		
		// (r, c) = 클릭할 수 있는 곳이고, 기존에 밝혀지지 않은 위치여야함
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isPossible(nr, nc) && plain[nr][nc] != 0) {
				DFS(nr, nc, cnt);
			}
		}
		
		
	} // end of dfs
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
