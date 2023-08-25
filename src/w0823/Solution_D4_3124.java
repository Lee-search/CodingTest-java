package w0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution_D4_3124 {
	
	static int V, E;
	static int[] parents;
	
	static class Edge {
		int from, to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			V = stoi(st.nextToken());	// 정점 수
			E = stoi(st.nextToken());	// 간선 수
			edgeList = new Edge[E];
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = stoi(st.nextToken());
				int to = stoi(st.nextToken());
				long weight = stoi(st.nextToken());
				
				edgeList[i] = new Edge(from, to, weight);
			} // end of init
			
			// 비용이 작은 순으로 정렬
			Arrays.sort(edgeList, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return Long.compare(o1.weight, o2.weight);
				}
			});
			
			makeSet();
			
			long cost = 0;	// 가중치 합
			int count = 0;	// 정점 갯수
			
			for(Edge edge : edgeList) {
				
				if(unionSet(edge.from, edge.to)) {
					cost += edge.weight;
					count += 1;
					if(count == V - 1) break;
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(cost).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static void makeSet() {
		
		parents = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	} // end of make-set
	
	public static int findSet(int a) {
		
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	} // end of find-set
	
	public static boolean unionSet(int a, int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	} // end of unionSet
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
