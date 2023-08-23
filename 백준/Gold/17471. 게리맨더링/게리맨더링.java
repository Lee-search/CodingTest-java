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

	static ArrayList<Integer> comb;
	static int N;
	static int[] people;
	static ArrayList<int[]> edges = new ArrayList<>();
	static boolean[] visited;
	static Deque<Integer> que = new ArrayDeque<>();

	/** initialize */
	public static void initial() throws Exception {
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		int[] linked;
		int size;
		edges.add(new int[0]);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			linked = new int[size];
			for (int j = 0; j < size; j++) {
				linked[j] = Integer.parseInt(st.nextToken());
			}
			edges.add(linked);
		}
	}

	static int minVal = Integer.MAX_VALUE;

	public static void combination() {
		int p1, p2;
		for (int i = 1; i < (1 << N >> 1); i++) {
			p1 = isConnect(i);
			p2 = isConnect(((1 << N) - 1) ^ i);
//			System.out.printf("%d %d\n", p1, p2);
			if (p1 != -1 && p2 != -1)
				minVal = Math.min(minVal, Math.abs(p1 - p2));
		}
	}

	/** BFS */
	public static int isConnect(int comb) {

		int cnt = 1;
		int p = 0;
		int n = 1 << N - 1;
		int first = N;
		while ((n & comb) != 1 << (first - 1) && first != 0) {
			first--;
			n >>= 1;
		}
		que.add(first);
		int visited = 1 << (first - 1);
		p += people[first];
		int val = 0;
		while (!que.isEmpty()) {
			n = que.poll();
			for (int l : edges.get(n)) {
				val = 1 << (l - 1);
				if ((val & comb) == val && (visited & val) == 0) {
					cnt++;
					p += people[l];
					visited |= val;
					que.add(l);
				}
			}
		}
		if (cnt == count1(comb))
			return p;
		else
			return -1;
	}

	/** 1의 개수 */
	public static int count1(int num) {
		int cnt = 0;
		for (cnt = 0; num != 0; cnt++) {
			num &= (num - 1);
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		initial();
		combination();
		System.out.println(minVal == Integer.MAX_VALUE ? -1 : minVal);
	}
}
