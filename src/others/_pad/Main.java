package others._pad;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int R, C, T;
	public static int[][] plain;
	public static int[][] cleaner;
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		T = stoi(st.nextToken());
		plain = new int[R][C];
		cleaner = new int[2][2];	// 클리너 위,아래
		
		int t = 0;	// cleaner 변수 할당을 위한 임시 변수
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				plain[i][j] = stoi(st.nextToken());
				if(plain[i][j] == -1) {
					cleaner[t++] = new int[] {i, j};
				}
			}
		} // end of input
		
//		System.out.println("---확산전---");
//		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
		
		for(int i = 0; i < T; i++) {
			// 순차 탐색이 아닌 동시 탐색을 위해 deepcopy
			int[][] cpPlain = new int[R][C];
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					cpPlain[r][c] = plain[r][c];
				}
			}
			
			
			spread(cpPlain);	// 미세먼지 퍼짐
			clean();	// 공기청정기 작동
		}
		
		System.out.println("---확산후---");
		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
	} // end of main
	
	public static void clean() {
		
		int upR = cleaner[0][0], upC = cleaner[0][1];		// 공기청정기는 윗칸에서 2칸 이상 떨어져있음
		int downR = cleaner[1][0], downC = cleaner[1][1];	// 공기청정기 위 아래 좌표 입력
			
		// 1. 상단 쿨링
		// 공청기 -> 좌상단, 역순으로 돌아야 기존 값 저장 가능
		for(int r = upR - 1, c = upC; r > 0; r--) {
			plain[r][c] = plain[r - 1][c];
		}
		
		// 좌상단 -> 우상단
		for(int r = 0, c = 0; c < C - 1; c++) {
			plain[r][c] = plain[r][c + 1];
		}
		
		// 우상단 -> 우하단
		for(int r = 0, c = C - 1; r < upR; r++) {
			plain[r][c] = plain[r + 1][c];
		}
		
		// 우하단 -> 공청기
		for(int r = upR, c = C - 1; c > upC; c--) {
			plain[r][c] = plain[r][c - 1];
		}
		
		// 공기청정기가 빨아들인 먼지 처리
		plain[upR][upC + 1] = 0;
		
		// 2. 하단 쿨링
		// 공청기 -> 좌하단
		for(int r = downR + 1, c = downC; r < R; r++) {
			plain[r][c] = plain[r - 1][c];
		}
		
		// 좌하단 -> 우하단
		for(int r = downR - 1, c = 1; c < C; c++) {
			plain[r][c] = plain[r][c - 1];
		}
		
		// 우하단 -> 우상단
		for(int r = R - 1, c = C - 1; r > downR; r--) {
			plain[r][c] = plain[r - 1][c];
		}
		
		// 우상단 -> 공기청정기
		for(int r = downR, c = C - 1; c > downC + 1; c--) {
			plain[r][c] = plain[r][c - 1];
		}
		
		System.out.println("---회전후---");
		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
	}
	
	public static void spread(int[][] copiedPlain) {
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				
				int value = copiedPlain[r][c] / 5;
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];	// 맵밖 or 클리너랑 만나면 확산 X
					
					if(isPossible(nr, nc) && plain[nr][nc] != -1) {
						plain[r][c] -= value;
						plain[nr][nc] += value;	// 옆으로 확산
					}
				}
			}
		}
		
		System.out.println("---확산후---");
		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < R && 0 <= c && c < C);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}