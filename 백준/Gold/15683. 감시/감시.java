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

	/** initialize */
	public static void initial() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N + 2][M + 2];
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
				if (n != 0 && n != 6)
					cctvs.add(new Cctv(i, j, n));
			}
		}
	}

	static int[] dR = { -1, 1, 0, 0 };
	static int[] dC = { 0, 0, -1, 1 };

	/** up down left right cctv search */
	public static int[][] search(int r, int c, int dir, int[][] nMaps) {
		int nr = r;
		int nc = c;
		int dr = dR[dir];
		int dc = dC[dir];
		while (nMaps[nr][nc] != 6) {
			if (nMaps[nr][nc] == 0)
				nMaps[nr][nc] = '#';
			nr += dr;
			nc += dc;
		}
		return nMaps;
	}

	public static int[][] copyArray(int[][] nMaps) {
		return Arrays.stream(nMaps).map(x -> Arrays.copyOf(x, x.length)).toArray(int[][]::new);
	}

	public static void dfs(int depth, int[][] nMaps) {
		if (depth == cctvs.size()) {
			int val = 0;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					if (nMaps[i][j] == 0)
						val++;
				}
			}
			minVal = Math.min(minVal, val);
			return;
		}
		Cctv cctv = cctvs.get(depth);
		int t = cctv.type;
		int r = cctv.r;
		int c = cctv.c;
		int[][] cMaps = copyArray(nMaps);
		if (t == 1) {
			dfs(depth + 1, search(r, c, 0, cMaps));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 1, cMaps));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 2, cMaps));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 3, cMaps));
		} else if (t == 2) {
			dfs(depth + 1, search(r, c, 0, search(r, c, 1, cMaps)));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 2, search(r, c, 3, cMaps)));
		} else if (t == 3) {
			dfs(depth + 1, search(r, c, 0, search(r, c, 2, cMaps)));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 0, search(r, c, 3, cMaps)));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 1, search(r, c, 2, cMaps)));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 1, search(r, c, 3, cMaps)));
		} else if (t == 4) {
			dfs(depth + 1, search(r, c, 0, search(r, c, 1, search(r, c, 2, cMaps))));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 1, search(r, c, 2, search(r, c, 3, cMaps))));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 2, search(r, c, 3, search(r, c, 0, cMaps))));
			cMaps = copyArray(nMaps);
			dfs(depth + 1, search(r, c, 3, search(r, c, 0, search(r, c, 1, cMaps))));
		} else if (t == 5) {
			dfs(depth + 1, search(r, c, 0, search(r, c, 1, search(r, c, 2, search(r, c, 3, cMaps)))));
		}
	}

	/** print map */
	public static void printMap(int[][] nMaps) {
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < M + 2; j++) {

				System.out.print(nMaps[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {
		initial();
		dfs(0, maps);
		System.out.println(minVal);
	}
}
