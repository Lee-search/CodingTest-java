import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	public static int[] boxies;	// 상자 높이 배열
	public static int count;	// 덤프 가능 홧수
	public static int answer;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/w0801/input_3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
 			count = Integer.parseInt(br.readLine());
			boxies = new int[100];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 100; i++) {
				boxies[i] = Integer.parseInt(st.nextToken());
			}
			
			dump();
			System.out.println("#" + tc + " " + answer);
		}
	}
	
	public static void dump() {
		Arrays.sort(boxies);
		int max = boxies[99], min = boxies[0];
		
		if(count == 0 || max - min == 0 || max - min == 1) {
			answer = max - min;
			return;
		}
		
		boxies[99] -= 1;
		boxies[0] += 1;
		count -= 1;
		dump();
	}
}
