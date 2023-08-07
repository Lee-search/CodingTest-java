package w0807;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_D3_1228 {
	
	public static int N, M; // 암호문 길이, 명령어 갯수
	public static List<Integer> password;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("./src/w0807/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			N = stoi(br.readLine());
			password = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				password.add(stoi(st.nextToken()));
			}
			
			M = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				// I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입
				st.nextToken();	// I
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				for(int j = 0; j < y; j++) {
					password.add(x + j, stoi(st.nextToken()));
				}
			}
			
			sb.append("#").append(testCase).append(" ");
			for(int i = 0; i < 10; i++) {
				sb.append(password.get(i)).append(" ");
			}
			sb.append("\n");
		} // end of tc 
			
		System.out.print(sb);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
