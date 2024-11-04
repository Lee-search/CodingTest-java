import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] A;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = stoi(st.nextToken());
        Arrays.sort(A); // 이진탐색 하려면 정렬해야함

        M = stoi(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = stoi(st.nextToken());
            sb.append(find(target)).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int find(int target) {

        int st = 0, en = A.length - 1;
        while(st <= en) {
            // mid 구하기
            int mid = (st + en) / 2;

            // 찾고자 하는 수가 mid가 가리키는 수인 경우
            if(target == A[mid]) return 1;
            // mid보다 작은 경우
            else if(target < A[mid]) en = mid - 1;
            // mid보다 큰 경우
            else st = mid + 1;
        }

        return 0;
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
