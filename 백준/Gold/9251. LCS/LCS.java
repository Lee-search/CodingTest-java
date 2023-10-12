
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int answer;
	static int[][] LCS;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int len1 = str1.length();
		int len2 = str2.length();
		LCS = new int[len1 + 1][len2 + 1];
		
		for(int i = 0; i <= len1; i++) {
			for(int j = 0; j <= len2; j++) {
				// 고려할 문자열이 없는 가장자리 
				if(i == 0 || j == 0) 
					continue;
				// 두 문자가 같다면, 지금까지의 LCS에 문자 +1
				else if(str1.charAt(i-1) == str2.charAt(j-1))
					LCS[i][j] = LCS[i-1][j-1] + 1;
				// 두 문자가 다르다면, 지금까지의 LCS 중 최대값 저장
				else
					LCS[i][j]= Math.max(LCS[i-1][j], LCS[i][j-1]);
				
				answer = Math.max(answer, LCS[i][j]);
			}
		} // end of for
		
//		print();
		System.out.println(answer);
	} // end of main
	
//	public static void print() {
//		for(int i = 0; i < LCS.length; i++) {
//			System.out.println(Arrays.toString(LCS[i]));
//		}
//		System.out.println();
//	}
}
