package others.baekjoon;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

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

        @Override
        public String toString() {
            return String.format("상어위치 R: %d, C: %d, 속도: %d, 방향: %d", r, c, s, d);
        }
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

            sharkList.add(new Shark(r, c, s, d, z));
            plain[r][c] = sharkList.size(); // 1번쨰로 잡힌거 1번으로 저장
        }

//        for(int i = 0; i <= R; i++) System.out.println(Arrays.toString(plain[i]));
//        System.out.println(sharkList);

        // 열 이동하며 상어 낚시 시작
        for(int c = 1; c <= C; c++)  fishing(c);
    } // end of main

    public static void fishing(int c) {

        for(int r = 1; r <= R; r++) {
            if(plain[r][c] != 0) {
                Shark s = sharkList.get(plain[r][c] - 1);
                plain[r][c] = 0;
                answer += s.s;
                s.alive = false;
            } // 해당 열에 상어가 있으면 상어 잡고 크기 합산
        }
        // 상어 잡기 완료 후 상어 이동
        move();
    }

    public static int[] dr = {0, -1, 1, 0, 0};  // 0, 위, 아래, 우, 좌
    public static int[] dc = {0, 0, 0, 1, -1};
    public static void move() {

        nextPlain = new int[R + 1][C + 1];
        for(int i = 0; i < sharkList.size(); i++) {

            Shark shark = sharkList.get(i);
            // 죽은 상어는 pass
            if(!shark.alive) continue;

            System.out.println("---BEFORE---");
            System.out.println(shark);

            // 상어 스피드만큼 이동 시작
            for(int j = 0; j < shark.s; j++) {
                int nr = shark.r + dr[shark.d];
                int nc = shark.c + dc[shark.d];

                if(isPossible(nr, nc)) {
                    shark.r = nr;
                    shark.c = nc;
                }
                else {
                    // 방향 반전 후 재이동
                    if(shark.d == 1) shark.d = 2;
                    else if(shark.d == 2) shark.d = 1;
                    else if(shark.d == 3) shark.d = 4;
                    else if(shark.d == 4) shark.d = 3;
                    shark.r += dr[shark.d];
                    shark.c += dc[shark.d];
                }
            } // end of for

            System.out.println("---AFTER---");
            System.out.println(shark);
        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi

    public static boolean isPossible(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    } // end of func
}
