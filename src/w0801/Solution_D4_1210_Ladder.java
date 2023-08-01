package w0801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1210_Ladder {
	
	public static int[][] plain;
	public static boolean[][] visited;
	public static boolean isAnswer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./src/w0801/input_2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int tc = 0;
		while(tc < 10) {
			tc = Integer.parseInt(br.readLine());
			
			plain = new int[100][100];
			isAnswer = false;
			
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < 100; j++) {
					plain[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int c = 0; c < 100; c++) {
				visited = new boolean[100][100];
				move(0, c);
				if(isAnswer) {
					System.out.println("#" + tc + " " + ++c);
					break;
				}
			}
		}
	}

	
	public static void move(int r, int c) {
		visited[r][c] = true;
		
		if(plain[r][c] == 2) {
			isAnswer = true;
			return;
		}
		
		if(isPossible(r, c - 1) && plain[r][c - 1] != 0) {
//			System.out.println(r + " " + c);
			move(r, c - 1);
			
		}
		else if(isPossible(r, c + 1) && plain[r][c + 1] != 0) {
//			System.out.println(r + " " + c);
			move(r, c + 1);
			
		}
		else if(isPossible(r + 1, c) && plain[r + 1][c] != 0){
//			System.out.println(r + " " + c);
			move(r + 1, c);
			
		}
	}
	
	public static boolean isPossible(int r, int c) {
		return (0 <= r && r < 100 && 0 <= c && c < 100) && visited[r][c] == false;
	}
}
