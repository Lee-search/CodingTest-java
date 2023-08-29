package w0824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_17143_낚시왕 {

    static int R, C, M, answer;
    static int[][] plain, nextPlain;
    static List<Shark> sharkList;

    static class Shark {

        int r, c, s, d, z;
        boolean alive = true;   // 상어 생존 여부 저장
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

//        @Override
//        public String toString() {
//            return String.format("상어위치 R: %d, C: %d, 속도: %d, 방향: %d", r, c, s, d);
//        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        M = stoi(st.nextToken());   // 상어의 수

        plain = new int[R + 1][C + 1];
        sharkList = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = stoi(st.nextToken());
            int c = stoi(st.nextToken());
            int s = stoi(st.nextToken());   // 속력
            int d = stoi(st.nextToken());   // 이동방향
            int z = stoi(st.nextToken());   // 크기

            // 상어 속도 그대로 받으면 시간초과남!!!!
            if(d == 1 | d == 2) s %= 2 * (R - 1);
            else s %= 2 * (C - 1);

            sharkList.add(new Shark(r, c, s, d, z));
            plain[r][c] = sharkList.size(); // 1번쨰로 잡힌거 1번으로 저장
        }

        // 열 이동하며 상어 낚시 시작
        for(int c = 1; c <= C; c++)  fishing(c);

        System.out.println(answer);
    } // end of main

    public static void fishing(int c) {

        for(int r = 1; r <= R; r++) {
            if(plain[r][c] != 0) {
                Shark s = sharkList.get(plain[r][c] - 1);
                plain[r][c] = 0;
                answer += s.z;
                s.alive = false;
                break;
            } // 해당 열에 상어가 있으면 상어 잡고 크기 합산
        }
        // 상어 잡기 완료 후 상어 이동
        move();
    } // end of func

    public static int[] dr = {0, -1, 1, 0, 0};  // 고정, 위, 아래, 우, 좌
    public static int[] dc = {0, 0, 0, 1, -1};
    public static void move() {

        nextPlain = new int[R + 1][C + 1];

//        System.out.println("---BEFORE---");
//        for(int i = 0; i <= R; i++) System.out.println(Arrays.toString(plain[i]));

        for(int i = 0; i < sharkList.size(); i++) {

            Shark shark = sharkList.get(i);
            // 죽은 상어는 pass
            if(!shark.alive) continue;

            // 상어 스피드만큼 이동 시작
            moveShark(shark, i, 0);
        }

//        System.out.println("---AFTER---");
//        for(int i = 0; i <= R; i++) System.out.println(Arrays.toString(nextPlain[i]));

        update();
    } // end of func

    /**
     * @param shark 상어 객체
     * @param idx   상어의 sharkList 상에서의 위치
     * @param cnt   이동 횟수
     */
    public static void moveShark(Shark shark, int idx, int cnt) {

        if(cnt == shark.s) {
            // 이동 완료 된 칸에 상어가 없으면 이동
            if(nextPlain[shark.r][shark.c] == 0) nextPlain[shark.r][shark.c] = idx + 1;
            else {
                Shark origin = sharkList.get(nextPlain[shark.r][shark.c] - 1);
                // 새로온 상어의 사이즈가 더 큰 경우
                if(origin.z < shark.z) {
//                    System.out.println("원래 있던 상어: " + nextPlain[shark.r][shark.c]);
//                    System.out.println("새로 방문한 상어: " + (idx + 1));
                    origin.alive = false;
                    nextPlain[shark.r][shark.c] = idx + 1;
                }
                // 원래 있던 상어가 더 큰 경우
                else shark.alive = false;
            }
            return;
        }

        int nr = shark.r + dr[shark.d];
        int nc = shark.c + dc[shark.d];

        if(isPossible(nr, nc)) {
            shark.r = nr;
            shark.c = nc;
            moveShark(shark, idx, cnt + 1);
        }
        else {
            // 방향 반전 후 재이동
            if(shark.d == 1) shark.d = 2;
            else if(shark.d == 2) shark.d = 1;
            else if(shark.d == 3) shark.d = 4;
            else if(shark.d == 4) shark.d = 3;
            moveShark(shark, idx, cnt);
        }
    } // end of func

    public static void update() {

        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                if(plain[i][j] != nextPlain[i][j]) plain[i][j] = nextPlain[i][j];
            }
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi

    public static boolean isPossible(int r, int c) {
        return 0 < r && r <= R && 0 < c && c <= C;
    } // end of func
}
