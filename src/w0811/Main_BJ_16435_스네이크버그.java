package w0811;

import java.util.Arrays;
import java.util.Scanner;

public class Main_BJ_16435_스네이크버그 {
	
	static int N, L;
	static int[] fruit;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		fruit = new int[N];
		
		for(int i = 0; i < N; i++) {
			fruit[i] = sc.nextInt();
		}
		
		Arrays.sort(fruit);
		
		for(int i = 0; i < N; i++) {
			if(fruit[i] > L) break;
			
			L += 1;
		}
		
		System.out.println(L);
	} // end of main
}
