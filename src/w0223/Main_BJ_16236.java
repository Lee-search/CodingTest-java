package w0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아기 상어: https://www.acmicpc.net/problem/16236
 * 구현, BFS, r과 c의 우선순위에 따라 방문 위치 갱신해주어야함에 유의
 */

public class Main_BJ_16236 {

    static int N, answer;
    static int[][] plain;

    // 북, 서, 동, 남 순으로 우선순위 지님
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static Shark shark;

    static class Shark {
        int r, c, size, eaten;
        public Shark(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.eaten = 0;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        plain = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                plain[i][j] = stoi(st.nextToken());
                if (plain[i][j] == 9) {
                    shark = new Shark(i, j, 2);
                }
            }
        } // end of init

        // 생선 탐색
        while (true) {

//            System.out.println("현재까지 이동 횟수: " + answer);
//            System.out.println("현재 상어의 크기: " + shark.size);
//            print();

            int[] fishInfo = findEatableFish();
            // 더 이상 먹을 수 있는 생선이 없는 경우
            if (fishInfo[0] == -1) {
                break;
            }

            // 1. 상어를 먹은 생선의 위치로 이동
            plain[shark.r][shark.c] = 0;
            plain[fishInfo[0]][fishInfo[1]] = 9;

            shark.r = fishInfo[0];
            shark.c = fishInfo[1];
            answer += fishInfo[2];
            
            // 2. 상어의 사이즈 계산
            if(shark.eaten + 1 == shark.size) {
                shark.eaten = 0;
                shark.size += 1;
            } else {
                shark.eaten += 1;
            }
        } // end of while

        System.out.println(answer);
    } // end of main

    public static int[] findEatableFish() {

        int mvR = -1, mvC = -1, mvCnt = -1;

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{shark.r, shark.c, 0});
        visited[shark.r][shark.c] = true;

        while (!q.isEmpty()) {
            
            int[] info = q.poll();
            int r = info[0], c = info[1], cnt = info[2];

            for (int d = 0; d < 4; d++) {
                // 새로운 위치 탐색
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 갈수 없는 칸이면 pass
                if(!isVisitable(nr, nc) || visited[nr][nc]) continue;

                // 1. 비어있는 칸이면 이동
                // 2. 나랑 사이즈가 같은 생선이면 이동만 가능
                if(plain[nr][nc] == 0 || plain[nr][nc] == shark.size) {
                    q.offer(new int[]{nr, nc, cnt + 1});
                    visited[nr][nc] = true;
                }
                // 3. 먹을 수 있으면 해당 좌표의 우선순위 계산
                else if (plain[nr][nc] < shark.size) {

                    // 먹을 수 있는 최초 값인 경우
                    if(mvCnt == -1) {
                        mvR = nr;
                        mvC = nc;
                        mvCnt = cnt + 1;
                    }
                    // 같은 거리에 있는 생선이 존재하는 경우
                    else if (mvCnt == cnt + 1) {
                        if (mvR > nr) {
                            mvR = nr;
                            mvC = nc;
                        } else if (mvR == nr) {
                            if(mvC > nc) {
                                mvC = nc;
                            }
                        }
                    }
                }
            }
        } // end of while

        return new int[] {mvR, mvC, mvCnt};
    } // end of func

    public static boolean isVisitable(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(plain[i]));
        }
    }
}
