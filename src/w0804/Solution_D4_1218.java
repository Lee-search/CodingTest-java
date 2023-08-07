package w0804;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_1218 {

	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream(new File("./src/w0804/input_1.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int len = Integer.parseInt(br.readLine());
			Stack<Character> stack = new Stack<>();
			int answer = 1;
			
			String line = br.readLine();
			for(int i = 0; i < len; i++) {
				char ch = line.charAt(i);
				
				if(ch == ')' || ch == ']' || ch == '}' || ch == '>') {
					if(stack.isEmpty()) {
						answer = 0;	// 괄호 합이 맞지 않음 -> 0
						break;
					}
					
					if(ch == ')' && stack.peek() == '(') stack.pop();
					else if(ch == ']' && stack.peek() == '[') stack.pop();
					else if(ch == '}' && stack.peek() == '{') stack.pop();
					else if(ch == '>' && stack.peek() == '<') stack.pop();
					else {
						answer = 0;
						break;
					}
				} else stack.push(ch);
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		} // end of tc
		
		System.out.println(sb);
	} // end of main
} // end of class
