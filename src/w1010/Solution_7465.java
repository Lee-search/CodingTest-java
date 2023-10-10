package w1010;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7465 {
	
	static int N, M, answer;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
	
		System.setIn(new FileInputStream(new File("./src/w1010/input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken()); // 사람 수
			M = stoi(st.nextToken()); // 관계 수
			make();
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				union(a, b);
			}
			
			// 자기 자신이 부모노드인 경우 -> 그룹의 개수
			answer = 0;
			for(int i = 1; i <= N; i++) {
				if(parents[i] == i) answer += 1;
			}
			
			sb.append("#" + testCase + " " + answer + "\n");
		} // end of tc
		
		System.out.print(sb.toString());
	} // end of main
	
	public static void make() {
		// 부모 트리 배열 생성 후 자기자신 초기화
		parents = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		// 자기자신이 부모노드인 경우 리턴, 아니면 부모찾아서 리턴
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return;
		if(aRoot > bRoot) parents[aRoot] = bRoot;
		else parents[bRoot] = aRoot;
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
