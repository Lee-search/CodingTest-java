package w0808;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233{
	
	public static int N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0808/input_1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			
			N = stoi(br.readLine());
			boolean isPossible = true;
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int current = stoi(st.nextToken());	// 현재 위치
				char oper = st.nextToken().charAt(0);	// 연산자
				
				// 연산자이면, 
				if(oper == '+' || oper == '-' || oper == '*' || oper == '/') {
					if(current * 2 > N) {	// 부모가 연산자이면 자식 노드가 2개 있어야함
						isPossible= false;
					}
				}
			}
			
			// 계산이 가능하다면 1, 계산이 불가능할 경우 0을 출력
			sb.append("#").append(testCase).append(" ");
			if(isPossible) sb.append("1").append("\n");
			else sb.append("0").append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
