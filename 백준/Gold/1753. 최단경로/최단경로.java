import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static List<Node>[] adjList;
	
	static boolean[] visited;
	static int[] dist;
	
	static class Node implements Comparable<Node> {
		
		int v, w;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	} // end of class
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = stoi(st.nextToken()); 	// 정점 수
		E = stoi(st.nextToken());	// 간선 수
		
		adjList = new List[V + 1];	// 연결 정보 초기화
		for(int i = 0; i <= V; i++)
			adjList[i] = new ArrayList<>();
		
		int start = stoi(br.readLine());	// 시작 정점
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = stoi(st.nextToken());
			int v = stoi(st.nextToken());
			int w = stoi(st.nextToken());
			
			adjList[u].add(new Node(v, w));
		} // end of init
		
		dijkstra(start);
		
		for(int i = 1; i <= V; i++) {
			sb.append(dist[i] != INF ? dist[i] : "INF").append("\n");
		}
		
		System.out.println(sb);
	}// end of main
	
	public static void dijkstra(int start) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited = new boolean[V + 1];
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			
			Node now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			for(Node next : adjList[now.v]) {
				// 기존에 next로 가는 weight보다
				// now를 거쳐 next로 가는 weight가 더 작은 경우
				if(dist[next.v] > dist[now.v] + next.w) {
					
					dist[next.v] = dist[now.v] + next.w;
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
		} // end of while
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
