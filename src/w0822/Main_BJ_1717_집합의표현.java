package w0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1717_집합의표현 {
	
	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		parents = new int[N + 1];
		makeSet();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = stoi(st.nextToken());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			
			// 0: Union
			if(cmd == 0) {
				unionSet(a, b);
			}
			// 1: Find
			else if(cmd == 1) {
				int aRoot = findSet(a);
				int bRoot = findSet(b);
				
				if(aRoot == bRoot) sb.append("yes\n");
				else sb.append("no\n");
			}
		}
		
		System.out.print(sb);
	} // end of main
	
	public static void makeSet() {
		
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	} // end of make
	
	public static int findSet(int a) {
		
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	} // end of find
	
	public static boolean unionSet(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	} // end of union
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
