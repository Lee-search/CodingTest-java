package w0829;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페_unsol {
	
	static int N, answer, localAnswer;
	static int[][] plain;
	static int[] deserts;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream(new File("./src/w0829/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= 1; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			answer = -1;
			
			for(int i = 0; i < N; i++) {
				
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
				}
			} // end of init
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					localAnswer = 0;
					sr = i; sc = j;
					deserts = new int[101];
					deserts[plain[i][j]] += 1;	// 시작 좌표 먹고 시작
					DFS(i, j, 0, 1);
					answer = Math.max(answer, localAnswer);
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.print(sb);
	} // end of main
	
	public static int[] dr = {1, 1, -1, -1};
	public static int[] dc = {-1, 1, 1, -1}; // 반시계방향
	
	public static int sr, sc;
	
	/**
	 * @param r
	 * @param c
	 * @param d : 직전까지 이동했던 방향
	 * @param cnt : 지금까지 이동한(먹은) 갯수
	 */
	public static void DFS(int r, int c, int d, int cnt) {
		
		if(sr == r && sc == c && d == 3) {
			localAnswer = Math.max(localAnswer, cnt);
			return;
		} // basis
		
		for(int nd = d; nd < d + 2; nd++) {
			
			if(nd == 4) break;
			
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			if(isPossible(nr, nc) && deserts[plain[nr][nc]] == 0) {
				deserts[plain[nr][nc]] += 1;
				System.out.println(nr + " " + nc);
				DFS(nr, nc, nd, cnt + 1); // 시계방향으로 꺾음
				deserts[plain[nr][nc]] -= 1;
			}
		}

	} // end of DFS
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
