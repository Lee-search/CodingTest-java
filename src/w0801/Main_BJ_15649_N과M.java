package w0801;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_BJ_15649_N과M {
	public static boolean[] visited;
	public static int n, m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		visited = new boolean[n + 1];
		pick(m, new ArrayList<Integer>());
		sc.close();
	}
	
	public static void pick(int m, List<Integer> arr) {
		if(m == 0) {
			for(int i : arr) System.out.print(i + " ");
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(visited[i] == true) continue;
			
			visited[i] = true;
			arr.add(i);
			pick(m - 1, arr);
			arr.remove(arr.size() - 1);
			visited[i] = false;
		}
	}
}

//public class Main {
//	public static boolean[] visited;
//	public static int n, m, t;
//	public static int[] arr;
//	
//	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
//		
//		n = sc.nextInt();
//		m = sc.nextInt();
//		t = m;
//		
//		visited = new boolean[n + 1];
//		arr = new int[n];
//		pick(m, 0);
//	}
//	
//	public static void pick(int m, int pos) {
//		if(m == 0) {
//			for(int j = 0; j < t; j++) {
//				System.out.print(arr[j] + " ");
//			}
//			System.out.println();
//			return;
//		}
//		
//		for(int i = 1; i <= n; i++) {
//			if(visited[i] == true) continue;
//			
//			visited[i] = true;
//			arr[pos] = i;
//			pick(m - 1, pos + 1);
//			visited[i] = false;
//		}
//	}
//}
