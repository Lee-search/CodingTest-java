import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int[] switchs;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스위치 수
		int n = Integer.parseInt(br.readLine());
		
		switchs = new int[n];
		String[] tmp = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			switchs[i] = Integer.parseInt(tmp[i]);
		}
		
		// 학생 수
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			tmp = br.readLine().split(" ");
			int gen = Integer.parseInt(tmp[0]);
			int pos = Integer.parseInt(tmp[1]);
			
			if(gen == 1) man(pos);
			else woman(pos);
		}
		
		// 한 줄에 20개씩 출력
		for(int i = 0; i < n; i++) {
			System.out.print(switchs[i] + " ");
			if((i + 1) % 20 == 0) System.out.println();
		}

	}
	// 남학생, 
	public static void man(int pos) {
		for(int i = 1; i < switchs.length + 1; i++) {
			if(i % pos == 0) {
				if(switchs[i - 1] == 0) switchs[i - 1] = 1;
				else switchs[i - 1] = 0;
			}
		}
	}
	// 여학생,
	public static void woman(int pos) {
		int left = pos - 1, right = pos - 1;
		
		while(true) {
			if(left - 1 < 0 || right + 1 >= switchs.length) break;
			
			if(switchs[left - 1] == switchs[right + 1]) {
				left -= 1;
				right += 1;
			}
			else break;
		}
		
		for(int i = left; i <= right; i++) {
			
			if(switchs[i] == 0) switchs[i] = 1;
			else switchs[i] = 0;
		}
		
	}
}
