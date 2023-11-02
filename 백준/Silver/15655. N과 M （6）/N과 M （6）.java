
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

public class Main {

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
                sb.append(selector[i] + " ");
            }
            sb.append("\n");
            return;
        } // basis

        for (int i = start; i < N; i++) {

            if(isSelected[i]) continue;

            isSelected[i] = true;
            selector[cnt] = numbers[i];
            permutations(i + 1, cnt + 1);
            isSelected[i] = false;
        } // end of foc
    } // end of func
}
