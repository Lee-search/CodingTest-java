package w1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_4485 {

	static int N;
	static int[][] plain, costArr;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int itor = 1;
		
		while(true) {
			
			N = stoi(br.readLine());
			if(N == 0) break;
			
			plain = new int[N][N];	// 동굴 배열
			costArr = new int[N][N];	// 비용 배열
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
				}
				Arrays.fill(costArr[i], Integer.MAX_VALUE);
			} // end of init
			
			dijkstra(0, 0);
			sb.append("Problem " + itor + ": " + costArr[N - 1][N - 1] + "\n");
			itor++;
		}
		
		System.out.print(sb.toString());
	} // end of main
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void dijkstra(int sr, int sc) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<>();
		pq.add(new int[] {plain[sr][sc], sr, sc});
		
		
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
