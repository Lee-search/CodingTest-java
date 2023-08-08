import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int R, C, T;
	public static int[][] plain;
	public static int[] cleaner;
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		T = stoi(st.nextToken());
		plain = new int[R][C];
		cleaner = new int[2];	// 클리너 위,아래
		
		int t = 0;	// cleaner 변수 할당을 위한 임시 변수
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				plain[i][j] = stoi(st.nextToken());
				if(plain[i][j] == -1) {
					cleaner[t++] = i;
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
		
		int sum = 0;
		for(int i = 0; i < R; i++)
			sum += Arrays.stream(plain[i]).sum();
		sum += 2;	// 공기청징기 -1 * 2
		
		System.out.println(sum);
		
//		System.out.println("---확산후---");
//		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
	} // end of main
	
	// 공기청정기는 항상 1번 열에 설치되어 있음
	public static void clean() {
		
		int upR = cleaner[0];		// 공기청정기는 윗칸에서 2칸 이상 떨어져있음
		int downR = cleaner[1];	// 공기청정기 위 아래 좌표 입력
			
		// 1. 상단 쿨링
		// (0,0) -> (upR, upC)
		for(int r = upR; r > 0; r--) {
			plain[r][0] = plain[r - 1][0];
		}
		
		// (0, C - 1) -> (0, 0)
		for(int c = 0; c < C - 1; c++) {
			plain[0][c] = plain[0][c + 1];
		}
		
		// (upR, C - 1) -> (0, C - 1)
		for(int r = 0; r < upR; r++) {
			plain[r][C - 1] = plain[r + 1][C - 1];
		}
		
		// (upR, 0) -> (upR, C - 1)
		for(int c = C - 1; c > 0; c--) {
			plain[upR][c] = plain[upR][c - 1];
		}
		
		// 2. 하단 쿨링
		// (R - 1, 0) -> (downR, 0)
		for(int r = downR; r < R - 1; r++) {
			plain[r][0] = plain[r + 1][0];
		}
		
		// (R - 1, C - 1) -> (R - 1, 0)
		for(int c = 0; c < C - 1; c++) {
			plain[R - 1][c] = plain[R - 1][c + 1];
		}
		
		// (downR, C - 1) -> (R - 1, C - 1)
		for(int r = R - 1; r > downR; r--) {
			plain[r][C - 1] = plain[r - 1][C - 1];
		}
				
		// (downR, 0) -> (downR, C - 1)
		for(int c = C - 1; c > 0; c--) {
			plain[downR][c] = plain[downR][c - 1];
		}
		
		// 항상 0 또는 -1 인 자리 처리
		plain[upR][0] = -1; plain[upR][1] = 0;
		plain[downR][0] = -1; plain[downR][1] = 0;
		
//		System.out.println("---회전후---");
//		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
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
		
//		System.out.println("---확산후---");
//		for(int i = 0; i < R; i++) System.out.println(Arrays.toString(plain[i]));
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < R && 0 <= c && c < C);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}