package others._pad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * R * C 크기의 표
 * - 각 칸에는 지뢰가 있을 수도 없을 수도 있음
 * - 지뢰가 있는 칸 클릭 -> 게임 끝
 * - 지뢰가 없는 칸 클릭 -> 8방에 몇개의 지뢰가 있는지 표시
 *
 * - 8방에 지뢰가 없으면(숫자가 0이면) -> 둘러본 8방의 칸도 숫자 표시함
 * - Q. 최소 몇번을 클릭해야 모든 칸들의 숫자가 표시되는가
 * - 0이 지속되면 8방으로 확산 계속 일어남 -> BFS 탐색 사용
 * - 0인 구역을 최대한 클릭해야함
 */

public class Solution_1868 {
	static int N;
	static char[][] map;
	static boolean[][] visited; //방문확인용 
	static int result;

	//8방 탐색을 위한 델타
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	static Queue<Bomb> queue = new LinkedList<>();
	
	static class Bomb {
		int x;
		int y;
		public Bomb(int x, int y) {
			// TODO Auto-generated constructor stub
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];//N*N크기의 map 생성
			visited = new boolean[N][N];
			
			for(int i=0; i<N;i++) {
				map[i]= br.readLine().toCharArray(); //한 줄씩 공백 기준으로 쪼개 넣기
			}
			//------------INPUT END--------------------------------
			
			//@@ TODO : 최소 클릭수를 구하기 위해서 어떤 곳을 클릭해야 하는지 구현해보세요.
			for (int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {

					if(map[i][j] != '.') { // 지뢰가 없는 지역이 아니면 누르지 않음.
						continue;
					}

					int boom = 0;
					for(int d = 0; d < 8; d++) { // 주변 8방 탐색
						int nr = i + dx[d];
						int nc = j + dy[d];

						if(isRange(nr, nc) && map[nr][nc] == '*') {
							boom++;	// 주변 지뢰 개수 추가
						}
					}

					if(boom == 0) { // 주변에 지뢰가 없음
						// 숫자 표기를 위해 큐에 삽입
						queue.add(new Bomb(i, j));
						bfs();
					}
				}
			}
			
			//@@ TODO : 클릭 횟수를 구해보세요
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == '.') {	// 바뀌지 않은 .의 개수 클릭해줘야함
						result++;
					}
				}
			}

			System.out.printf("#%d %d\n", t, result);
			result = 0;

		}

	}
	
	public static void bfs() {

		while (!queue.isEmpty()) {	// 탐색 후보군인 큐가 빌 때 까지 탐색
			Bomb b = queue.poll();

			int boom = 0;

			for(int d = 0; d < 8; d++) { // 주변 8방 탐색
				int nr = b.x + dx[d];
				int nc = b.y + dy[d];

				if(isRange(nr, nc) && map[nr][nc] == '*') {
					boom++;	// 주변 지뢰 개수 추가
				}
			}

			if(boom == 0) {
				map[b.x][b.y] = 'x';

				for (int d = 0; d < 8; d++) {
					int nr = b.x + dx[d];
					int nc = b.y + dy[d];

					if(!isRange(nr, nc) || map[nr][nc] == '.' || visited[nr][nc]) {
						continue;
					}

					visited[nr][nc] = true;
					queue.add(new Bomb(nr, nc));
				}
			}
			else {
				map[b.x][b.y] = 'x';
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return false;
		return true;
	}

}