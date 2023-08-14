package w0814;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
	
	static int N, M, R, C, L, answer;
	static int[] dr, dc;
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
			
			System.out.println();
			
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
	
	public static boolean isMove(int r, int c, int nr, int nc) {
		if(plain[r][c] == 1) return true;
		else if(plain[r][c] == 2) {
			if(plain[nr][nc] == 3) return false;
			else return true;
		}
		else if(plain[r][c] == 3) {
			if(plain[nr][nc] == 2) return false;
			else return true;
		}
		else if(plain[r][c] == 4) {
			if(plain[nr][nc] == 2) return false;
			else return true;
		}
		else if(plain[r][c] == 5) return true;
		else if(plain[r][c] == 6) return true;
		else return true;
	}
	
	public static void move(int r, int c, int cnt, int[] dr, int[] dc) {
		
		if(cnt == L) return;
		
		for(int d = 0; d < dr.length; d++) {
			int nr = r + dr[d], nc = c + dc[d];
			if(isPossible(nr, nc) && isMove(r, c, nr, nc)) {
				answer += 1;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, cnt + 1});
			}
		}
	} // end of mv func
	
	public static void BFS(int sr, int sc) {
		
		q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, 1});	// r, c, cnt
		visited[sr][sc] = true;
		answer += 1;
		
		while(!q.isEmpty()) {
			
			int[] info = q.poll();
			int r = info[0], c = info[1], cnt = info[2];
			
			switch(plain[r][c]) {
			case 1:
				dr = new int[] {-1, 0, 1, 0};
				dc = new int[] {0, 1, 0, -1};
				move(r, c, cnt, dr, dc);
				break;
			case 2:
				dr = new int[] {-1, 1};
				dc = new int[] {0, 0};
				move(r, c, cnt, dr, dc);
				break;
			case 3:
				dr = new int[] {0, 0};
				dc = new int[] {-1, 1};
				move(r, c, cnt, dr, dc);
				break;
			case 4:
				dr = new int[] {-1, 0};
				dc = new int[] {0, 1};
				move(r, c, cnt, dr, dc);
				break;
			case 5:
				dr = new int[] {1, 0};
				dc = new int[] {0, 1};
				move(r, c, cnt, dr, dc);
				break;
			case 6:
				dr = new int[] {1, 0};
				dc = new int[] {0, -1};
				move(r, c, cnt, dr, dc);
				break;
			case 7:
				dr = new int[] {-1, 0};
				dc = new int[] {0, -1};
				move(r, c, cnt, dr, dc);
				break;
			} // end of switch
			
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
