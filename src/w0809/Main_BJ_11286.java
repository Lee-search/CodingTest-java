package w0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_11286 {
	
	public static final int size = 100_000;
	public static int N, lastIndex;
	public static long[] stack;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		stack = new long[size + 1];
		lastIndex = 0;
		
		for(int i = 0; i < N; i++) {
			
		}
		
		
	} // end of main
	
	public static void push(int num) {
		if(lastIndex == 0) {	// 값이 없으면 첫번째 자리에 넣기
			stack[++lastIndex] = num;
			return;
		}
		
		// 절댓값 비교 후 가장 작은 것 위로
		if(Math.abs(stack[lastIndex]) > Math.abs(stack[lastIndex * 2])) {
			
		}
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
