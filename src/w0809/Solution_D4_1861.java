package w0809;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1861 {
	
	public static int N;
	public static int[][] plain;
	public static boolean[][] visited;
	public static int Max;		// 가장 많이 이동한 칸 갯수
	public static int answer;	// 가장 많이 이동한 칸의 시작 위치
	public static int started;	// 시작한 위치
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0809/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			visited = new boolean[N][N];
			Max = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
				}
			} // end of input
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					started = plain[i][j];	// 시작값 초기화
					visited[i][j] = true;
					DFS(i, j, 0);
					visited[i][j] = false;
				}
			} // end of DFS
			
			sb.append("#").append(testCase).append(" ").append(answer).append(" ").append(Max + 1).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	
	public static void DFS(int r, int c, int count) {
		
		if(count == Max) { // 같은 값이면 더 작은 값 채택
			answer = Math.min(answer, started);
		}
		
		if(count > Max) {	// 큰 값이면 교체
			answer = started;
			Max = count;
		}
		
		// 4방 탐색
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!isPossible(nr, nc)) continue;
			// 정확하게 1 커야함
			if(plain[nr][nc] == plain[r][c] + 1) {
				
				visited[nr][nc] = true;
				DFS(nr, nc, count + 1);
				visited[nr][nc] = false;
			}
		}
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N) && !visited[r][c];
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
