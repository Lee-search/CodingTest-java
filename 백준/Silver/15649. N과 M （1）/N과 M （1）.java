import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static boolean[] visited;
	public static int n, m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		visited = new boolean[n + 1];
		pick(m, new ArrayList<Integer>());
	}
	
	public static void pick(int m, List<Integer> arr) {
		if(m == 0) {
//			System.out.println(arr.toString());
			
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

