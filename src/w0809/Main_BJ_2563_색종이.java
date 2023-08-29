package w0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_2563_색종이 {
	
	public static int N, answer;
	public static int[][] plain;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		plain = new int[101][101];
		
		for(int testCase = 0; testCase < N; testCase++) {
			st = new StringTokenizer(br.readLine());
			int c = stoi(st.nextToken());
			int r = stoi(st.nextToken());
			
			for(int i = 0; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					// 이미 한번 지나간 영역이면
					if(plain[r + i][c + j] == 1) continue;
					plain[r + i][c + j] = 1;
					answer += 1;
				}
			}
		} // end of tc
		
		System.out.println(answer);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
