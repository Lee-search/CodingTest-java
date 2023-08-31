package w0830;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_10971_외판원순회2 {
	
	static int N, answer;
	static int[][] adjMatrix;
	
	static int[] selector;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		answer = Integer.MAX_VALUE;
		adjMatrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int n = stoi(st.nextToken());
				if(n != 0) adjMatrix[i][j] = n;
			}
		} // end of init
		
		selector = new int[N];
		isSelected = new boolean[N];
		permutations(0);
		
		System.out.println(answer);
	} // end of main
	
	public static void permutations(int cnt) {
		
		if(cnt == N) {
			walk();
			return;
		} // basis
		
		for(int i = 0; i < N; i++) {
			
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			selector[cnt] = i;
			permutations(cnt + 1);
			isSelected[i] = false;
		}
	} // end of func
	
	public static void walk() {
		
		int total = 0;
		
		// 맨 마지막 위치에서 다시 처음으로 돌아와야함
		if(adjMatrix[selector[N - 1]][selector[0]] == 0) return;
		
		for(int i = 1; i < N; i++) {
			
			int a = selector[i - 1];
			int b = selector[i];
			// 다음 칸으로 갈 수 없으면 종료
			if(adjMatrix[a][b] == 0) return;
			total += adjMatrix[a][b];
		}
		
		total += adjMatrix[selector[N - 1]][selector[0]];
		answer = Math.min(total, answer);

		System.out.println(Arrays.toString(selector));
		System.out.println(total);
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
