import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static boolean[] visited;
	static int[][] maps;
	static int[] per;
	static boolean[] ru;
	static int now;
	static int score;
	static int maxScore;

	/** initialize */
	public static void initial() throws Exception {
		N = Integer.parseInt(br.readLine());
		maps = new int[N][9];
		visited = new boolean[10];
		per = new int[9];
		ru = new boolean[4];
		now = 0;
		score = 0;
		maxScore = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void permutation(int depth) {
		if (depth == 9) {
			score = 0;
			now = 0;
			play();
			maxScore = Math.max(maxScore, score);
			return;
		}
		if (depth == 3) {
			per[3] = 0;
			permutation(depth + 1);
		} else {
			for (int i = 1; i < 9; i++) {
				if (!visited[i]) {
					visited[i] = true;
					per[depth] = i;
					permutation(depth + 1);
					visited[i] = false;
				}
			}
		}
	}

	static void play() {
		int inning = 0;
		while (inning < N) {
			oneInning(inning);
			inning++;
		}
	}

	static void oneInning(int inning) {
		int outCnt = 0;
		int ta;
		while (outCnt != 3) {
			now %= 9;
			ta = maps[inning][per[now]];
			outCnt += oneTa(ta);
			now++;
		}
		ru = new boolean[4];
	}

	static int oneTa(int ta) {
		switch (ta) {
		case 0:
			return 1;
		case 1:
			ru[0] = true;
			if (ru[3])
				score++;
			for (int i = 2; i >= 0; i--) {
				ru[i + 1] = ru[i];
			}
			ru[0] = false;
			return 0;
		case 2:
			if (ru[2])
				score++;
			if (ru[3])
				score++;
			ru[3] = false;
			if (ru[1]) {
				ru[3] = true;
				ru[1] = false;
			}
			
			ru[2] = true;
			return 0;
		case 3:
			if (ru[1])
				score++;
			if (ru[2])
				score++;
			if (ru[3])
				score++;
			ru = new boolean[4];
			ru[1] = false;
			ru[2] = false;
			ru[3] = true;
			return 0;
		case 4:
			ru[0] = true;
			for (boolean b : ru) {
				if (b)
					score++;
			}
			ru = new boolean[4];
			return 0;
		default:
			return 0;
		}

	}

	public static void main(String[] args) throws Exception {
		initial();
		permutation(0);
		System.out.println(maxScore);
	}
}
