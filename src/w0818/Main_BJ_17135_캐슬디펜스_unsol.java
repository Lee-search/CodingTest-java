package w0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_17135_캐슬디펜스_unsol {
	
	static int N, M, D, answer;
	static int[][] plain, dump;
	
	static int enemiCnt, dumpCnt;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());	// 행
		M = stoi(st.nextToken());	// 열
		D = stoi(st.nextToken());	// 공격 가능 거리
		plain = new int[N][M];
		dump = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				plain[i][j] = stoi(st.nextToken());
				dump[i][j] = plain[i][j];
				if(plain[i][j] == 1) {
					enemiCnt += 1;
					dumpCnt += 1;
				}
			}
		} // end of init
		
		isSelected = new boolean[M]; // 배치한 궁수 표기
		combinations(0, 0);
		System.out.println(answer);
	} // end of main
	
	// 1. 조합을 통해 배치 가능한 경우의 수 구하기
	public static void combinations(int cnt, int start) {
		
		if(cnt == 3) {
			shoot();
			reset();
			return;
		} // basis
		
		for(int i = start; i < M; i++) {
			isSelected[i] = true;
			combinations(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	} // end of func
	
	public static int[] dr = {0, -1, 0};
	public static int[] dc = {-1, 0, 1};
	
	// 2. 해당 배치에서 전투 진행, 최대값 갱신
	public static void shoot() {
		
		int sum = 0;	// 해당 전투의 킬 수
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> list = new ArrayList<>();
		
		while(enemiCnt > 0) {

			for(int i = 0; i < M; i++) // R, C, 이동거리
				if(isSelected[i]) q.offer(new int[] {N - 1, i, 1});
			
			while(!q.isEmpty()) {
				
				int[] info = q.poll();
				int r = info[0], c = info[1], dist = info[2];
				
				if(dist > D) continue;	// 발사 가능한 최대 거리 초과
				
				if(plain[r][c] == 1) {
					for(int i = 0; i < list.size(); i++) {
						if(list.get(i)[0] == r && list.get(i)[1] == c) continue;
					}
					list.add(new int[] {r, c});
				}

				for(int i = 0; i < 3; i++) {
					int nr = r + dr[i], nc = c + dc[i];
					if(isPossible(nr, nc)) {
						q.offer(new int[] {nr, nc, dist + 1});
						break;
					}
				}
			} // end of outer itor
			
			for(int i = 0; i < list.size(); i++) {
				int r = list.get(i)[0];
				int c = list.get(i)[1];
				
				if(plain[r][c] == 1) {
					plain[r][c] = 0;
					sum += 1;
					enemiCnt -= 1;
				}
			}
			
			move();
			list  = new ArrayList<>();
		}
		
		answer = Math.max(answer, sum);
	} // end of func
	
	// 3. 궁수가 화살 쏜 후 적 전진
	public static void move() {

		for(int i = N - 1; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				if(plain[i][j] == 1) {
					plain[i][j] = 0;
					enemiCnt -= 1;
					if(!isPossible(i + 1, j)) continue;
					plain[i + 1][j] = 1;
					enemiCnt += 1;
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(plain[i]));
//		}
//		System.out.println();
	} // end of func
	
	// 4. 첫 시뮬레이션 끝난 후 초기화
	public static void reset() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				plain[i][j] = dump[i][j];
			}
		}
		enemiCnt = dumpCnt;
	} // end of func

	public static int getDist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
