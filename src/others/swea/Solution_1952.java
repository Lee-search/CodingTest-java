package w0807;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1952 {
	
	public static int[] tickets;
	public static int[] plans;
	public static int[] myPlan;
	public static int answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0807/sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			tickets = new int[4];
			plans = new int[12];
			myPlan = new int[12];
			
			st = new StringTokenizer(br.readLine());	// 이용권 가격
			for(int i = 0; i < 4; i++) tickets[i] = stoi(st.nextToken());
			
			answer = tickets[3];	// 최소값의 기준을 연권으로 잡고 시작
			
			st = new StringTokenizer(br.readLine());	// 12개월 이용 계획
			for(int i = 0; i < 12; i++) plans[i] = stoi(st.nextToken());
			
			DFS(0);
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	} // end of main
	
	
	public static void DFS(int cnt) {	// cnt: 0~11 + 1월
		
		if(cnt >= 12) {
			answer = Math.min(answer, Arrays.stream(myPlan).sum());
			return;
		}
		
		// 1일권
		myPlan[cnt] = plans[cnt] * tickets[0];	// 해당 월의 가격 저장
		DFS(cnt + 1);
		
		// 1달권
		myPlan[cnt] = tickets[1];
		DFS(cnt + 1);
		
		// 3달권
		if(cnt == 10) {	// 11월
			myPlan[cnt] = tickets[2]; myPlan[cnt + 1] = 0;
			DFS(cnt + 1);
		}
		else if(cnt == 11) { // 12월
			myPlan[cnt] = tickets[2];
			DFS(cnt + 2);
		}
		else {
			myPlan[cnt] = tickets[2]; myPlan[cnt + 1] = 0; myPlan[cnt + 2] = 0;
			DFS(cnt + 3);
		}
	}
		
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
