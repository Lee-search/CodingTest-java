package w1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Main_BJ_15663 {

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

        permutation(0);

        System.out.println(sb.toString());
    } // end of main

    public static void permutation(int cnt) {

        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selector[i]).append(" ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = 0; i < N; i++) {

            if(isSelected[i]) continue;

            isSelected[i] = true;
            selector[cnt] = numbers[i];
            permutation(cnt + 1);
            isSelected[i] = false;
        } // end of for
    } // end of func
}
