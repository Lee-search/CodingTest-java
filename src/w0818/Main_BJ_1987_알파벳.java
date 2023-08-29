package w0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1987_알파벳 {
	
	static int R, C, answer;
	static char[][] plain;
	static int[] usedAlpha;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		
		plain = new char[R][C];
		usedAlpha = new int['Z'-'A' + 1];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				plain[i][j] = line.charAt(j);
			}
		} // end of init
		
		usedAlpha[plain[0][0] - 'A'] += 1; // 시작 위치 알파벳 사용처리
		DFS(0, 0, 1);
		System.out.println(answer);
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void DFS(int r, int c, int cnt) {
		
		answer = Math.max(answer, cnt);	// 정답 갱신
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isPossible(nr, nc) && usedAlpha[plain[nr][nc] - 'A'] == 0) {
				usedAlpha[plain[nr][nc] - 'A'] += 1;
				DFS(nr, nc, cnt + 1);
				usedAlpha[plain[nr][nc] - 'A'] -= 1;
			}
		}
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
