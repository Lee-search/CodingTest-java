package others.baekjoon.graph_traversal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7569 {
	
	public static int N, M;
	public static int count, answer;
	public static int[][] plain;
	public static Queue<int[]> queue; // r, c, day
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = stoi(st.nextToken());
		N = stoi(st.nextToken());
		plain = new int[N][M];
		queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				plain[i][j] = stoi(st.nextToken());
				
				if(plain[i][j] == 0) {
					count += 1;
				} // 사전에 안익은 토마토 갯수 저장
				if(plain[i][j] == 1) {
					queue.offer(new int[] {i, j, 0});
				} // 익은 토마토 좌표 저장
			}
		}
		
		// 익은 토마토가 하나도 없으면 -> 토마토 익지 못함, -1
		if(queue.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		// 안익은 토마토가 하나도 없으면 -> 이미 다 익어있음, 0
		if(count == 0) {
			System.out.println(0);
			return;
		}
		
		BFS();
		
		// BFS 탐색 이후에도 안익은 토마토가 남아있으면 -1
		if(count > 0) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(answer);
	} // end of main
	
	public static void BFS() {
		
		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			
			int r = info[0];	
			int c = info[1];	// 위치
			int day = info[2];	// 경과 날짜
			
			answer = Math.max(answer, day); // 날짜가 늘어나면 반영
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				// 아직 익지 않은 토마토인 경우
				if((0 <= nr && nr < N && 0 <= nc && nc < M) && plain[nr][nc] == 0) {
					plain[nr][nc] = 1;
					count -= 1;
					queue.offer(new int[] {nr, nc, day + 1});
				}
			}
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
