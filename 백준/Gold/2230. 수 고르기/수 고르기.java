
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static int[] nArray;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        answer = Integer.MAX_VALUE;
        nArray = new int[N];
        for (int i = 0; i < N; i++) {
            nArray[i] = stoi(br.readLine());
        }

        Arrays.sort(nArray);

        // 주의, 같은 수일 수도 있음
        for (int left = 0; left < N; left++) {
            for (int right = left; right < N; right++) {
                int n = nArray[right] - nArray[left];
                if (n >= M) {
                    answer = Math.min(answer, n);
                    break;
                }
            }
        }

        System.out.println(answer);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
