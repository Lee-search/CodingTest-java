package w0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_16935 {
	
	public static int N, M, R;
	public static int[][] plain;
	public static int[][] templ;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		R = stoi(st.nextToken());
		
		plain = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				plain[i][j] = stoi(st.nextToken());
			}
		}
		
		// 명령어 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			
			switch(stoi(st.nextToken())) {
			case 1:		// 상하반전
				upDown();
				break;
			case 2:		// 좌우반전
				rightLeft();
				break;
			case 3:		// 우측회전
				rotateRgt();
				break;
			case 4:		// 좌측회전
				rotateLft();
				break;
			case 5:		// 부분배열 우측회전
				blockRgt();
				break;
			case 6:		// 부분배열 좌측회전
				blockLft();
				break;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(plain[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	} // end of main
	
	public static void upDown() {
		
		int[] tmp;
		// i: 위에서 아래로, j: 아래에서 위로
		for(int i = 0, j = N - 1; i < j; i++, j--) {
			tmp = plain[i].clone();
			for(int t = 0; t < M; t++) plain[i][t] = plain[j][t];
			for(int t = 0; t < M; t++) plain[j][t] = tmp[t];
		}
		
	} // end of func1
	
	public static void rightLeft() {
		
		int tmp;
		// 행 별로 하나씩 바꾸기
		for(int r = 0; r < N; r++) {
			// i: 좌 -> 우, j: 우 -> 좌
			for(int i = 0, j = M - 1; i < j; i++, j--) {
				tmp = plain[r][i];
				plain[r][i] = plain[r][j];
				plain[r][j] = tmp;
			}
		}
		
	} // end of func2
	
	public static void rotateRgt() {

		templ = new int[M][N];
		
		// 행별로 열에 저장
		for(int i = 0; i < N; i++) { // 행
			for(int j = 0; j < M; j++) { // 열
				templ[j][(N - 1) - i] = plain[i][j];
			}
		}
		
		plain = new int[M][N];
		for(int i = 0; i < M; i++) {
			plain[i] = templ[i].clone();
		}
		
		int tmp = N; N = M; M = tmp;
	} // end of func3
	
	public static void rotateLft() {
		
		templ = new int[M][N];
		
		// 행별로 열에 저장
		for(int i = 0; i < N; i++) { // 행
			for(int j = 0; j < M; j++) { // 열
				templ[(M - 1) - j][i] = plain[i][j];
			}
		}
		
		plain = new int[M][N];
		for(int i = 0; i < M; i++) {
			plain[i] = templ[i].clone();
		}
		
		int tmp = N; N = M; M = tmp;
	} // end of func4
	
	public static void blockRgt() {
		
		templ = new int[N][M];
		
		for(int r = 0; r < (N - 1)/2 + 1; r++) {
			for(int c = 0; c < (M - 1)/2 + 1; c++) {
				templ[r][c + (M - 1)/2 + 1] = plain[r][c];
			}
		} // 좌로 이동
		
		for(int r = 0; r < (N - 1)/2 + 1; r++) {
			for(int c = (M - 1)/2 + 1; c < M; c++) {
				templ[r + (N - 1)/2 + 1][c] = plain[r][c];
			}
		} // 아래 이동
		
		for(int r = (N - 1)/2 + 1; r < N; r++) {
			for(int c = (M - 1)/2 + 1; c < M; c++) {
				templ[r][c - (M - 1)/2 - 1] = plain[r][c];
			}
		} // 우로 이동
		
		for(int r = (N - 1)/2 + 1; r < N; r++) {
			for(int c = 0; c < (M - 1)/2 + 1; c++) {
				templ[r - (N - 1)/2 - 1][c] = plain[r][c];
			}
		} // 위로 이동
		
		for(int i = 0; i < N; i++) {
			plain[i] = templ[i].clone();
		}
		
	} // end of func5
	
	public static void blockLft() {
		
		templ = new int[N][M];
		
		for(int r = 0; r < (N - 1)/2 + 1; r++) {
			for(int c = 0; c < (M - 1)/2 + 1; c++) {
				templ[r + (N - 1)/2 + 1][c] = plain[r][c];
			}
		} // 아래로
		
		for(int r = (N - 1)/2 + 1; r < N; r++) {
			for(int c = 0; c < (M - 1)/2 + 1; c++) {
				templ[r][c + (M - 1)/2 + 1] = plain[r][c];
			}
		} // 우로
		
		for(int r = (N - 1)/2 + 1; r < N; r++) {
			for(int c = (M - 1)/2 + 1; c < M; c++) {
				templ[r - (N - 1)/2 - 1][c] = plain[r][c];
			}
		} // 위로
		
		for(int r = 0; r < (N - 1)/2 + 1; r++) {
			for(int c = (M - 1)/2 + 1; c < M; c++) {
				templ[r][c - (M - 1)/2 - 1] = plain[r][c];
			}
		} // 좌로
		
		for(int i = 0; i < N; i++) {
			plain[i] = templ[i].clone();
		}
		
	} // end of func6
	
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
	
} // end of class
