package w1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * N과 M(7) : https://www.acmicpc.net/problem/15656
 * 순열, 백트래킹
 */

public class Main_BJ_15656 {

    static int N, M;
    static int[] numbers;
    static int[] selector;
    static boolean[] isSelected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        selector = new int[M];
        isSelected = new boolean[N];
        permutations(0, 0);

        System.out.println(sb.toString());
    } // end of main

    public static void permutations(int start, int cnt) {

        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selector[i]).append(" ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = 0; i < N; i++) {

            selector[cnt] = numbers[i];
            permutations(i, cnt + 1);
        } // end of foc
    } // end of func
}
