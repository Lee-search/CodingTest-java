import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int L, C;
	static char[] alpha;

	public static void permutation(int length, int lastIdx, char[] per, int moeum, int jaeum) {
		if (length == L) {
			if (moeum < 1 || jaeum < 2)
				return;
			for (char p : per) {
				sb.append(p);
			}
			sb.append("\n");
			return;
		}
		char c;
		for (int i = lastIdx; i < C; i++) {
			c = alpha[i];
			per[length] = c;
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
				permutation(length + 1, i + 1, per, moeum + 1, jaeum);
			else
				permutation(length + 1, i + 1, per, moeum, jaeum + 1);
			
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		alpha = new char[C];
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(alpha);
		permutation(0, 0, new char[L], 0, 0);
		System.out.println(sb);
	}
}
