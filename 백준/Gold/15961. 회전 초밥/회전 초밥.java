import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, d, k, c, count, answer;
	public static int[] sushi;
	public static int[] picker;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());	// 접시의 수
		d = stoi(st.nextToken());	// 초밥의 가짓수
		k = stoi(st.nextToken());	// 연속해서 먹는 수
		c = stoi(st.nextToken());	// 쿠폰번호
		
		// 투포인터를위해 배열 뒤에 배열 복사
		sushi = new int[N * 2];
		for(int i = 0; i < N; i++) {
			sushi[i] = stoi(br.readLine());
		}
		for(int i = N; i < N * 2; i++) {
			sushi[i] = sushi[i - N];
		}

		picker = new int[d + 1];	// 초밥은 1번부터 시작
		count = 0;	// 먹은 초밥의 가짓수
		for(int i = 0; i < k; i++) {
			if(picker[sushi[i]] == 0) count += 1;
			picker[sushi[i]] += 1;
		}
		
		// 쿠폰으로 주는 초밥 먹었는지 확인
		answer = picker[c] == 0 ? count + 1 : count;

		//System.out.println(Arrays.toString(picker));
		//System.out.println(count);

		for(int start = 1; start < N; start++) {
			int end = start + k - 1;	// 마지막으로 먹는 초밥
			
			// 뒤에서 스시 빼기
			picker[sushi[start - 1]] -= 1;
			if(picker[sushi[start - 1]] == 0) count -= 1;

			// 앞에서 스시 넣기
			if(picker[sushi[end]] == 0) count += 1;
			picker[sushi[end]] += 1;

			// C를 안먹었으면 쿠폰으로 주기
			answer = Math.max(answer, picker[c] == 0 ? count + 1 : count);

		}

		System.out.println(answer);
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
}
