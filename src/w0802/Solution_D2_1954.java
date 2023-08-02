package w0802;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_D2_1954 {
	public static int N;
	public static boolean[][] visited;
	public static int[][] plain;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0802/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = null;
		
		int T = stoi(br.readLine());
		int[][] dir = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			visited = new boolean[N][N];
			
			int r = 0, c = 0, d = 0;	// 초기화
			visited[0][0] = true;
			int count = 1;
			
			while(true) {
				plain[r][c] = count++;	// 방문 위치 확인
				if(count == N * N + 1) break;
				
				int nr = r + dir[d][0];
				int nc = c + dir[d][1];
				
				if(isPossible(nr, nc)) {	// 갈 수 있으면 이동
					r = nr;
					c = nc;
					visited[nr][nc] = true;
				}
				else {	// 못가면 마킹 숫자 초기화 후 반대방향 탐색
					count--;
					d = direct(d);
				}
			}
			
			sb = new StringBuilder().append("#").append(testCase).append("\n");
			for(int i = 0; i < plain.length; i++) {
				for(int t : plain[i]) {
					sb.append(t).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}
	
	public static boolean isPossible(int r, int c) {
		return ((0 <= r && r < N && 0 <= c && c < N) && visited[r][c] == false);
	}
	
	public static int direct(int d) {
		if(d == 3) return 0;
		return d + 1;
	}
	
	public static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
