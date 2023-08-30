import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] maps;
	static int[][][] dp;

	public static void initial() throws Exception {
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		dp = new int[N][N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i < N; i++) {
			dp[0][i][0] = 1;
			if (maps[0][i] == 1)
				break;
		}

	}

	static int[] dR = new int[] { 0, 1, 1 };
	static int[] dC = new int[] { 1, 1, 0 };

	public static int go() {
		// r, c, angle
		int r, c;
		Deque<int[]> que = new ArrayDeque<>();
		que.add(new int[] { 0, 1, 0 });

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				r = i;
				c = j;
				if (maps[r][j] == 1)
					continue;
				dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][1];
				if (maps[r - 1][c] == 0 && maps[r][c - 1] == 0 && maps[r - 1][c - 1] == 0)
					dp[r][c][1] += dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
				dp[r][c][2] = dp[r - 1][c][1] + dp[r - 1][c][2];
			}
		}
		return dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
	}

	public static void main(String[] args) throws Exception {
		initial();
		int cnt = go();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.printf("%d %d %d      ", dp[i][j][0], dp[i][j][1], dp[i][j][2]);
//			}
//			System.out.println();
//		}
		System.out.println(cnt);
	}
}
