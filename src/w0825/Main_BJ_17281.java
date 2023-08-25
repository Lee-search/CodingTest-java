package w0825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17281 {
	
	static int N, answer = 0;
	static int[][] infoList;
	
	static boolean[] isSelected;
	static int[] selector;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		infoList = new int[N][9];	// N번째 이닝에 1~9번 플레이어의 정보 저장
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int p = 0; p < 9; p++) {
				infoList[i][p] = stoi(st.nextToken());
			}
		} // end of init
		
		isSelected = new boolean[9];
		selector = new int[9];
		
		permutation(0);
		System.out.println(answer);
		
	} // end of main
	
	public static void permutation(int cnt) {
		
		if(cnt == 9) {
			if(selector[3] == 0) {	// 4번타자는 1번
				//System.out.println(Arrays.toString(selector));
				play();
			}
			return;
		} // basis
		
		for(int i = 0; i < 9; i++ ) {
			
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			selector[cnt] = i;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
	
	public static void play() {
		
		int player = 0;	// 플레이어 정보
		int score = 0;
		int result;
		
		for(int i = 0; i < N; i++) {
			int outCnt = 0;
			Queue<Integer> ground = new ArrayDeque<>();
			ground.offer(0);	// 3루
			ground.offer(0);	// 2루
			ground.offer(0);	// 1루
			
			while(outCnt < 3) {
				
				// 해당 플레이어의 결과
				result = infoList[i][selector[player]];
				//System.out.println(i + "번째 이닝의 " + player + "번타자" + selector[player] + "의 결과: " + result);
				
				if(result == 0) outCnt += 1;
				else if(result == 1) {
					score += ground.poll();
					ground.offer(1);
				}
				else if(result == 2) {
					score += ground.poll();
					score += ground.poll();
					ground.offer(1);
					ground.offer(0);
				}
				else if(result == 3) {
					score += ground.poll();
					score += ground.poll();
					score += ground.poll();
					ground.offer(1);
					ground.offer(0);
					ground.offer(0);
				}
				else if(result == 4) {
					score += 1;
					score += ground.poll();
					score += ground.poll();
					score += ground.poll();
					ground.offer(0);
					ground.offer(0);
					ground.offer(0);
				}
				
				// 다음 플레이어 이동
				player = (player + 1) % 9;
			}
		}
		
		answer = Math.max(answer, score);
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
