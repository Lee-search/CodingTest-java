package w0802;

import java.util.Scanner;

public class Main_BJ_15650_N과M {
	
	public static int n, m;
	public static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[m];
		
		comb(0, 1);
		sc.close();
	}
	
	public static void comb(int count, int start) {
		
		if(count == m) {
			for(int a : arr) {
				System.out.print(a + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = start; i <= n; i++) {
			arr[count] = i;
			comb(count + 1, i + 1);
		}
	}
}
