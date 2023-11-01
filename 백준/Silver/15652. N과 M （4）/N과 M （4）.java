
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {

    static int N, M;
    static int[] selector;
    static boolean[] isSelected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        selector = new int[M];
        permutation(1, 0);

        System.out.print(sb.toString());

    } // end of main

    public static void permutation(int start, int cnt) {

        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selector[i] + " ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = start; i <= N; i++) {

            selector[cnt] = i;
            permutation(i, cnt + 1);
        }
    } // end of func
}
