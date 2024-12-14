import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr, counter;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        arr = new int[N];
        counter = new int[100_001]; // 100_000 이하의 양의 정수에 대해 카운트

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int lp = 0, rp = 0;    // 투 포인터
        int answer = 0;

        while(rp < N) {

            int left = arr[lp], right = arr[rp];
            if(counter[right] < K) {
                counter[right] += 1;
                rp += 1;
            }
            // K를 넘어가면, K와 같아질때까지 lp 이동
            else {
                counter[left] -= 1;
                lp += 1;
            }

            answer = Math.max(answer, rp - lp);
        }

        System.out.println(answer);
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}