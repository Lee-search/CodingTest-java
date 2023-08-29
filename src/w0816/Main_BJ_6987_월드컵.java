package w0816;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_6987_월드컵 {
	
	static int answer;
	static int[][] table;	// 승-패 테이블
	
	static int[] redTeam = new int[15];		// 각 팀의 대전 상대
	static int[] blueTeam = new int[15];	// 조합으로 구현 후 저장
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int testCase = 1; testCase <= 4; testCase++) {
			
			table = new int[6][3];	// 6팀의 승,무,패 저장
			answer = 0;
			boolean flag = false;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 6; i++) {	
				
				table[i][0] = stoi(st.nextToken()); // 승
				table[i][1] = stoi(st.nextToken());	// 무
				table[i][2] = stoi(st.nextToken()); // 패
				
				// 승무패 합이 5가 아닌 경우 제외
				if(Arrays.stream(table[i]).sum() != 5) flag = true;
			} // end of init
			
			if(flag) {	// 예외 발생 -> 다음 tc
				System.out.print(answer + " ");
				continue;
			}
			
			combi();
//			System.out.println(Arrays.toString(redTeam));
//			System.out.println(Arrays.toString(blueTeam));
			
			DFS(0);
			System.out.print(answer + " ");
		} // end of tc
		
	} // end of main

	public static void DFS(int cnt) {
		
		if(cnt == 15) {
			answer = 1;
			return;
		}
		
		int a = redTeam[cnt];	// A팀
		int b = blueTeam[cnt];	// B팀
		
		// A승 : B패
		if(table[a][0] > 0 && table[b][2] > 0) {
			table[a][0] -= 1;
			table[b][2] -= 1;
			DFS(cnt + 1);
			table[a][0] += 1;
			table[b][2] += 1;
		}
		
		// A무 : B무
		if(table[a][1] > 0 && table[b][1] > 0) {
			table[a][1] -= 1;
			table[b][1] -= 1;
			DFS(cnt + 1);
			table[a][1] += 1;
			table[b][1] += 1;
		}
		
		// A패 : B승
		if(table[a][2] > 0 && table[b][0] > 0) {
			table[a][2] -= 1;
			table[b][0] -= 1;
			DFS(cnt + 1);
			table[a][2] += 1;
			table[b][0] += 1;
		}
		
	} // end of func
	
	public static void combi() {
		
		int idx = 0;
		// 두 팀이 경기하는 Combination 생성
		for(int i = 0; i < 6; i++) {
			for(int j = i + 1; j < 6; j++) {
				redTeam[idx] = i;
				blueTeam[idx] = j;
				idx ++;
			}
		}
		
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
