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
	static int cnt;

	public static void initial() throws Exception {
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0;
	}

	static int[] dR = new int[] { 0, 1, 1 };
	static int[] dC = new int[] { 1, 1, 0 };

	public static void bfs() {
		// r, c, angle
		int r, c, angle;
		int newR, newC;
		int[] now;
		Deque<int[]> que = new ArrayDeque<>();
		que.add(new int[] { 0, 1, 0 });

		while (!que.isEmpty()) {
			now = que.poll();
			r = now[0];
			c = now[1];
			angle = now[2];
			if (r == N - 1 && c == N - 1) {
				cnt++;
				continue;
			}
			for (int i = 0; i < 3; i++) {
				if (Math.abs(i - angle) > 1)
					continue;
				newR = r + dR[i];
				newC = c + dC[i];
				
				if (newR < N && newC < N && maps[newR][newC] == 0) {
					if (i == 1 && (maps[newR - 1][newC] == 1 || maps[newR][newC - 1] == 1))
						continue;
					que.add(new int[] { newR, newC, i });
				}
			}
		}
	}



	public static void main(String[] args) throws Exception {
		initial();
		bfs();
		System.out.println(cnt != 0 ? cnt : 0);
	}
}
