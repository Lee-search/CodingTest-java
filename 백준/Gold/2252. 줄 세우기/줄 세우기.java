import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static boolean[] isCmp;
	static int[] in;
	static int N, M;
	static char[][] maps;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
	static ArrayList<Integer> res = new ArrayList<>();

	/** initialize */
	public static void initial() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		in = new int[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			edges.add(new ArrayList<>());
		}
		int n1, n2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			in[n2]++;
			edges.get(n1).add(n2);
		}
	}

	/** 위상 정렬 */
	public static void tp_sort() {
		Deque<Integer> que = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) {
			if (in[i] == 0)
				que.add(i);
		}
		while (!que.isEmpty()) {
			int now = que.poll();
			visited[now] = true;
			res.add(now);
			for (int edge : edges.get(now)) {
				if (--in[edge] == 0)
					que.add(edge);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		initial();
		tp_sort();
		for (int n : res) {
			sb.append(n).append(" ");
		}
		for (int i = 1; i < N + 1; i++) {
			if (!visited[i])
				sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
