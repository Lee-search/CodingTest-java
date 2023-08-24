package w0824;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, answer;
	static int[][] plain;

	static List<int[]> coreList;

	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream(new File("./src/w0824/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			coreList = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
					if(plain[i][j] == 1) {
						// 가장자리 유닛 제외
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
						coreList.add(new int[] {i, j});
					}
				}
			} // end of init

			answer = Integer.MAX_VALUE;
			isSelected = new boolean[coreList.size()];
			
			// 코어 배열 순서를 조합으로 나열
			for(int i = coreList.size(); i >= 0; i--) {
				combination(0, 0, i);
				if(answer != Integer.MAX_VALUE) break; // 최솟값이 갱신되면 종료
			}

			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main

	/**
	 * N개의 코어 중 R개를 선택하는 조합 생성
	 * @param cnt
	 * @param start
	 * @param r
	 */
	public static void combination(int cnt, int start, int r) {
		
		if(cnt == r) {
			// 코어를 뽑는 해당 경우에 대해 DFS 탐색
			DFS(0);
			return;
		} // basis
		
		for(int i = start; i < coreList.size(); i++) {
			
			isSelected[i] = true;
			combination(cnt + 1, i + 1, r);
			isSelected[i] = false;
		}
	}

	/**
	 * @return 연결한 전선의 갯수
	 */
	public static int getWires() {
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			cnt += Arrays.stream(plain[i]).sum();
		}
		return cnt - N;
	}
	
	/**
	 * cnt번째 코어에 대해서 DFS 탐색
	 * @param cnt : combination 으로 선택된 코어 번호
	 */
	public static void DFS(int cnt) {

		// cnt가 지정한 r만큼 도달했으면 연결된 코어 수 구하고 return
		if(cnt == coreList.size()) {
			answer = Math.min(answer, getWires());
			return;
		} // basis

		// 조합에서 선택되지 않았으면 다음 코어에 대해 탐색
		if(!isSelected[cnt]) DFS(cnt + 1);

		int[] core = coreList.get(cnt);
		int r = core[0];
		int c = core[1];
		
		boolean isCharged;	// 충전 가능 여부 확인
		int pos;	// 백트래킹을 위해 마지막으로 수정한 위치 저장
		
		// 위로 탐색
		isCharged = true;
		pos = r;
		for(int nr = r - 1; nr >= 0; nr--) {
			if(plain[nr][c] == 0) {
				plain[nr][c] = 1;	// 빈 공간이면 선 연결
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
				plain[r][nc] = 1;	// 빈 공간이면 선 연결
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
				plain[nr][c] = 1;	// 빈 공간이면 선 연결
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
				plain[r][nc] = 1;	// 빈 공간이면 선 연결
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
