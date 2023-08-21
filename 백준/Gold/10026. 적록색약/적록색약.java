import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Idx {
	int r;
	int c;

	public Idx(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int nR, nC;
	static int N;
	static char[][] maps;
	static boolean[][] visited;

	public static void search() {
		for (int i = nR; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					nR = i;
					nC = j;
					return;
				}
			}
		}
		nR = N - 1;
		nC = N - 1;
		return;
	}

	static int[] dR = { -1, 1, 0, 0 };
	static int[] dC = { 0, 0, -1, 1 };

	public static int count() {
		visited = new boolean[N][N];
		Deque<Idx> que = new ArrayDeque<>();
		nR = 0;
		nC = 0;
		que.add(new Idx(0, 0));
		visited[0][0] = true;
		Idx temp;
		int R, C;
		char color;
		int newR, newC;
		int cnt = 0;
		while (nR != N - 1 || nC != N - 1) {
			cnt++;
			que.add(new Idx(nR, nC));
			visited[nR][nC] = true;
			while (!que.isEmpty()) {

				temp = que.poll();
				R = temp.r;
				C = temp.c;

				color = maps[R][C];
				for (int i = 0; i < 4; i++) {
					newR = R + dR[i];
					newC = C + dC[i];
					try {
						if (!visited[newR][newC] && maps[newR][newC] == color) {
							visited[newR][newC] = true;
							que.add(new Idx(newR, newC));
						}
					} catch (Exception e) {

					}
				}
			}
			search();
			if(nR == N-1 && nC == N-1 && !visited[nR][nC]) cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {

		N = Integer.parseInt(br.readLine());
		maps = new char[N][N];
		for (int i = 0; i < N; i++) {
			maps[i] = br.readLine().toCharArray();
		}
		int cnt1 = count();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j] == 'G')
					maps[i][j] = 'R';
			}

		}
		int cnt2 = count();
		System.out.println(cnt1 + " " + cnt2);
	}
}
