package w0830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H, answer;
	static int[][] plain;
	static Queue<int[]> q;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		K = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = stoi(st.nextToken());
		H = stoi(st.nextToken());
		answer = -1;

		plain = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int n = stoi(st.nextToken());
				if(n != 0) plain[i][j] = n;
			}
		} // end of init

		BFS(0,0);
		System.out.println(answer);
	} // end of main

	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};

	public static int[] hr = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static int[] hc = {1, 2, 2, 1, -1, -2, -2, -1};

	// 0,0 -> H - 1, W - 1
	public static void BFS(int sr, int sc) {

		q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, K, 0});
		visited = new boolean[H][W][K + 1];
		visited[sr][sc][K] = true;

		while (!q.isEmpty()) {

			int[] info = q.poll();
			int r = info[0], c = info[1], k = info[2], cnt = info[3];

			if(r == H - 1 && c == W - 1) {
				answer = cnt;
				return;
			} // basis

			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = r + dc[i];

				if(isPossible(nr, nc, k)) {
					q.offer(new int[] {nr, nc, k, cnt + 1});
					visited[nr][nc][k] = true;
				}
			} // 인접 방향 이동

			if(k > 0) {
				for(int i = 0; i < 8; i++) {
					int nr = r + hr[i];
					int nc = r + hc[i];

					if(isPossible(nr, nc, k - 1)) {
						q.offer(new int[] {nr, nc, k - 1, cnt + 1});
						visited[nr][nc][k-1] = true;
					}
				} // 말처럼 이동
			}
		}
	} // end of BFS

	public static boolean isPossible(int r, int c, int k) {
		return 0 <= r && r < H && 0 <= c && c < W && !visited[r][c][k] && plain[r][c] == 0;
	} // end of func

	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
