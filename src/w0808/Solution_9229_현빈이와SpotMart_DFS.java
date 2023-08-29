package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_현빈이와SpotMart_DFS {
	
	public static int N, M;
	public static int[] snack;
	public static boolean[] picked;
	public static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0808/input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());	// 총 과자 갯수
			M = stoi(st.nextToken());	// 가능한 무게 MAX
			
			snack = new int[N];
			picked = new boolean[N];
			answer = -1;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				snack[i] = stoi(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static void dfs(int pic, int count) {
		
		if(count >= 2) {			
			int sum = 0;
			for(int i = 0; i < N; i++) {
				if(picked[i]) sum += snack[i];
			}
			
			if(sum > M) return;	// M보다 크면 들고갈 수 없음
			
			answer = Math.max(answer, sum);
			return;
		}
		
		for(int i = pic; i < N; i++) {
			picked[i] = true;
			dfs(i + 1, count + 1);
			picked[i] = false;
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
