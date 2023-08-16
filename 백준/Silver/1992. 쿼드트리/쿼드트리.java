import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int[][] plain;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = stoi(br.readLine());
		plain = new int[N][N];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				plain[i][j] = line.charAt(j) - '0';
			}
		} // end of init
		
		System.out.println(getSubs(0, 0, N));
		
		
	} // end of main
	
	/**
	 * @param sr : 시작 행 좌표
	 * @param sc : 시작 열 좌표
	 * @param size : 검증할 N의 크기, 1/2 로 분할
	 */
	public static String getSubs(int sr, int sc, int size) {
		
		if(size == 1) {
			return plain[sr][sc] == 0 ? "0" : "1";
		} // end of basis
		
		String sub01 = getSubs(sr, sc, size / 2);
		String sub02 = getSubs(sr, sc + size / 2, size / 2);
		String sub03 = getSubs(sr + size / 2, sc, size / 2);
		String sub04 = getSubs(sr + size / 2, sc + size / 2, size / 2);
		
		// 4개의 사분면에 표현하는 경우 괄호 사용
		String line = sub01 + sub02 + sub03 + sub04;
		if(line.equals("0000")) return "0";
		else if(line.equals("1111")) return "1";
		else return "(" + line + ")";
		
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
