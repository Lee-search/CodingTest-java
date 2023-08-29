package w0804;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_D3_1225_암호생성기 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0804/input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int testCase = 0;
		while(testCase < 10) {
			testCase = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			Deque<Integer> q = new ArrayDeque<>();
			for(int i = 0; i < 8; i++) q.addLast(Integer.parseInt(st.nextToken()));
			
			boolean isMade = false;
			while(!isMade) {
				for(int i = 1; i <= 5; i++) {
					int tmp = q.pollFirst();
//					System.out.print("pop: " + tmp + " ");
					
					tmp -= i;	// 첫 자리수에서 i 빼고 push
					if(tmp < 0) tmp = 0;
					
					q.addLast(tmp);
//					System.out.println("push: " + tmp);
					
					if(tmp == 0) {	// 0 이 생기면 break;
						isMade = true;
						break;
					}
				}
			} // while 탈출한 경우 -> 비밀번호 완성
			
			sb.append("#").append(testCase).append(" ");
			while(!q.isEmpty()) sb.append(q.pollFirst()).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
