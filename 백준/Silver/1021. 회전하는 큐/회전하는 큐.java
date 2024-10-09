import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            deque.add(i + 1);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {

            int n = Integer.parseInt(st.nextToken());   // 찾고자 하는 수
//            System.out.println("찾는 수: " + n);

            // 양쪽 끝에서부터 리스트로 접근
            int left = 0, right = deque.size() - 1;
            int check = 0;  // 확인한 깊이

            while(left <= right) {

                int nLeft = deque.get(left++);
                int nRight = deque.get(right--);

                if(nLeft == n) {
                    // 2번연산
                    for(int j = 0; j < check; j++) {
                        int m = deque.pollFirst();
                        deque.addLast(m);
                    }
                    answer += check;
                    break;
                }
                else if(nRight == n) {
                    // 3번 연산
                    for(int j = 0; j < check + 1; j++) {
                        int m = deque.pollLast();
                        deque.addFirst(m);
                    }
                    answer += (check + 1);
                    break;
                }
                check++;
            }
            // 찾고자 하는 수가 있다면, 왼쪽 끝이 n이 될때까지 연산 수행
//            System.out.println("deque 상태: " + deque + ", check: " + check);
            deque.pollFirst();
        }

        System.out.println(answer);
    }
}
