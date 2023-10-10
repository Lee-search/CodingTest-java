
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int K, answer;
	static Deque<Integer>[] gearList;

	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream(new File("./src/w1010/input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			K = stoi(br.readLine()); // 회전 횟수 K
			gearList = new Deque[5];
			
			for(int i = 1; i <= 4; i++) {	// 기어 4개 입력
				st = new StringTokenizer(br.readLine());
				
				Deque<Integer> gear = new LinkedList<>();
				for(int j = 0; j < 8; j++) 
					gear.add(stoi(st.nextToken()));
				gearList[i] = gear;
			}
			
//			print();
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int n = stoi(st.nextToken()); // 자석 번호
				int d = stoi(st.nextToken()); // 회전 방향
				
				checkLeft(n - 1, -1 * d);
				checkRight(n + 1, -1 * d);
				rotate(n, d);
//				print();
			}
			
			answer = 0;
			// N 극이 0, S 극이 1
			for(int i = 1; i <= 4; i++) {
				answer += gearList[i].getFirst() * (int) Math.pow(2, i - 1);
			}
			sb.append("#" + testCase + " " + answer + "\n");
		} // end of tc
	
		System.out.println(sb.toString());
	} // end of main
	
	public static void checkLeft(int n, int d) {

		if(n < 1) return;
		// 왼쪽 기준 이전값인 오른쪽의 6번 비교
		if(((List) gearList[n + 1]).get(6) != ((List)gearList[n]).get(2)) {
			checkLeft(n - 1, -1 * d);
			rotate(n, d);
		}
	}
	
	public static void checkRight(int n, int d) {
		
		if(n > 4) return;
		// 오른쪽 기준 이전값인 왼쪽의 2번 비교 
		if(((List) gearList[n - 1]).get(2) != ((List)gearList[n]).get(6)) {
			checkRight(n + 1, -1 * d);
			rotate(n, d);
		}
	}
	
	public static void rotate(int n, int d) {
		// n번 톱니 회전
		if(d == 1) {		// 시계방향
			int tmp = gearList[n].pollLast();
			gearList[n].addFirst(tmp);
		}
		else if(d == -1) {	// 반시계 방향
			int tmp = gearList[n].pollFirst();
			gearList[n].addLast(tmp);
		}
	}
	
//	public static void print() {
//		System.out.println("1번톱니 : " + gearList[1]);
//		System.out.println("2번톱니 : " + gearList[2]);
//		System.out.println("3번톱니 : " + gearList[3]);
//		System.out.println("4번톱니 : " + gearList[4]);
//		System.out.println();
//	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
