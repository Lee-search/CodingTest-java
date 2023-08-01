import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	public static int[][] plain;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream(new File("./src/w0801/input_3.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc < t + 1; tc++) {
			int n = Integer.parseInt(br.readLine());
			plain = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				int j = 0;
				for(char c : br.readLine().toCharArray()) {
					plain[i][j++] = c - '0';
				}
			}
			
			int middle = n / 2;
			int tmp = -1;
			int answer = 0;
			
			for(int i = 0; i < n; i++) {

				if(i <= middle) tmp += 1;
				else tmp -= 1;
				
//				System.out.println("tmp: " + tmp);
				for(int j = middle - tmp; j <= middle + tmp; j++) {
//					System.out.print(plain[i][j] + " ");
					answer += plain[i][j];
				}
			}
			System.out.println("#" + tc + " " + answer);
		}
	}
}
