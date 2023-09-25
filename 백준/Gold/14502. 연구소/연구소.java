import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer;
	static int[][] plain, copiedPlain;
	static ArrayList<int[]> safeList, virusList;
	
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		plain = new int[N][M];
		safeList = new ArrayList<>();
		virusList = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int n = stoi(st.nextToken());
				if(n == 0) safeList.add(new int[] {i, j});
				else {
					if(n == 2) virusList.add(new int[] {i, j});
					plain[i][j] = n;
				}
			}
		} // end of init
		
		isSelected = new boolean[safeList.size()];
		combination(0, 0);

		System.out.println(answer);
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	public static boolean[][] visited;
	
	public static void simulation() {
		
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		
		copiedPlain = new int[N][M];
		for(int i = 0; i < N; i++) {
			copiedPlain[i] = plain[i].clone();
		}
		
		for(int i = 0; i < safeList.size(); i++) {
			if(isSelected[i]) {
				int wr = safeList.get(i)[0], wc = safeList.get(i)[1];
				copiedPlain[wr][wc] = 1;	// 벽 생성
			}
		}
		
		for(int i = 0; i < virusList.size(); i++) {
			int vr = virusList.get(i)[0], vc = virusList.get(i)[1];
			q.offer(new int[] {vr, vc});
			visited[vr][vc] = true;
		}
		
		
		while(!q.isEmpty()) {
			
			int r = q.peek()[0], c = q.poll()[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isPossible(nr, nc) && copiedPlain[nr][nc] == 0) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copiedPlain[i][j] == 0 && !visited[i][j]) {
					sum += 1;
				}
			}
		}
		answer = Math.max(answer, sum);
		
	} // end of func
	
	public static void combination(int cnt, int start) {
		
		if(cnt == 3) { // basis
			simulation();
			return;
		}
		
		for(int i = start; i < safeList.size(); i++) {
			
			isSelected[i] = true;
			combination(cnt + 1, i + 1);
			isSelected[i]= false;
		}
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M && !visited[r][c];
	} // end of func

	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
	
}
