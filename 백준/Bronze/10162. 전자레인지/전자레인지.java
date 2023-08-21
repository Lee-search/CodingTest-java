import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 각각 지정된 시간
	static int A = 300;
	static int B = 60;
	static int C = 10;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		// 각각 누르는 횟수
		int Acnt, Bcnt, Ccnt;

		// 그리디, 큰거부터 세고 빼고 나누고
		Acnt = T / A;
		T -= Acnt * A;
		Bcnt = T / B;
		T -= Bcnt * B;
		Ccnt = T / C;
		T -= Ccnt * C;

		// T가 0이어야 함
		if (T == 0) {
			System.out.printf("%d %d %d", Acnt, Bcnt, Ccnt);
		}
		else {
			System.out.println(-1);
		}
	}
}
