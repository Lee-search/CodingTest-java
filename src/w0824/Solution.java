package w0824;

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

public class Solution {
	
	static int N, answer;
	static int[][] plain;
	
	static int coreCnt;
	static List<int[]> cores;
	
	static int[] selector;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream(new File("./src/w0824/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= 3; testCase++) {
			
			N = stoi(br.readLine());
			plain = new int[N][N];
			cores = new ArrayList<>();
			answer = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					plain[i][j] = stoi(st.nextToken());
					if(plain[i][j] == 1) {
						
						coreCnt += 1; // 코어 갯수 저장
						// 이미 가장자리에 있어서 전원이 들어오는 코어 pass
						if(i == 0 || i == N - 1 || j == 0 || j == N - 1) continue;
						cores.add(new int[] {i, j});
						
					}
				}
			} // end of init
			
//			System.out.println("---Cores---");
//			for(int i = 0; i < cores.size(); i++) 
//				System.out.print(Arrays.toString(cores.get(i)) + ", ");
//			System.out.println();
			
			// 1. 코어 배열 순서를 순열로 나열
			isSelected = new boolean[cores.size()];
			selector = new int[cores.size()];	
			permutation(0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static void permutation(int cnt) {
		
		if(cnt == cores.size()) {
			// 코어를 뽑는 해당 경우에 대해 DFS 탐색
			DFS(0);
			return;
		} // basis
		
		for(int i = 0; i < cores.size(); i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			selector[cnt] = i;
			permutation(cnt + 1);
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
		return cnt - coreCnt;
	}
	
	/**
	 * cnt번째 코어에 대해서 DFS 탐색
	 * @param cnt : permutation으로 선택된 코어 번호
	 */
	public static void DFS(int cnt) {
		
		if(cnt == cores.size()) {
			answer = Math.min(answer, getWires());
			return;
		} // basis
		
		
		int[] core = cores.get(cnt);
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
		
		DFS(cnt + 1);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
