package others.baekjoon.data_structure;

/**
 * 후위 표기식2 : https://www.acmicpc.net/problem/1935
 * 스택 자료구조 이해, 같은 피연산자가 나올 수 있음에 유의
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_BJ_1935 {
	
	public static int N;
	public static double[] arr;
	public static Stack<Double> stack;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		stack = new Stack<>();
		
		char[] expr = br.readLine().toCharArray();
		
		arr = new double[N];
		for(int i = 0; i < N; i++) {
			arr[i] = stod(br.readLine());
		}	// 피연산자를 배열에 저장
		
		for(char ch : expr) {
			
			double tmp;
			
			if('A' <= ch && ch <= 'Z') {	// 피연산자이면
				stack.push(arr[ch - 'A']);  // 큐에 삽입
			}
			else if(ch == '+'){	// 연산자이면 계산
				stack.push(stack.pop() + stack.pop());
			}
			else if(ch == '-') {
				tmp = stack.pop();
				stack.push(stack.pop() - tmp);
			}
			else if(ch == '*') {
				stack.push(stack.pop() * stack.pop());
			}
			else if(ch == '/') {
				tmp = stack.pop();
				stack.push(stack.pop() / tmp);
			}
		}
		System.out.println(String.format("%.2f", stack.peek()));
		
	} // end of main
	
	public static double stod(String s) {
		return (double)Integer.parseInt(s);
	}
} // end of class
