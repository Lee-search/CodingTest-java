package tmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	
	public static int R, C, T;
	public static int[][] plain;
	public static int[][] cleaner;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		T = stoi(st.nextToken());
		plain = new int[R][C];
		cleaner = new int[2][2];	// 클리너 위,아래
		
		int t = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				plain[i][j] = stoi(st.nextToken());
				if(plain[i][j] == -1) {
					cleaner[t++] = new int[] {i, j};
				}
			}
		} // end of input
		
		System.out.println("---확산전---");
		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
		
		for(int i = 0; i < T; i++) {
			// 탐색 후 퍼트리기
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(plain[r][c] > 0) {
						spread(r, c);	// 미세먼지 퍼짐
					}
				}
			}
			
		}
		System.out.println("---확산후---");
		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
		
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void spread(int r, int c) {
		
		int[][] cPlain = new int[R][C];
		
		
		
		int value = plain[r][c] / 5;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];	// 맵밖 or 클리너랑 만나면 확산 X
			
			if(isPossible(nr, nc) && plain[nr][nc] != -1) {
				plain[r][c] -= value;
				plain[nr][nc] += value;	// 옆으로 확산
			}
		}
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < R && 0 <= c && c < C);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
