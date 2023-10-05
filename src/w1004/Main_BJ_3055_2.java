package w1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_3055_2 {
	
	static int R, C;
	static char[][] plain;
	static boolean[][] visited;
	static Queue<int[]> water, kaktus;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		plain = new char[R][C];
		visited = new boolean[R][C];
		
		water = new ArrayDeque<>();
		kaktus = new ArrayDeque<>();
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				plain[i][j] = line.charAt(j);
				
				// 물이 차있는 위치 queue에 저장
				if(plain[i][j] == '*') water.offer(new int[] {i, j});
				else if(plain[i][j] == 'S') {
					kaktus.offer(new int[] {i, j, 0});
					visited[i][j] = true;
				}
			}
		} // end of init
		
		BFS();
		System.out.println("KAKTUS");
	} // end of main
	
	public static void BFS() {
		
		while(!kaktus.isEmpty()) {
			
			// 현재 위치에서 이동가능한 곳만 탐색
			for(int i = 0; i < water.size(); i++) {
				
				int r = water.peek()[0];
				int c = water.poll()[1];
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(isMovable(nr, nc) && (plain[nr][nc] == '.' || plain[nr][nc] == 'S')) {
						water.offer(new int[] {nr, nc});
						plain[nr][nc] = '*';
					}
				}
			} // end of for
			
			move();
		} // end of while
	} // end of func
	
	public static void move() {
		
		for(int i = 0; i < kaktus.size(); i++) {
			
			int r = kaktus.peek()[0];
			int c = kaktus.peek()[1];
			int cnt = kaktus.poll()[2];
			
			if(plain[r][c] == 'D') {
				System.out.println(cnt);
				System.exit(0);
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isMovable(nr, nc) && !visited[nr][nc] && plain[nr][nc] != 'X' && plain[nr][nc] != '*') {
					kaktus.offer(new int[] {nr, nc, cnt + 1});
					visited[nr][nc] = true;
				}
			}
		}
	} // end of func
	
	public static boolean isMovable(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	} // end of func
}