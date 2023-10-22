
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

        int left = 0, right = 0;
        while(left <= right && right < N) {
            int n = nArray[right] - nArray[left];
            if(n >= M) {
                answer = Math.min(answer, n);
                left += 1;
            }
            else right += 1;
        }

        System.out.println(answer);
    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
