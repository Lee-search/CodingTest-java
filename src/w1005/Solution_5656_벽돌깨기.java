package w1005;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {

	static int N, W, H, answer, count;
	static int[][] plain, dump;
	
	static int[] selector;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream(new File("./src/w1005/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());	// 떨어트릴 벽돌 개수
			W = stoi(st.nextToken());
			H = stoi(st.nextToken());
			plain = new int[H][W];
			dump = new int[H][W];		// 원본 저장
			
			count = 0;
			answer = 0;
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					int n = stoi(st.nextToken());
					if(n != 0) {
						plain[i][j] = dump[i][j] = n;
						count += 1;
					}
				}
			} // end of init
			
			selector = new int[N];
			permutation(0);
			
			sb.append("#" + testCase + " " + (count - answer) + "\n");
		} // end of tc
		
		System.out.println(sb.toString());
	} // end of main
	
	
	// 어느 위치에 구슬 던질지 중복 순열로 파악
	public static void permutation(int cnt) {
		
		if(cnt == N) {
//			System.out.println("던진 위치: " + Arrays.toString(selector));
			
			List<int[]> brokenList = new ArrayList<>();	// 깨진 벽돌 리스트
			
			for(int i = 0; i < N; i++ ) {
				BFS(selector[i], brokenList);
			}
			
			// broken 된 벽돌 갯수의 최대 값 비교
			answer = Math.max(answer, brokenList.size());
//			print();

			// BFS 종료 후 rollback
//			for(int i = 0; i < brokenList.size(); i++) {
//				int r = brokenList.get(i)[0];
//				int c = brokenList.get(i)[1];
//				plain[r][c] = dump[r][c];
//			}
			for(int i = 0; i < H; i++) {
				plain[i] = dump[i].clone();
			}
			return;
		}
		
		for(int i = 0; i < W; i++) {
			selector[cnt] = i;
			permutation(cnt + 1);
		}
	} // end of func

	public static void drop() {
	
		// 모든 열에 대해 시도
		for(int c = 0; c < W; c++) {
	
			// 해당 열에 떨어트릴 경우 제거되는 맨 윗 벽돌 찾기
			int r = 0;
			while(r < H && plain[r][c] == 0) ++r;
			
			// 벽돌이 존재하지 않으면 다음 열로 건너뛰기
			if(r == H) continue;
			
			// 벽돌이 존재한다면 인접 벽돌 찾기
			BFS(r, )
			// 벽돌 내리기
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// w : 좌표에 구슬 투척
	public static void BFS(int w, List<int[]> broken) {
		
		Queue<int[]> q = new ArrayDeque<>();
//		boolean[][] visited = new boolean[H][W];
		
		// 1. 벽돌에 부딪힐 때 까지 내려가면서 탐색
		for(int i = 0; i < H; i++) {
			if(plain[i][w] != 0) {
				if(plain[i][w] > 1) q.add(new int[] {i, w, plain[i][w]});
//				visited[i][w] = true;
				plain[i][w] = 0;
				break;
			}
		} // end of for
		
		// 2. 벽돌 위치에서 제거 범위만큼 queue에 삽입 후 삭제
		while(!q.isEmpty()) {
			
			int r = q.peek()[0];
			int c = q.peek()[1];
			int size = q.poll()[2];	// 폭발 범위
			
			if(size == 0) continue;
			else if(size == 1) {
				broken.add(new int[] {r, c});
				continue;
			}
			else {
				broken.add(new int[] {r, c});
				// 폭발 범위가 2 이상이면, 폭발 범위만큼 queue에 삽입
				for(int i = 0; i < size; i++) {
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d] * i;
						int nc = c + dc[d] * i;
						
						if(isPossible(nr, nc) && plain[nr][nc] > 0/*!visited[nr][nc]*/) {
							if(plain[i][w] > 1) q.add(new int[] {nr, nc, plain[nr][nc]});
//							visited[nr][nc] = true;
							plain[nr][nc] = 0;
						}
					}
				} // end of for
			} // end of if-else
		} // end of while
		
//		 3. BFS 종료 후 블럭 아래로 한칸 씩 이동
		down();
	} // end of BFS

	private static void down() {

		// 열 기준으로 내리기
		for (int c = 0; c < W; c++) {
			int r = H - 1, nr = -1;
			while (r > 0) {
				if(plain[r][c] == 0) {
					// 빈칸이면 윗 행부터 내릴 벽돌 찾기
					nr = r - 1;
					while(nr > 0 && plain[nr][c] == 0) --nr;
					plain[r][c] = plain[nr][c];
					plain[nr][c] = 0; // 빈칸 처리
				}

				if(nr == 0) break;
				--r;
			}
		}

//		Queue<Integer> line = new ArrayDeque<>();
//		for(int i = 0; i < W; i++) {
//			for(int j = H - 1; j >= 0; j--) {
//				if(plain[j][i] != 0) {
//					line.add(plain[j][i]);
//					plain[j][i] = 0;
//				}
//			}
//			for(int j = H - 1; j >= 0; j--) {
//				if(!line.isEmpty()) plain[j][i] = line.poll();
//			}
//		} // end of for

//		for(int i = H - 2; i >= 0; i--) {
//			for(int j = 0; j < W; j++) {
//				if(plain[i][j] != 0 && plain[i + 1][j] == 0) {
//					int x = i;
//					for(int k = i + 1; k < H; k++) {
//						if(plain[k][j] != 0) break;
//						x = k;
//					}
//					plain[x][j] = plain[i][j];
//					plain[i][j] = 0;
//				}
//			}
//		}
	} // end of func

//	public static void print() {
//		for(int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(plain[i]));
//		}
//		System.out.println();
//	} // end
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
