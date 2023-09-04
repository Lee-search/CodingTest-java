import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int K, W, H;
	static int[][] maps;
	static boolean[][][] visited;
	static int minCost;

	public static void initial() throws Exception {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		maps = new int[H][W];
		visited = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minCost = Integer.MAX_VALUE;
	}

	static int[] dRN = new int[] { -2, -1, -2, -1, 2, 1, 2, 1 };
	static int[] dCN = new int[] { -1, -2, 1, 2, -1, -2, 1, 2 };

	static int[] dR = new int[] { -1, 0, 1, 0 };
	static int[] dC = new int[] { 0, 1, 0, -1 };

	public static void bfs() {
		// r, c, cnt, remain
		int remain = K;
		int r, c, cnt = 0;
		int newR, newC;
		int[] now;
		boolean isCan = false;
		Deque<int[]> que = new ArrayDeque<>();
		que.add(new int[] { 0, 0, 0, remain });
		visited[0][0][remain] = true;
		while (!que.isEmpty()) {
			now = que.poll();
			r = now[0];
			c = now[1];
			cnt = now[2];
			remain = now[3];
			if (r == H - 1 && c == W - 1) {
				isCan = true;
				break;
			}
			for (int i = 0; i < 4; i++) {
				newR = r + dR[i];
				newC = c + dC[i];
				if (newR >= 0 && newC >= 0 && newR < H && newC < W && !visited[newR][newC][remain]
						&& maps[newR][newC] == 0) {
					visited[newR][newC][remain] = true;
					que.add(new int[] { newR, newC, cnt + 1, remain });
				}
			}
			if (remain > 0) {
				remain--;
				for (int i = 0; i < 8; i++) {
					newR = r + dRN[i];
					newC = c + dCN[i];

					if (newR >= 0 && newC >= 0 && newR < H && newC < W && !visited[newR][newC][remain]
							&& maps[newR][newC] == 0) {
						visited[newR][newC][remain] = true;
						que.add(new int[] { newR, newC, cnt + 1, remain });
					}
				}
			}
		}

		System.out.println(isCan ? cnt : -1);
	}

	public static void main(String[] args) throws Exception {
		initial();
		bfs();
	}
}
