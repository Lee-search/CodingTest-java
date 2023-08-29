package w0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_15683_감시 {
	
	static int N, M, answer;
	static int[][] plain;

	static int[] selector;

	static List<int[]> cams;

	static int[][] copiedPlain;

	// 1. 중복순열로 가능한 모든 가능성 조회
	// 2. 앞선 코드에 d로 순회하는 부분만 순열값으로 변경
	// 3. copy 맵에 순열로 생성한 모든 경우 표시하고 sum 구함
	// 4. max 값 비교
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		plain = new int[N][M];

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

		selector = new int[cams.size()];	// 카메라 갯수만큼 초기화
		perm(0);
		
		System.out.println(N * M - answer - wall);
	} // end of main

	public static void perm(int cnt) {

		if(cnt == cams.size()) {
			copiedPlain = new int[N][M];
			// 카메라 번호에 대해서
			for(int i = 0; i < cams.size(); i++) {
//				System.out.println("select:" + Arrays.toString(selector));
				// 중복순열로 선택된 d 방향으로 탐색 시작
				camera(i, selector[i]);
			}
			return;
		}

		for(int i = 0; i < 4; i++) {
			selector[cnt] = i;
			perm(cnt + 1);
		}
	}

	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	// num번째 카메라의 감시할 수 있는 값의 최대 계산
	public static void camera(int num, int d) {
		
		int sr = cams.get(num)[0];
		int sc = cams.get(num)[1];
		int r = sr, c = sc;	// 이동을 위한 지역변수 선언

		switch(plain[sr][sc]) {
		case 1:
			do {
				// 카피한 평면에 카메라가 촬영하는 범위 표기
				copiedPlain[r][c] = 1;
				r = r + dr[d];
				c = c + dc[d];
			} while(isPossible(r, c));
			break;

		case 2:
			if(d == 0 || d == 2) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
			}

			else {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
			}
			break;

		case 3:

			if(d == 0) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
			}
			else if(d == 1) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
			}
			else if(d == 2) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
			}
			else {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
			}
			break;

		case 4:

			if(d == 0) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
			}
			else if(d == 1) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
			}
			else if(d == 2) {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[1];
					c = c + dc[1];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
			}
			else {
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[2];
					c = c + dc[2];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[3];
					c = c + dc[3];
				} while(isPossible(r, c));
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[0];
					c = c + dc[0];
				} while(isPossible(r, c));
			}
			break;

		case 5:
			for(int i = 0; i < 4; i++) {
				r = sr; c = sc;
				do {
					copiedPlain[r][c] = 1;
					r = r + dr[i];
					c = c + dc[i];
				} while(isPossible(r, c));
			}
			break;
		}

		int sum = 0;
		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(copiedPlain[i]));
			sum += Arrays.stream(copiedPlain[i]).sum();
		}

//		System.out.println();

//		System.out.println("sum: " + sum);
		answer = Math.max(answer, sum);
	} // end of func
	
	// 범위 밖 또는 벽을 만나면 false 리턴
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < N && 0 <= c && c < M) && plain[r][c] != 6;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
