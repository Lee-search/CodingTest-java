package w1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * N과 M(5) https://www.acmicpc.net/problem/15654
 * 순열, 백트래킹 통해 선택된 수가 두번 이상 중복되는 것 방지
 */

public class Main_BJ_15654 {

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
        selector = new int[M];
        isSelected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = parseInt(st.nextToken());
        } // end of init

        Arrays.sort(numbers);
        permutations(0, 0);
        System.out.print(sb.toString());

    } // end of main

    public static void permutations(int start, int cnt) {

        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selector[i] + " ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = 0; i < N; i++) {

            if(isSelected[i]) continue;    // 중복 순열 방지

            selector[cnt] = numbers[i];
            isSelected[i] = true;
            permutations(i + 1, cnt + 1);
            isSelected[i] = false;
        }
    } // end of func
}
