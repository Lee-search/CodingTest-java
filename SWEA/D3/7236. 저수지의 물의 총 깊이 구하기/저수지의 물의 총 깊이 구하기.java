import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int N;
	public static char[][] plain;
	public static int answer;
	public static int[][] dic = // 12, 1, 3, 5, 6, 7, 9, 11시 방향
		{ {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream(new File("./src/w0802/input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			N = stoi(br.readLine());
			plain = new char[N][N];
			answer = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					plain[i][j] = st.nextToken().charAt(0);
				}
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(plain[r][c] == 'G') continue;
					
					int sum = 0;
					for(int d = 0; d < dic.length; d++) {	// 8 방향 탐색
						int nr = r + dic[d][0];
						int nc = c + dic[d][1];
						if(isPossible(nr, nc) && plain[nr][nc] == 'W') sum += 1;
					}
					if(sum == 0) sum += 1;
					answer = Math.max(answer, sum);
				}
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");	
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < N);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
