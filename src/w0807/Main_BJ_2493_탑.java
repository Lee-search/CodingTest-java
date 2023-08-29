package w0807;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_2493_탑 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = stoi(br.readLine());
		int[] topList = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			topList[i] = stoi(st.nextToken());
		}
		
		Stack<int[]> stack = new Stack<>();
		int[] answer = new int[N];
		
		for(int i = 0; i < N; i++) {
			int target = topList[i];	// 현재 높이
			
			while(!stack.isEmpty()) {
				// 받을 수 없는 경우
				if(stack.peek()[1] < target) {
					stack.pop();
				}
				// 받을 수 있는 경우
				else {
					answer[i] += stack.peek()[0] + 1;
					break;
				}
			}
			
			stack.add(new int[] {i, target});
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
//		for(int i = 0; i < stack.size(); i++) {
//			System.out.println(Arrays.toString(stack.get(i)));
//		} // => [1, 9] [3, 7] [4, 4]

		
	} // end of main
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	} // end of stoi
} // end of class
