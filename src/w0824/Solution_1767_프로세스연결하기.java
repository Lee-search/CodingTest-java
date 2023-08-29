package w0824;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_프로세스연결하기 {
	
	static int N, answer;
	static int[][] plain;
	static List<int[]> coreList;
	
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		
		System.setIn(new FileInputStream(new File("./src/w0825/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			coreList = new ArrayList<>();
			answer = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
					if(plain[i][j] == 1) {
						// 가장자리 코어는 제외
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
						coreList.add(new int[] {i, j});
					}
				}
			} // end of init
			
			// 1. 가장자리 코어 제외 나머지 코어에 대해 nCr 검증
			isSelected = new boolean[coreList.size()];
			for(int i = 0; i < coreList.size(); i++) {
				combination(0, 0, coreList.size() - i);
				if(answer != Integer.MAX_VALUE) break;	// 최소값 갱신 시 break
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	public static void combination(int cnt, int start, int R) {
		
		if(cnt == R) {
			
			DFS(0);
			return;
		}
		
		for(int i = start; i < coreList.size(); i++) {
			
			isSelected[i] = true;
			combination(cnt + 1, i + 1, R);
			isSelected[i] = false;
		}
	} // end of combination
	
	
	/**
	 * @param cnt : 코어 번호
	 */
	public static void DFS(int cnt) {
		
		if(cnt == coreList.size()) {
			
			int railCnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(plain[i][j] == 2) railCnt += 1;
				}
			}
			
			answer = Math.min(answer, railCnt);
			return;
		}
		
		// 선택되지 않은 코어는 pass
		if(!isSelected[cnt]) DFS(cnt + 1);
			
		int r = coreList.get(cnt)[0];
		int c = coreList.get(cnt)[1];
		
		boolean isCharged;	// 충전 가능 여부 확인
		int pos;	// 백트래킹을 위해 마지막으로 수정한 위치 저장
		
		// 위로 탐색
		isCharged = true;
		pos = r;
		for(int nr = r - 1; nr >= 0; nr--) {
			if(plain[nr][c] == 0) {
				plain[nr][c] = 2;	// 빈 공간이면 선 연결
				pos = nr;
			}
			else {	// 코어 또는 선을 만나면 충전 불가능
				isCharged = false;
				break;
			}
		}
		
		// 충전이 가능한 경우만 DFS로 다음 노드 탐색
		if(isCharged) DFS(cnt + 1);
		for(int nr = pos; nr < r; nr++) {
			plain[nr][c] = 0;	// 다시 0으로 복구
		}
		
		// 우로 탐색
		isCharged = true;
		pos = c;
		for(int nc = c + 1; nc < N; nc++) {
			if(plain[r][nc] == 0) {
				plain[r][nc] = 2;	// 빈 공간이면 선 연결
				pos = nc;
			}
			else {	// 코어 또는 선을 만나면 충전 불가능
				isCharged = false;
				break;
			}
		}
		
		// 충전이 가능한 경우만 DFS로 다음 노드 탐색
		if(isCharged) DFS(cnt + 1);
		for(int nc = pos; nc > c; nc--) {
			plain[r][nc] = 0;	// 다시 0으로 복구
		}
		
		// 아래로 탐색
		isCharged = true;
		pos = r;
		for(int nr = r + 1; nr < N; nr++) {
			if(plain[nr][c] == 0) {
				plain[nr][c] = 2;	// 빈 공간이면 선 연결
				pos = nr;
			}
			else {	// 코어 또는 선을 만나면 충전 불가능
				isCharged = false;
				break;
			}
		}
		
		// 충전이 가능한 경우만 DFS로 다음 노드 탐색
		if(isCharged) DFS(cnt + 1);
		for(int nr = pos; nr > r; nr--) {
			plain[nr][c] = 0;	// 다시 0으로 복구
		}
		
		// 좌로 탐색
		isCharged = true;
		pos = c;
		for(int nc = c - 1; nc >= 0; nc--) {
			if(plain[r][nc] == 0) {
				plain[r][nc] = 2;	// 빈 공간이면 선 연결
				pos = nc;
			}
			else {	// 코어 또는 선을 만나면 충전 불가능
				isCharged = false;
				break;
			}
		}
		
		// 충전이 가능한 경우만 DFS로 다음 노드 탐색
		if(isCharged) DFS(cnt + 1);
		for(int nc = pos; nc < c; nc++) {
			plain[r][nc] = 0;	// 다시 0으로 복구
		}
		
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
