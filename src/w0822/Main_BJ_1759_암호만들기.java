package w0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1759_암호만들기 {
	
	static int L, C;
	static char[] alpha;
	
	static int[] selector;
	static boolean[] isSelected;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		L = stoi(st.nextToken());
		C = stoi(st.nextToken());
		alpha = new char[C];
		
		selector = new int[L];
		isSelected = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		} // end of init
		
		Arrays.sort(alpha);
		
		comb(0, 0);
		System.out.println(sb);
	} // end of main
	
	public static void getPwd() {
		
		StringBuilder pwd = new StringBuilder();
		
		int aeiou = 0;	// 모음 수
		int others = 0;	// 자음 수
		
		for(int i = 0; i < C; i++) {
			if(isSelected[i]) {
				// 캐릭터 중에서 모음 카운트
				if(alpha[i] == 'a' || alpha[i] == 'e' || alpha[i] == 'i'
						|| alpha[i] == 'o' || alpha[i] == 'u') aeiou += 1;
				// 자음 카운트
				else others += 1;
				pwd.append(alpha[i]);
			}
		}
		
		if(aeiou >= 1 && others >= 2) sb.append(pwd).append("\n");
	} // end of innerFunc
	
	public static void comb(int cnt, int start) {
		
		if(cnt == L) {
			getPwd();
			return;
		} // basis
		
		for(int i = start; i < C; i++) {
			
			isSelected[i] = true;
			comb(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
