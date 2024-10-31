import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());   // 트럭의 수
        int w = stoi(st.nextToken());   // 다리 길이
        int L = stoi(st.nextToken());   // 최대 하중

        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = stoi(st.nextToken());
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        int current = 0;        // 현재 고려중인 트럭 번호
        int time = 1;           // 단위 시간
        int total = trucks[0];  // 다리위에 있는 트럭 무게 총합
        q.offer(new int[] {trucks[current], w - 1});

        while (!q.isEmpty()) {

            time += 1;
            int k = q.size();   // 현재 다리위의 트럭 수

            for(int i = 0; i < k; i++) {
                // 다리위에서 이동
                int weight = q.peek()[0];   // 트럭의 무게
                int position = q.poll()[1]; // 트럭의 위치

                // 내릴 수 있으면 내리기
                if(position == 0) {
                    total -= weight;
                }
                else {
                    q.offer(new int[] {weight, position - 1});
                }
            }

            // 탑승가능한지 확인 후 탑승
            if(current + 1 < n && total + trucks[current + 1] <= L) {
                q.offer(new int[] {trucks[current + 1], w - 1});
                total += trucks[current + 1];
                current += 1;
            }
        }

        System.out.println(time);
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
