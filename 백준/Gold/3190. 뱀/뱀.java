import java.io.*;
import java.util.*;

public class Main {
	
	static int N, K, L, Answer;
	static int[][] plain;
	static Map<Integer, Character> dirInfos;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());	// 보드 크기
		K = stoi(br.readLine());	// 사과 갯수
		plain = new int[N + 1][N + 1];
		dirInfos = new HashMap<>();
		
		// 사과의 위치
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			
			plain[r][c] = 1;	// 사과를 1로 표시
		}
		
		L = stoi(br.readLine());	// 뱀의 방향 전환 횟수
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			char c = st.nextToken().toCharArray()[0];
			// 방향전환 정보 저장
			dirInfos.put(x, c);
		}
		
		start();	// 시뮬레이션 시작
		
		System.out.println(Answer);
		
	} // end of main
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static void start() {
		
		int second = 0;	// 경과 시간
		int dir = 0;	// 현재의 머리 방향
		// Deque의 First를 머리로, Last를 꼬리로 저장
		Deque<int[]> snake = new ArrayDeque<>();
		snake.add(new int[] {1, 1});	// 초기값 저장
		
		while(true) {
			// 초 증가
			second += 1;
			
			// 머리 이동 위치 확인
			int r = snake.getFirst()[0];
			int c = snake.getFirst()[1];
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 벽인지 확인
			if(nr <= 0 || nr > N || nc <= 0 || nc > N) {
				
				Answer = second;
				return;	// 벽에 부딫혔으므로 게임 종료
			}
			
			// 자기 몸통에 부딫혔는지 확인
			for(int[] info : snake) {
				
				if(info[0] == nr && info[1] == nc) {
					
					Answer = second;
					return;	// 자기 자신에 부딫혔으므로 게임 종료
				}
			}
			
			// 머리 정보 추가
			snake.addFirst(new int[] {nr, nc});
			
			// 사과가 있으면? 지도에서 사과 제거 후 그대로
			if(plain[nr][nc] == 1) {
				plain[nr][nc] = 0;
			}
			// 사과가 없으면? 꼬리칸에 대한 정보 제거
			else {
				snake.removeLast();
			}
			
			// 방향 전환 정보가 있는지 확인
			if(dirInfos.get(second) != null) {
				
				// 있다면 고개 돌리기
				dir = getDir(dir, dirInfos.get(second));
			}
			
//			System.out.println("현재 초: " + second);
//			System.out.print("현재 뱀 모습: ");
//			for(int[] info : snake) {
//				System.out.print("[" + info[0] + ", " + info[1] + "], ");
//			}
//			System.out.println();
		}
	} // end of func
	
	// 방향 전환 과정을 담당하는 함수
	static int getDir(int dir, char ch) {
		
		if(ch == 'L') {	// 왼쪽으로 고개 돌림
			
			if(dir == 0) dir = 3;
			else dir -= 1;
		}
		else {	// 오른쪽으로 고개 돌림
			
			if(dir == 3) dir = 0;
			else dir += 1;
		}
		
		return dir;
	} // end of func
	
	static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi

}
