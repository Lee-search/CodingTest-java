package w0803;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_12891 {
	
	public static int S, P;	// 문자열 길이, 부분문자열 길이
	public static int A, C, G, T;
	public static String pass;
	public static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		S = stoi(st.nextToken());
		P = stoi(st.nextToken());
		
		pass = br.readLine();
		
		st = new StringTokenizer(br.readLine(), " ");
		A = stoi(st.nextToken());
		C = stoi(st.nextToken());
		G = stoi(st.nextToken());
		T = stoi(st.nextToken());
		
		// 부분합: 지나가면서 A,C,G,T가 나온 횟수만큼 ++
		// 첫번째 합은 무조건 0이어야함
		int[][] sums = { new int[S + 1], new int[S + 1], new int[S + 1], new int[S + 1] };
		for(int i = 0; i < pass.length(); i++) {
			switch(pass.charAt(i)) {
			case 'A':
				sums[0][i + 1] = sums[0][i] + 1;
				sums[1][i + 1] = sums[1][i];
				sums[2][i + 1] = sums[2][i];
				sums[3][i + 1] = sums[3][i];
				break;
			case 'C':
				sums[0][i + 1] = sums[0][i];
				sums[1][i + 1] = sums[1][i] + 1;
				sums[2][i + 1] = sums[2][i];
				sums[3][i + 1] = sums[3][i];
				break;
			case 'G':
				sums[0][i + 1] = sums[0][i];
				sums[1][i + 1] = sums[1][i];
				sums[2][i + 1] = sums[2][i] + 1;
				sums[3][i + 1] = sums[3][i];
				break;
			case 'T':
				sums[0][i + 1] = sums[0][i];
				sums[1][i + 1] = sums[1][i];
				sums[2][i + 1] = sums[2][i];
				sums[3][i + 1] = sums[3][i] + 1;
				break;
			}
		}
		
//		System.out.println(Arrays.toString(sums[0]));
//		System.out.println(Arrays.toString(sums[1]));
//		System.out.println(Arrays.toString(sums[2]));
//		System.out.println(Arrays.toString(sums[3]));
		
		// sp: startPoint, ep: endPoint
		// 부분 문자열의 길이만큼 sp, ep 지정
		for(int sp = 0, ep = sp + P - 1; ep < S; sp++, ep++) {
			
			// 조건을 만족하는 문자열이면 answer
			if(sums[0][ep + 1] - sums[0][sp] >= A
					&& sums[1][ep + 1] - sums[1][sp] >= C
					&& sums[2][ep + 1] - sums[2][sp] >= G
					&& sums[3][ep + 1] - sums[3][sp] >= T
					) answer += 1;
		}
		System.out.println(answer);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
