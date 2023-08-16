package w0814;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution_1953 {
	
	static int N, M, R, C, L, answer;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int[][] plain;
	static boolean[][] visited;
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream(new File("./src/w0814/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());	// 세로크기
			M = stoi(st.nextToken());	// 가로크기
			R = stoi(st.nextToken());	// 맨홀 세로 위치
			C = stoi(st.nextToken());	// 맨홀 가로 위치
			L = stoi(st.nextToken());	// 탈출 후 소요 시간
			
			plain = new int[N][M];
			visited = new boolean[N][M];
			answer = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					plain[i][j] = stoi(st.nextToken());
				}
			} //숫자 1 ~ 7은 해당 위치의 터널 구조물 타입, 숫자 0 은 터널이 없는 장소
			
			BFS(R, C);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static boolean isMovable(int r, int c, int d, int nr, int nc) { // 이동 가능 검증
		int before = plain[r][c];
		int after = plain[nr][nc];
		
		if(before == 1) {	// 십자터널
			if(d == 0 && (after == 2 || after == 5 || after == 6 || after == before)) return true;	// 위
			if(d == 1 && (after == 3 || after == 6 || after == 7 || after == before)) return true;	// 우측
			if(d == 2 && (after == 2 || after == 4 || after == 7 || after == before)) return true;	// 아래
			if(d == 3 && (after == 3 || after == 4 || after == 5 || after == before)) return true; // 좌측
		}
		else if(before == 2) {	// 상하터널
			if(d == 0 && (after == 1 || after == 5 || after == 6 || after == before)) return true;	// 위
			if(d == 2 && (after == 1 || after == 4 || after == 7 || after == before)) return true;	// 아래
		}
		else if(before == 3) {	// 좌우터널
			if(d == 1 && (after == 1 || after == 6 || after == 7 || after == before)) return true;	// 우측
			if(d == 3 && (after == 1 || after == 4 || after == 5 || after == before)) return true; // 좌측
		}
		else if(before == 4) {	// 상우터널
			if(d == 0 && (after == 1 || after == 2 || after == 5 || after == 6)) return true;	// 위
			if(d == 1 && (after == 1 || after == 3 || after == 6 || after == 7)) return true;	// 우측
		}
		else if(before == 5) {	// 하우터널
			if(d == 1 && (after == 1 || after == 3 || after == 6 || after == 7)) return true;	// 우측
			if(d == 2 && (after == 1 || after == 2 || after == 4 || after == 7)) return true;	// 아래
		}
		else if(before == 5) {	// 하우터널
			if(d == 1 && (after == 1 || after == 3 || after == 6 || after == 7)) return true;	// 우측
			if(d == 2 && (after == 1 || after == 2 || after == 4 || after == 7)) return true;	// 아래
		}
		else if(before == 6) {	// 하좌터널
			if(d == 2 && (after == 1 || after == 2 || after == 4 || after == 7)) return true;	// 아래
			if(d == 3 && (after == 1 || after == 3 || after == 4 || after == 5)) return true;	// 좌측
		}
		else if(before == 7) {	// 상좌터널
			if(d == 0 && (after == 1 || after == 2 || after == 5 || after == 6)) return true;	// 위
			if(d == 3 && (after == 1 || after == 3 || after == 4 || after == 5)) return true;	// 좌측
		}
		return false;
	}
	
	public static void move(int r, int c, int cnt, int[] dr, int[] dc) { // 이동
		
		if(cnt == L) return;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d], nc = c + dc[d];
			if(isPossible(nr, nc) && isMovable(r, c, d, nr, nc)) {
				answer += 1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, cnt + 1});
			}
		}
	} // end of mv func
	
	public static void BFS(int sr, int sc) { // 완탐
		
		q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, 1});	// r, c, cnt
		visited[sr][sc] = true;
		answer += 1;
		
		while(!q.isEmpty()) {
			
			int[] info = q.poll();
			int r = info[0], c = info[1], cnt = info[2];
		
			move(r, c, cnt, dr, dc);
			
			if(cnt == L) return;
		}
		
	} // end of BFS
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M) && !visited[r][c] && plain[r][c] != 0;
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
