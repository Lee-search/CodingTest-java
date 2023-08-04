import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	static int[] opers = new int[4];
	static int target;
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) arr[i] = stoi(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) opers[i] = stoi(st.nextToken());
		
		target = arr[0]; // 첫번째 수는 무조건 들어감
		dfs(1);
		System.out.println(max + "\n" + min);
		
	} // end of main
	
	public static void dfs(int cnt) {
		
		if(cnt == N) {
			max = Math.max(max, target);
			min = Math.min(min, target);
			return;
		}
		
		// +, -, *, / 중에 하나 선택
		for(int i = 0; i < 4; i++) {
			if(opers[i] == 0) continue; // 연산자 쓸수 없는 경우
			
			switch(i) {
			case 0:	// +
				target += arr[cnt];
				opers[i] -= 1; // 사용했다고 알림
				dfs(cnt + 1);
				target -= arr[cnt];
				opers[i] += 1; // 초기화
				break;
			case 1:	// -
				target -= arr[cnt];
				opers[i] -= 1; // 사용했다고 알림
				dfs(cnt + 1);
				target += arr[cnt];
				opers[i] += 1; // 초기화
				break;
			case 2:
				target *= arr[cnt];
				opers[i] -= 1;
				dfs(cnt + 1);
				target /= arr[cnt];
				opers[i] += 1; // 초기화
				break;
			case 3:
				int tmp = target;
				target /= arr[cnt];
				opers[i] -= 1;
				dfs(cnt + 1);
				target = tmp;
				opers[i] += 1; // 초기화
				break;
			} // end of switch
			
		} // end of for
	} // end of method
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
