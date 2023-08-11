import java.util.Scanner;

public class Main {
	
	static int N, count;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		while(N > 0) {
//			System.out.println(N);
			if(N % 5 == 0) {
				N -= 5;
			}
			else { // 5로 안나눠짐
				N -= 3;
			}
			count += 1;
		}
		
		if(N != 0) count = -1;
		System.out.println(count);
	} // end of main
}
