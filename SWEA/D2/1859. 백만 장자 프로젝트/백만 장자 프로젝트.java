import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int tc = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= tc; testCase++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			long sum = 0;	// 1_000_000 * 1_000_000
			int max = 0;
			for(int i = n - 1; i >= 0; i--) {
				if(arr[i] > max) {
					max = arr[i];
					continue;
				}
				sum += max - arr[i];
			}
			
			sb.append("#").append(testCase).append(" ").append(sum).append("\n");
		} // end of tc
		System.out.println(sb);
	} // end of main 
} // end of class