import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] jusik;
    static long sumTot;

    /** initialize */
    public static void initial() throws Exception {
        N = Integer.parseInt(br.readLine());
        jusik = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            jusik[i] = Integer.parseInt(st.nextToken());
        }
        sumTot = 0;
    }

    public static void greedy() {
        int nowJuga;
        int maxVal = 0;
        for (int i = N - 1; i >= 0; i--) {
            nowJuga = jusik[i];
            if (maxVal < nowJuga)
                maxVal = Math.max(maxVal, nowJuga);
            else
                sumTot += (maxVal - nowJuga);
        }
    }

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            initial();
            greedy();
            sb.append(sumTot).append('\n');
        }
        System.out.println(sb);
    }
}
