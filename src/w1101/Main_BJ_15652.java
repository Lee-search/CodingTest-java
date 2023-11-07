package w1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * N과 M(4) : https://www.acmicpc.net/problem/15652
 * 중복 순열, 기존에 선택한 수를 매개변수로 전달하여 거기서부터 시작하도록 함
 */

public class Main_BJ_15652 {

    static int N, M;
    static int[] selector;
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
