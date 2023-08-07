package w0804;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main_BJ_2164 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Deque<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) q.addLast(i);	// 초기화
		while(q.size() != 1) {
			q.pollFirst();
			q.addLast(q.pollFirst());
		}
		
		System.out.println(q.pollLast());
		sc.close();
	} // end of main
} // end of class
