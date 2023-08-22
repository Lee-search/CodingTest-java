import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		adjList = new List[N];
		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		} // end of init
		
		for(int i = 0; i < N - 1; i++ ) {
			visited = new boolean[N];
			visited[i] = true;
			DFS(i, 0);
		}
		
		System.out.println(0);
	} // end of main
	
	public static void DFS(int start, int cnt) {
		
		if(cnt == 4) {
			System.out.println(1);
			System.exit(0);
		} // basis
		
		for(int i = 0; i < adjList[start].size(); i++) {
			int next = adjList[start].get(i);
			if(!visited[next]) { // 연결 관계 있으면,
				visited[next] = true;
				DFS(next, cnt + 1); // 연결관계 확인 후 탐색
				visited[next] = false;
			}
		}
	} // end of DFS
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
