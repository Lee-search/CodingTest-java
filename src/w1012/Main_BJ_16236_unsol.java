package w1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_16236_unsol {

    static class Shark {
        int r, c, size, cnt;
        public Shark(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
            cnt = 0;
        }
        public void eat() {
            this.cnt += 1;
            // 사이즈만큼 생선을 먹은 경우 초기화 실행
            if(cnt == size) {
                size += 1;
                cnt = 0;
            }
        }
        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + size + ", " + cnt + ")";
        }
    } // end of class

    static class Fish implements Comparable<Fish> {
        int r, c, size;
        public Fish(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }
        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.size, o.size);
        }
        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + size + ")";
        }
    } // end of class

    static int N, count;
    static int[][] plain;
    static Shark shark;
    static PriorityQueue<Fish> pq;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = stoi(st.nextToken());
                if(n != 0) {
                    if(n == 9)  {   // 상어 정보 초기화
                        shark = new Shark(i, j, 2);
                    }
                    else {       // 생선 정보 초기화 후 우선순위큐 삽입
                        pq.add(new Fish(i, j, n));
                        plain[i][j] = n;
                    }
                }
            }
        } // end of init
//        print();
        find();
    } // end of main
    
    // 1. 상어가 먹을 수 있는 생선 찾기
    public static void find() {

        while(!pq.isEmpty()) {

            Fish fish = pq.poll();
            // 더 이상 먹을 수 있는 생선이 없는 경우
            if(shark.size <= fish.size) {
                System.out.println(count);
                return;
            }
            BFS();
        }
    } // end of func

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    // 2.상어보다 작은 생선이 있는 경우 BFS 탐색 후 생선 먹기
    public static void BFS() {

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new int[] {shark.r, shark.c});
        visited[shark.r][shark.c] = true;

        while(!q.isEmpty()) {

            int[] info = q.poll();
            int r = info[0], c = info[1];

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isPossible(nr, nc) && !visited[nr][nc]) {
                    // 상어보다 큰 생선이면 못지나감
                    if(plain[nr][nc] > shark.size) continue;
                    // 먹을 수 있는 생선이면 먹음
                    if(plain[nr][nc] < shark.size) {
                        shark.eat();
                    }

                }
            }
        }

    }

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    } // end of func

    public static Fish compareDist(Fish o1, Fish o2) {
        int dist1 = Math.abs(o1.r - shark.r) + Math.abs(o1.c - shark.c);
        int dist2 = Math.abs(o2.r - shark.r) + Math.abs(o2.c - shark.c);
        // 0 보다 크면 dist2가 작으면 dist1가 더 가까움
        return Integer.compare(dist1, dist2) > 0 ? o2 : o1;
    }

    public static void print() {
        System.out.println("공간 정보 출력");
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(plain[i]));
        }
        System.out.println("상어 정보 출력");
        System.out.println(shark);
        System.out.println("생선 정보 출력");
        // PQ 구조적 특성상 size가 정렬되어 출력되지는 않는다
        for (Fish fish : pq) {
            System.out.println(fish);
        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
