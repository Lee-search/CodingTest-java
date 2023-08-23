import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

class Cctv {
	int r;
	int c;
	int type;

	public Cctv(int r, int c, int type) {
		super();
		this.r = r;
		this.c = c;
		this.type = type;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static boolean[] visited;
	static int[][] maps;
	static ArrayList<Cctv> cctvs = new ArrayList<>();
	static int minVal = Integer.MAX_VALUE;
	static int cnt;

	/** initialize */
	public static void initial() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N + 2][M + 2];
		cnt = N * M;
		int n;
		for (int i = 0; i < N + 2; i++) {
			maps[i][0] = 6;
			maps[i][M + 1] = 6;
		}
		for (int i = 0; i < M + 2; i++) {
			maps[0][i] = 6;
			maps[N + 1][i] = 6;
		}
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				n = Integer.parseInt(st.nextToken());
				maps[i][j] = n;
				if (n == 6)
					cnt--;
				else if (n != 0) {
					cctvs.add(new Cctv(i, j, n));
					cnt--;
				}
			}
		}
	}

	static int[] dR = { -1, 1, 0, 0 };
	static int[] dC = { 0, 0, -1, 1 };

	/** up down left right cctv search */
	public static int search(int r, int c, int[] dirs, boolean willOn) {
		int v = 0;
		int nr, nc, dr, dc;
		int identifier = 10 * r + c;
		for (int dir : dirs) {
			dr = dR[dir];
			dc = dC[dir];
			nr = r + dr;
			nc = c + dc;
			while (maps[nr][nc] != 6) {
				if (willOn && maps[nr][nc] == 0) {
					v++;
					maps[nr][nc] = identifier;
				} else if (!willOn && maps[nr][nc] == identifier)
					maps[nr][nc] = 0;
				nr += dr;
				nc += dc;
			}
		}
		return v;
	}

//	public static int[][] copyArray(int[][] nMaps) {
//		return Arrays.stream(nMaps).map(x -> Arrays.copyOf(x, x.length)).toArray(int[][]::new);
//	}

	static int[][][] d = new int[][][] { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 1 }, { 2, 3 } },
			{ { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };

	public static void dfs(int depth, int val) {
		if (depth == cctvs.size()) {
			minVal = Math.min(minVal, val);
			return;
		}
		Cctv cctv = cctvs.get(depth);
		int t = cctv.type;
		int r = cctv.r;
		int c = cctv.c;
		for (int[] dirs : d[t - 1]) {
			dfs(depth + 1, val - search(r, c, dirs, true));
			search(r, c, dirs, false);
		}
	}

	/** print map */
	public static void printMap() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				System.out.print(maps[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		initial();
		dfs(0, cnt);
		System.out.println(minVal);
	}
}
