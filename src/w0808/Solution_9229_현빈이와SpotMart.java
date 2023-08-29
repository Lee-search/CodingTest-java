package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_현빈이와SpotMart {
	
	public static int N, M;
	public static int[] snack;
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
			answer = -1;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				snack[i] = stoi(st.nextToken());
			}
			
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					System.out.println(i + " " + j);
					if(snack[i] + snack[j] <= M) {
						
						answer = Math.max(answer, snack[i] + snack[j]);
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main

	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
