import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16918
public class Main {

    static int R, C, N;
    static char[][] plain;
    static int[][] cntMarker;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        N = stoi(st.nextToken());

        plain = new char[R][C];
        cntMarker = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                plain[i][j] = line.charAt(j);
                if (plain[i][j] == 'O') {
                    cntMarker[i][j] = 3;    // 3초 타이머
                }
            }
        } // end of init

        // 3초 카운트

        // 1초 후 cntMarker 업데이트
        // 2초 후 cnt 후 폭탄 설치
        // 3초 후 cnt 후 cntMarker 업데이트, 폭탄 터진거 확인
        for (int time = 1; time <= N; time++) {

            // 홀수 초에는 폭탄 처리
            if (time % 2 == 1) {
                explode();
            }
            // 짝수 초에는 폭탄 설치
            else if (time % 2 == 0) {
                explode();
                setBombs();
            }
            //print(time);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(plain[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    } // end of main

    // 4방향
    public static void explode() {

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 타이머 다 된 경우
                if (cntMarker[r][c] == 1) {
                    cntMarker[r][c] -= 1;
                    plain[r][c] = '.';
                    // 폭탄이 터지는 경우 4방향도 폭발
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                        // 폭발한거 plain에 반영
                        plain[nr][nc] = '.';
                    }
                }
                // 터지지 않는 경우 카운트 감소
                else if (cntMarker[r][c] > 1) {
                    cntMarker[r][c] -= 1;
                }
            }
        } // end of for

        // 주변 4칸 연쇄폭발로 사라졌는데 cntMarker에 남아있는거 제거
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (cntMarker[r][c] > 0 && plain[r][c] == '.') {
                    cntMarker[r][c] = 0;
                }
            }
        }
    } // end of func

    // 빈 칸이면 폭탄 설치
    public static void setBombs() {

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (plain[r][c] == '.') {
                    plain[r][c] = 'O';
                    cntMarker[r][c] = 3;
                }
            }
        }
    }

//    public static void print(int time) {
//        System.out.println(time + "초 경과...");
//        System.out.println(" -- plain -- ");
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(plain[i]));
//        }
//        System.out.println(" -- cntMarker -- ");
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(cntMarker[i]));
//        }
//    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
