import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int n, m;
	public static int[] sums;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		OutputStreamWriter osr = new OutputStreamWriter(System.out);
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sums = new int[n + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= n; i++) {
			sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
		}
		
//		System.out.println(Arrays.toString(sums));
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
//			System.out.println(sums[end] - sums[start - 1]);
			osr.write(sums[end] - sums[start - 1] + "\n");
		}
		osr.flush();
	} // end of main
}