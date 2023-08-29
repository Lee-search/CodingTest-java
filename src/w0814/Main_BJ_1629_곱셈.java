package w0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1629_곱셈 {
	
	static int A, B, C;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(calc(A, B));
	} // end of main
	
	public static long calc(int a, int b) {
		
		if(b == 1) return a % C;
		
		long half = calc(a, b / 2);
		if(b % 2 == 1) return (half * half) % C * a % C;
		return (half * half) % C; 
	} // end of func
}
