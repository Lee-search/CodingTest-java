package tmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_BJ_1316 {
	
	static int N, answer;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int['z' - 'a' + 1];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			// 각 알파벳의 갯수 저장
			for(int j = 0; j < line.length(); j++) dp[j] += 1;
			
			int count = 0;	// 각 알파벳 개수
			for(int j = 0; j < line.length() - 1; j++) {
				
				// 현재 문자과 다음 문자이 같음
				if(line.charAt(j) == line.charAt(j + 1)) count++;
				else {
					if(count == dp[line.charAt(j)]) {
						answer += 1;
					}
				}
			}
		}
		
		
		
	} // end of main
} // end of class
