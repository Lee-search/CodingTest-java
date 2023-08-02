package w0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_3040 {
	
	public static int[] arr = new int[9];
	public static int[] choice = new int[7];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 모든 숫자는 서로 다름, 순서 X -> Combination
		comb(0, 0);
	}
	
	public static void comb(int start, int count) {
		
		if(count == 7) {
			StringBuilder sb = new StringBuilder();
			int sum = 0;
			
			for(int a : choice) {
				sum += a;	
				sb.append(a).append("\n");
			}
			
			if(sum == 100) System.out.println(sb);
			return;
		}
		
		for(int i = start; i < 9; i++) {
			choice[count] = arr[i];
			comb(i + 1, count + 1);
		}
	}
}
