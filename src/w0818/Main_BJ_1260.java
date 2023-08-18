package w0818;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1260 {
	
	static int N, M, V;
	static int[][] adjMatrix;
	static StringBuilder sb;
	static boolean[] visited; // DFS용 방문 확인 배열
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();	// 탐색 결과 저장 위해 전역 선언
		
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());	// 정점
		M = stoi(st.nextToken());	// 간선
		V = stoi(st.nextToken());	// 시작 번호
		
		adjMatrix = new int[N + 1][N + 1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = stoi(st.nextToken());
			int to = stoi(st.nextToken());
			
			adjMatrix[from][to] = 1;
			adjMatrix[to][from] = 1;	// 방문처리
		} // end of init
		
		visited = new boolean[N + 1];
		DFS(V);
		sb.append("\n");
		BFS(V);
		
		System.out.print(sb);
	} // end of main
	
	public static void DFS(int current) {
		
		visited[current] = true;
		sb.append(current).append(" "); // 방문
		
		for(int i = 0; i <= N; i++) {
			
			if(!visited[i] && adjMatrix[current][i] == 1) {
				visited[i] = true;
				DFS(i);
				//visited[i] = false; // 백트래킹으로 다른 이동가능한 경로를 탐색토록 변경 가능함
			}
		}
	} // end of DFS
	
	public static void BFS(int start) {
		
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			
			int current = q.poll();
			sb.append(current).append(" "); // 방문
			
			for(int i = 1; i <= N; i++) { // 인접행렬 탐색
				
				if(!visited[i] && adjMatrix[current][i] == 1) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	} // end of bfs
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
