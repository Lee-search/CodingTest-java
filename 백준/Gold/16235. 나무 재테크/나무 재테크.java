import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] A, plain;
    static class Tree implements Comparable<Tree>{

        int r, c, age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree other) {
            // 나이 순으로 정렬
            return this.age - other.age;
        }

        @Override
        public String toString() {
            return "좌표: (" + r + ", " + c + "), 나이: " + age;
        }
    }

    static PriorityQueue<Tree> pq;
    static Queue<Tree> summerQ, autumnQ;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());   // 지도 크기
        M = stoi(st.nextToken());   // 나무 갯수
        K = stoi(st.nextToken());   // K 년

        A = new int[N + 1][N + 1];  // 추가되는 양분
        plain = new int[N + 1][N + 1];  // 땅에 남아있는 양분

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = stoi(st.nextToken());
                plain[i][j] = 5;    // 양분
            }
        }

        pq = new PriorityQueue<>(); // 나무 정보 큐
        summerQ = new ArrayDeque<>();
        autumnQ = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            int z = stoi(st.nextToken());

            pq.offer(new Tree(x, y, z));
        }

        for (int i = 0; i < K; i++) {
            simulation();
        }

        System.out.println(pq.size());
    } // end of main

    static int[][] dir = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };
    static void simulation() {

        // pq에 대해 복사 붙혀넣기 할때 시간초과 날 수 있음, 가능한 반복문 한번에 로직 처리
        // 봄: 자신의 나이만큼 양분 흡수 가능하면 먹고 나이 증가, 아니면 사망
        while (!pq.isEmpty()) {

            Tree t = pq.poll();
            if(plain[t.r][t.c] >= t.age) {
                plain[t.r][t.c] -= t.age;
                t.age += 1;
                autumnQ.offer(t);
            }
            else {
                summerQ.offer(t);
            }
        }

        // 여름: 죽은 나무 -> 양분 (죽은 나무의 나이 / 2 만큼, 소숫점 배제)
        while (!summerQ.isEmpty()) {
            Tree t = summerQ.poll();
            plain[t.r][t.c] += Math.floor((double) t.age / 2);
        }

        // 가을: 나이가 5의 배수인 나무에 대해, 인접 8칸으로 번식
        while (!autumnQ.isEmpty()) {
            Tree t = autumnQ.poll();
            if(t.age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int nr = t.r + dir[j][0];
                    int nc = t.c + dir[j][1];
                    if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
                    pq.offer(new Tree(nr, nc, 1));
                }
            }
            pq.offer(t);
        }

        // 겨울: 땅에 양분 추가
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                plain[i][j] += A[i][j];
            }
        }
    } // end of simulation

//    static void print() {
//
//        System.out.println("---현재 살아있는 나무 리스트---");
//        PriorityQueue<Tree> copy = new PriorityQueue<>(pq);
//        while (!copy.isEmpty()) {
//            System.out.println(copy.poll());
//        }
//        System.out.println("---땅의 양분 상태---");
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= N; j++) {
//                System.out.print(plain[i][j] + ", ");
//            }
//            System.out.println();
//        }
//    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
