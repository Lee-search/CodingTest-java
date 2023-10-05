package w1004;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_5643_키순서 {
	
	static int N, M, answer, tmp;
	static List<Integer>[] downList, upList;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream(new File("./src/w1004/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			N = stoi(br.readLine());
			M = stoi(br.readLine());
			answer = 0;
			downList = new List[N + 1];
			upList = new List[N + 1];
			
			for(int i = 1; i <= N; i++) {
				downList[i] = new ArrayList<>();
				upList[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				downList[a].add(b);
				upList[b].add(a);
			} // end of init 
			
			for(int i = 1; i <= N; i++) {
	        	
	        	tmp = 0;
	        	visited = new boolean[N + 1];
	            down(i);	up(i);
	            // 노드의 위 아래 탐색 결과가 N - 1개가 나와야 순서 파악 가능
	            if(tmp == N - 1) answer += 1;
	        }
			
			sb.append("#" + testCase + " " + answer + "\n");
		} // end of tc
		
		System.out.print(sb.toString());
	} // end of main
	
	// 나보다 큰 사람 찾기
	public static void down(int start) {
		
		visited[start] = true;
		
		for(int i = 0; i < downList[start].size(); i++) {
			int n = downList[start].get(i);
			if(!visited[n]) {
				tmp += 1;
				down(n);
			}
		}

	} // end of down
	
	// 나보다 작은 사람 찾기
	public static void up(int start) {

		visited[start] = true;

		for(int i = 0; i < upList[start].size(); i++) {
			int n = upList[start].get(i);
			if(!visited[n]) {
				tmp += 1;
				up(n);
			}
		}
	} // end of up
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
