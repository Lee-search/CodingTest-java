import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[][] maps;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		maps = new int[N][3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			maps[0][i] = Integer.parseInt(st.nextToken());
		}

		int temp;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			temp = Integer.parseInt(st.nextToken());
			maps[i][0] = Math.min(temp + maps[i - 1][1], temp + maps[i - 1][2]);
			temp = Integer.parseInt(st.nextToken());
			maps[i][1] = Math.min(temp + maps[i - 1][0], temp + maps[i - 1][2]);
			temp = Integer.parseInt(st.nextToken());
			maps[i][2] = Math.min(temp + maps[i - 1][0], temp + maps[i - 1][1]);
		}
		System.out.println(Math.min(maps[N-1][0], Math.min(maps[N-1][1], maps[N-1][2])));
	}
}
