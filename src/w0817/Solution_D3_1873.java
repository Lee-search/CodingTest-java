package w0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1873 {
	
	public static int H, W, N;
	public static char[][] plain;
	
	public static int curRow, curCol, curDir;
	public static int[] dr = {-1, 0, 1, 0};	// 상, 우, 하, 좌
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream(new File("./src/w0817/input_3.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			H = stoi(st.nextToken());	// 높이
			W = stoi(st.nextToken());	// 너비
			plain = new char[H][W];		// 게임 맵
			
			for(int i = 0; i < H; i++) {
				String line = br.readLine();
				for(int j = 0; j < W; j++) {
					plain[i][j] = line.charAt(j);
					if(plain[i][j] == '^' || plain[i][j] == '>' || plain[i][j] == 'v' || plain[i][j] == '<') {
						curRow = i;
						curCol = j;
						setDir(plain[i][j]);	// 탱크 위치정보 처리
					}
						
				}
			} // end of init
			
			N = stoi(br.readLine());	// 명령 갯수
			exec(br.readLine());		// 명령 수행
			
			sb.append("#").append(testCase).append(" ");
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					sb.append(plain[i][j]);
				} 
				sb.append("\n");
			} // end of write
			
		} // end of tc
		
		System.out.println(sb);
	} // end of main
	
	public static void exec(String command) {
		
		for(int i = 0; i < N; i++) {

			switch(command.charAt(i)) {
			case 'U':
				// 위로 회전 후 이동
				setDir('^');
				plain[curRow][curCol] = '^';	// 이동할 수 없더라도 방향은 바꾸어야함
				
				if(!isPossible(curRow + dr[curDir], curCol + dc[curDir])) continue;	// 맵 밖 제외
				
				if(plain[curRow + dr[curDir]][curCol + dc[curDir]] == '.') {
					// 기존 위치에서 탱크 삭제
					plain[curRow][curCol] = '.';
					
					curRow += dr[curDir]; 
					curCol += dc[curDir];
					// 새로운 위치에서 탱크 갱신
					plain[curRow][curCol] = '^';
				}
				break;
			case 'D':
				// 아래로 회전 후 이동
				setDir('v');
				plain[curRow][curCol] = 'v';
				
				if(!isPossible(curRow + dr[curDir], curCol + dc[curDir])) continue;
				
				if(plain[curRow + dr[curDir]][curCol + dc[curDir]] == '.') {
					plain[curRow][curCol] = '.';
					
					curRow += dr[curDir]; 
					curCol += dc[curDir];
					plain[curRow][curCol] = 'v';
				}
				break;
			case 'L':
				// 왼쪽으로 회전 후 이동
				setDir('<');
				plain[curRow][curCol] = '<';
				
				if(!isPossible(curRow + dr[curDir], curCol + dc[curDir])) continue;
				
				if(plain[curRow + dr[curDir]][curCol + dc[curDir]] == '.') {
					plain[curRow][curCol] = '.';
					
					curRow += dr[curDir]; 
					curCol += dc[curDir];
					plain[curRow][curCol] = '<';
				}
				break;
			case 'R':
				// 오른쪽으로 회전 후 이동
				setDir('>');
				plain[curRow][curCol] = '>';
				
				if(!isPossible(curRow + dr[curDir], curCol + dc[curDir])) continue;
				
				if(plain[curRow + dr[curDir]][curCol + dc[curDir]] == '.') {
					plain[curRow][curCol] = '.';
					
					curRow += dr[curDir]; 
					curCol += dc[curDir];
					plain[curRow][curCol] = '>';
				}
				break;
			case 'S':
				// 바라보는 방향으로 포탄 발사
				int fireRow = curRow;
				int fireCol = curCol;
				
				while(true) {	// 포탄 발사 후 계속해서 이동
					int nr = fireRow + dr[curDir];	// 이동하고자 하는 위치
					int nc = fireCol + dc[curDir];
					
					if(!isPossible(nr, nc)) break;	// 맵 밖으로 나가면 정지
					
					if(plain[nr][nc] == '*') {	 // 벽돌벽 만나면 부수고 정지
						plain[nr][nc] = '.';
						break;	
					}
					
					if(plain[nr][nc] == '#') break;	// 강철벽 만나면 그냥 정지
					
					fireRow = nr;
					fireCol = nc;
				} // end of play S
				
				break;
			} // end of switch
		}
		
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
	
	public static void setDir(char ch) {
		if(ch == '^') curDir = 0;
		else if(ch == '>') curDir = 1;
		else if(ch == 'v') curDir = 2;
		else if(ch == '<') curDir = 3;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
