package w1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17472 {
	
	static int N, M;
	static int[][] plain;
	static boolean[][] visited;
	static List<int[]>[] islands;
	static List<Edge> edgeList;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		@Override
		public String toString() {
			return "from " + from + " to " + to + " weight " + weight; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		plain = new int[N][M];
		visited = new boolean[N][M];
		edgeList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int n = stoi(st.nextToken());
				if(n != 0) plain[i][j] = n;
			}
		} // end of init
		
		islands = new List[6 + 1];	// 섬의 최대 개수 6개
		for(int i = 1; i <= 6; i++) islands[i] = new ArrayList<>();
		
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(plain[i][j] == 1 && !visited[i][j]) BFS(i, j, cnt++);
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(plain[i]));
		}
		
		for(int i = 1; i <= 6; i++) {
			List<int[]> island = islands[i];
			
			for(int[] info : island) {
				
				int r = info[0], c = info[1];
				int current = plain[r][c];
				
				// 북으로 이동
				int length = -1;
				while(r > 0) {
					r -= 1;
					length += 1;
					// 다른 섬을 만났을 때,
					if(plain[r][c] > 0) {
						// 같은 섬에 부딫혔으면 탐색 취소
						if(plain[r][c] == current || plain[r + 1][c] == plain[r][c]) break;
						if(length >= 2) {
							edgeList.add(new Edge(current, plain[r][c], length));
						} else break;
					}
				}
				
				// 남으로 이동
				length = -1;
				while(r < N - 1) {
					r += 1;
					length += 1;
					// 다른 섬을 만났을 때,
					if(plain[r][c] > 0) {
						// 같은 섬에 부딫혔으면 탐색 취소
						if(plain[r][c] == current || plain[r - 1][c] == plain[r][c]) break;
						if(length >= 2) {
							edgeList.add(new Edge(current, plain[r][c], length));
						} else break;
					}
				}
				
				// 동으로 이동
				length = -1;
				while(c < M - 1) {
					c += 1;
					length += 1;
					// 다른 섬을 만났을 때,
					if(plain[r][c] > 0) {
						// 같은 섬에 부딫혔으면 탐색 취소
						if(plain[r][c] == current || plain[r][c - 1] == plain[r][c]) break;
						if(length >= 2) {
							edgeList.add(new Edge(current, plain[r][c], length));
						} else break;
					}
				}
				
				// 서로 이동
				length = -1;
				while(c > 0) {
					c -= 1;
					length += 1;
					// 다른 섬을 만났을 때,
					if(plain[r][c] > 0) {
						// 같은 섬에 부딫혔으면 탐색 취소
						if(plain[r][c] == current || plain[r][c + 1] == plain[r][c]) break;
						if(length >= 2) {
							edgeList.add(new Edge(current, plain[r][c], length));
						} else break;
					}
				}
				
			} // end of innerFor
		} // end of outerFor
		
		Collections.sort(edgeList);
		for(Edge o : edgeList) {
			System.out.println(o);
		}
		
	} // end of main
	
	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};
	
	// 1. 노드 번호 구성용 BFS
	public static void BFS(int sr, int sc, int cnt) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			
			int r = q.peek()[0];
			int c = q.poll()[1];
			plain[r][c] = cnt;
			islands[cnt].add(new int[] {r, c});
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(isPossible(nr, nc) && plain[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		} // end of while
	} // end of BFS
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
