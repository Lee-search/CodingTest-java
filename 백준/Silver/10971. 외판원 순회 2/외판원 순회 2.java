import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] maps;
	static boolean[] visited;
	static int minCost;
	static int start;

	public static void initial() throws Exception {
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		minCost = Integer.MAX_VALUE;
	}

	public static void dfs(int depth, int now, int cost) {
		int newCost;
		if (depth == N - 1) {
			newCost = maps[now][start];
			if (newCost == 0)
				return;
			minCost = Math.min(minCost, cost + newCost);
			return;
		}

		if (cost > minCost)
			return;
		
		for (int i = 0; i < N; i++) {
			newCost = maps[now][i];
			if (newCost != 0 && !visited[i] && cost + newCost <= minCost) {
				visited[i] = true;
				dfs(depth + 1, i, cost + newCost);
				visited[i] = false;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		initial();
		for (int j = 0; j < N; j++) {
			start = j;
			visited[j] = true;
			dfs(0, j, 0);
			visited[j] = false;
		}
		System.out.println(minCost);
	}
}
