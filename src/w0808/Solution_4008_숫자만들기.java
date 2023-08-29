package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기 {
	
	public static int N, max, min;
	public static int[] opers;
	public static int[] numbers;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0808/input_3.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			
			opers = new int[4];	// 연산자 갯수 받기
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				opers[i] = stoi(st.nextToken());
			}
			
			numbers = new int[N];	// 숫자 받기
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				numbers[i] = stoi(st.nextToken());
			}
			
			dfs(numbers[0], 1);
			
			sb.append("#").append(testCase).append(" ").append(max - min).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	
	public static void dfs(int result, int count) {
		
		if(count == N) { // 기저조건
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		if(opers[0] > 0) {
			opers[0] -= 1;
			dfs(result + numbers[count], count + 1);
			opers[0] += 1;
		}
		
		if(opers[1] > 0) {
			opers[1] -= 1;
			dfs(result - numbers[count], count + 1);
			opers[1] += 1;
		}
		
		if(opers[2] > 0) {
			opers[2] -= 1;
			dfs(result * numbers[count], count + 1);
			opers[2] += 1;
		}
		
		if(opers[3] > 0) {
			opers[3] -= 1;
			dfs(result / numbers[count], count + 1);
			opers[3] += 1;
		}
	} // end of dfs
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
} // end of class
