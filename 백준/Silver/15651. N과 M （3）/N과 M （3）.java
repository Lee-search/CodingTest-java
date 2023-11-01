
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main {

    static int N, M;
    static int[] selected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        selected = new int[M];
        selector(0);  // 0번째 수부터 M개 선택

        System.out.print(sb.toString());

    } // end of main

    public static void selector(int cnt) {

        if(cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i] + " ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = 1; i <= N; i++) {

            selected[cnt] = i;
            selector(cnt + 1);
        }
    }
}
