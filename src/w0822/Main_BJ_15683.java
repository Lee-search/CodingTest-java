package w0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_15683 {
	
	static int N, M;
	static int[][] plain;
	static boolean[][] visited;
	static List<int[]> cams;
	
	static int[] maxArray;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		plain = new int[N][M];
		visited = new boolean[N][N];
		cams = new ArrayList<>();
		
		int wall = 0;	// 벽 개수
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				plain[i][j] = stoi(st.nextToken());
				if(1 <= plain[i][j] && plain[i][j] <= 5) {
					cams.add(new int[] {i, j}); // 카메라 리스트에 추가
				}
				else if(plain[i][j] == 6) wall += 1;
			}
		} // end of init

		// 각 번호의 카메라가 볼 수 있는 최대 공간 수 저장
		maxArray = new int[cams.size()];
		for(int i = 0; i < cams.size(); i++) {
			camera(i);
		}
		
		System.out.println(N * M - wall - Arrays.stream(maxArray).sum());
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	// num번째 카메라의 감시할 수 있는 값의 최대 계산
	public static void camera(int num) {
		
		int sr = cams.get(num)[0];
		int sc = cams.get(num)[1];
		int max = 0; // 가능한 최대 탐색 범위 (switch 내부 초기화)
		
		int r = sr, c = sc;	// 이동을 위한 지역변수
		
		// 중복방지: visited 한 경우 카운트 하지 않고 넘어감
		switch(plain[sr][sc]) {
		case 1:
			for(int i = 0; i < 4; i++) {
				r = sr; c = sc;
				int sum = 0;	// 현재 탐색한 블럭 수
				do {
					sum += 1;
					r = r + dr[i];
					c = c + dc[i];
				} while(isPossible(r, c));
				
				max = Math.max(max, sum);
			}
			break;
		case 2:
			for(int[] ds : new int[][] {{0, 2},{1, 3}}) {
				r = sr; c = sc;
				int sum = 0;	// 현재 탐색한 블럭 수
				do {
					sum += 1;
					r = r + dr[ds[0]];
					c = c + dc[ds[0]];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[1]];
					c = c + dc[ds[1]];
				} while(isPossible(r, c));
				
				max = Math.max(max, sum);
			}
			break;
		case 3:
			for(int[] ds : new int[][] {{0, 1},{1, 2},{2, 3},{3, 0}}) {
				
				int sum = 0;	// 현재 탐색한 블럭 수
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[0]];
					c = c + dc[ds[0]];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[1]];
					c = c + dc[ds[1]];
				} while(isPossible(r, c));
				
				max = Math.max(max, sum);
			}
			break;
		case 4:
			for(int[] ds : new int[][] {{0,1,2},{1,2,3},{2,3,0},{3,0,1}}) {
				
				int sum = 0;	// 현재 탐색한 블럭 수
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[0]];
					c = c + dc[ds[0]];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[1]];
					c = c + dc[ds[1]];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					sum += 1;
					r = r + dr[ds[2]];
					c = c + dc[ds[2]];
				} while(isPossible(r, c));
				
				max = Math.max(max, sum);
			}
			break;
		case 5:	
			for(int i = 0; i < 4; i++) {
				r = sr; c = sc;
				do {
					max += 1;
					r = r + dr[i];
					c = c + dc[i];
				} while(isPossible(r, c));
			}
			break;
		}
		
		maxArray[num] = Math.max(maxArray[num], max);
	} // end of func
	
	// 범위 밖 또는 벽을 만나면 false 리턴
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M) && plain[r][c] != 6;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
