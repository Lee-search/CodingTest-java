import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> live = new ArrayDeque<>();
		Deque<Integer> dead = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) live.offerLast(i);
		
		int count = 1;
		while(!live.isEmpty()) {
			
			int target = live.pollFirst();
			if(count % M == 0) {
				dead.offerLast(target);
			}
			else {
				live.offerLast(target);
			}
			count += 1;
		}
		
		StringBuilder sb = new StringBuilder().append(dead);
		sb.replace(0, 1, "<");
		sb.replace(sb.length() - 1, sb.length(), ">");
		
		System.out.println(sb);
	}
}
