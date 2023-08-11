package tmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_16236 {
	
	static int N;
	static int[][] plain;
	static boolean[][] visited;
	static int answer;
	
	static int[] fish_left;
	static int shark_r, shark_c, size;
	static int eat_count, move_count;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		plain = new int[N][N];
		fish_left = new int[6 + 1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				plain[i][j] = stoi(st.nextToken());
				if(plain[i][j] == 9) {
					shark_r = i;
					shark_c = j;
					size = 2;
				} // 상어 위치 초기화
				else if(1 <= plain[i][j] && plain[i][j] <= 6) {
					fish_left[plain[i][j]] += 1;
				} // 생선 크기마다 남은 개수 초기화
			}
		} // end of input
		
		
		for(int i = 1; i <= 6; i++) {
			
			// 상어가 먹을 수 있는 크기의 생선이 남아있는 경우, BFS()
			if(size > i) {
				
				while(fish_left[i] > 0) {
					int tmp = fish_left[i];
					visited = new boolean[N][N];
					BFS(i);
					
					if(tmp == fish_left[i]) { // BFS 후애도 생선 그대로?
						System.out.println(answer);
						return;
					}
				} // end of while
			}
		} // end of bfs
		
		System.out.println(answer);
		
		
	} // end of main
	
	public static void calc() {	// 상어 사이즈 계산
		
		if(eat_count == size) {
			size += 1;
			eat_count = 0;
		} // 자기 크기만큼 먹었으면, 초기화
	}
	
	public static void BFS(int f_size) {
		
		Queue<int[]> q = new ArrayDeque<>();
		// 상어 위치 r, c, 상어 이동 횟수 cnt
		visited[shark_r][shark_c] = true;
		q.offer(new int[] {shark_r, shark_c, 0});
		
		while(!q.isEmpty()) {
			
			int[] info = q.poll();
			int r = info[0], c = info[1], cnt = info[2];
			
			// 먹을 수 있는 생선 나오면? 종료
			if(plain[r][c] < size) {
				eat_count += 1;
				calc();
				answer += cnt;
				fish_left[f_size] -= 1;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 이동하려면 작거나 같아야함
				if(isPossible(nr, nc) && plain[nr][nc] <= size) {
					q.offer(new int[] {nr, nc, cnt + 1});
				} // 갈수 있으면 큐에 넣기
			}
			
		} // end of while
		
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N) && !visited[r][c];
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
