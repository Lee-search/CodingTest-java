package w0817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5644_무선충전_unsol {
	
	public static int M, A; 				// 총 이동 시간, BC의 개수
	public static int answer;				// 충천량의 총 합
	public static int[] mvInfoA, mvInfoB;	// A, B의 이동 정보
	public static int[][][] plain;			// BC가 위치한 공간 배열
	
	public static int[] chargeInfoA, chargeInfoB;	// 충천이 이뤄지는지 확인을 위한 임시 배열
	public static void main(String[] args) throws Exception {
		
		//System.setIn(new FileInputStream(new File("./src/w0817/input_1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = stoi(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken());	// 총 이동 시간
			A = stoi(st.nextToken());	// BC 개수
			
			plain = new int[11][11][A];	// (1,1) 부터 (10,10), 중복되는 BC 고려
			mvInfoA = new int[M];
			mvInfoB = new int[M];
			
			chargeInfoA = new int[M + 1];
			chargeInfoB = new int[M + 1];
			
			st = new StringTokenizer(br.readLine());	// A의 이동 정보
			for(int i = 0; i < M; i++) mvInfoA[i] = stoi(st.nextToken());
			
			st = new StringTokenizer(br.readLine());	// B의 이동 정보
			for(int i = 0; i < M; i++) mvInfoB[i] = stoi(st.nextToken());
			
			for(int i = 0; i < A; i++) {	// BC 입력
				st = new StringTokenizer(br.readLine());
				
				int y = stoi(st.nextToken());	// Y좌표
				int x = stoi(st.nextToken());	// X좌표
				int C = stoi(st.nextToken());	// 충전 범위
				int P = stoi(st.nextToken());	// 처리량
				
				int size = 0, cnt = 0;	// 커지는 크기, 라인 갯수
				for(int r = x - C; r <= x + C; r++) {
					for(int c = y - size; c <= y + size; c++) {
						if(isPossible(r, c)) plain[r][c][i] = P;	// 범위 바깥 예외처리
					}
					
					if(cnt < C) size += 1;
					else size -= 1;
					cnt += 1;
				}
			} // end of init
			
//			for(int i = 1; i <= 10; i++) {
//				for(int j = 1; j <= 10; j++) {
//					System.out.print(Arrays.toString(BC[i][j]) + "\t");
//				}
//				System.out.println();
//			} // 입력 받은 배열 출력코드
			
			userTest();	// 사용자 M번 이동 시작
			
			
			
			
		} // end of tc
	
	} // end of main
	
	public static void charge(int ar, int ac, int br, int bc, int idx) {
		
		if(ar == br && ac == bc) { 
			// 사용자 A, B 같은 위치 -> 
			chargeInfoA[idx] = Arrays.stream(plain[ar][br]).max().getAsInt() / 2;
			chargeInfoB[idx] = Arrays.stream(plain[ar][br]).max().getAsInt() / 2;
		}
		
		
		
		System.out.println(Arrays.toString(chargeInfoA));
		System.out.println(Arrays.toString(chargeInfoB));
	}
	
	public static int[][] dir = {{0, 0}, {-1,0}, {0, 1}, {1, 0}, {0, -1}};	// 이동 벡터
	public static void userTest() {
		
		int ar = 1, ac = 1; 	// a의 r, c값
		int br = 10, bc = 10;	// b의 r, c값
		for(int i = 0; i <= M; i++) {
			
			// 1. 사용자 시작 자리에서 충전 가능한지 확인, 가능하면 충전
			charge(ar, ac, br, bc, i);
			
			// 2. 다음 자리로 이동
			if(i == M) continue;	// 마지막 단계에서는 이동 생략
			
			int ad = mvInfoA[i];
			ar = ar + dir[ad][0];
			ac = ac + dir[ad][1];
			
			int bd = mvInfoB[i];
			br = br + dir[bd][0];
			bc = bc + dir[bd][1];
		}
		
	} // end of func
	
	public static boolean isPossible(int r, int c) {
		return 0 < r && r <= 10 && 0 < c && c <=10;
	} // end of func
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
