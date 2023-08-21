package others._pad;

/**
 * https://www.acmicpc.net/problem/16987
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_16987 {
	
	static int N, answer;
	static int[][] eggs;
	
	static int[] selector;
	static boolean[] isSeleted;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		eggs = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggs[i][0] = stoi(st.nextToken());	// 내구도
			eggs[i][1] = stoi(st.nextToken());	// 무게
		} // end of init
		
		selector = new int[N];
		isSeleted = new boolean[N];
		permutations(0);
		
		System.out.println(answer);
		
	} // end of main
	
	public static void collision() {
		
		int breakSum = 0;	// 깨진 개수
		int left = 0, right = 1;
		if(right == N) return;	// 깨지지 않은 다른 계란 없음
		
		while(true) {
			
			if(left >= N - 1) break;
			
			// 왼손에 든 계란 정보
			int leftVal = eggs[selector[left]][0];	// 내구도
			int leftWgt = eggs[selector[left]][1];	// 무게
			
			// 오른손에 든 계란 정보
			int rightVal = eggs[selector[right]][0];
			int rightWgt = eggs[selector[right]][1];
			
			if(leftVal == 0) {	// 왼손 계란 X
				breakSum += 1;
				
				left += 1;
				right += 1;
				continue;
			}

			eggs[selector[left]][0] -= rightWgt;
			eggs[selector[right]][0] -= leftWgt;
			
			if(rightVal == 0) {	// 오른손 계란 X
				breakSum += 1;
				
				right += 1;
			}
		}
		
		answer = Math.max(answer, breakSum);
	} // end of func
	
	public static void permutations(int cnt) {
		
		if(cnt == N) {
			collision();
			return;
		} // basis
		
		// 조건: N <= 8, 복잡도 8! -> 완탐 풀이 가능
		for(int i = 0; i < N; i++) {
			
			if(isSeleted[i]) continue;
			
			isSeleted[i] = true;
			selector[cnt] = i;
			permutations(cnt + 1);
			isSeleted[i] = false;
		}
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
