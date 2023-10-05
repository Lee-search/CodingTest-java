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
//			System.out.println("broken size : " + brokenList.size());
			
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
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// w : 좌표에 구슬 투척
	public static void BFS(int w, List<int[]> broken) {
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];
		
		// 1. 벽돌에 부딪힐 때 까지 내려가면서 탐색
		for(int i = 0; i < H; i++) {
			if(plain[i][w] != 0) {
				q.add(new int[] {i, w, plain[i][w]});
				visited[i][w] = true;
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
						
						if(isPossible(nr, nc) && !visited[nr][nc]) {
							q.add(new int[] {nr, nc, plain[nr][nc]});
							visited[nr][nc] = true;
							plain[nr][nc] = 0;
						}
					}
				} // end of for
			} // end of if-else
		} // end of while
		
//		System.out.println("내려가기 전 ");
//		print();
		
//		 3. BFS 종료 후 블럭 아래로 한칸 씩 이동
		Queue<Integer> line = new ArrayDeque<>();
		
		for(int i = 0; i < W; i++) {
			for(int j = H - 1; j >= 0; j--) {
				if(plain[j][i] != 0) {
					line.add(plain[j][i]);
					plain[j][i] = 0;
				}
			}
			for(int j = H - 1; j >= 0; j--) {
				if(!line.isEmpty()) plain[j][i] = line.poll();
			}
		}
		
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
		
//		System.out.println("내려간 후 ");
//		print();
		
	} // end of BFS
	
	public static void print() {
		for(int i = 0; i < H; i++) {
			System.out.println(Arrays.toString(plain[i]));
		}
		System.out.println();
	} // end
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
