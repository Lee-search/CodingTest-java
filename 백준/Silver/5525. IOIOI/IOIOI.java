import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static char[] ioi;
    static int cnt = 0;

    /** initialize */
    public static void initial() throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        ioi = br.readLine().toCharArray();
    }

    /** idx부터 가능한지 확인 */
    // I가 N+1번
    public static int check(int idx) {
        for (int i = 0; i < 2 * N - 1; i += 2) {
            if (ioi[idx + i] != 'I')
                return idx + i + 1;

            if (ioi[idx + i + 1] != 'O')
                return idx + i + 1;
        }
        if (ioi[idx + 2 * N] != 'I')
            return idx + 2 * N + 1;
        cnt++;
        return idx + 2;
    }

    public static void main(String[] args) throws Exception {
        initial();
        int idx = 0;
        while (idx < M - 2 * N) {
            idx = check(idx);
        }
        System.out.println(cnt);
    }
}
