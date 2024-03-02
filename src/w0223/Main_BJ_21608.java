package w0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/**
 * 상어 초등학교: https://www.acmicpc.net/problem/21608
 * 구현, for문의 r, c 탐색 순서에 따라 자연스럽게 조건3의 우선순위가 형성됨
 * 입력과 동시에 자리배치를 진행해서 반복문 최적화 가능
 */

public class Main_BJ_21608 {

    static int N, answer;
    static int[][] likeInfo;
    static int[] orderInfo;
    static int[][] plain;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = parseInt(br.readLine());
        likeInfo = new int[N * N + 1][4];   // 좋아하는 사람 정보
        plain = new int[N][N];          // 교실 정보

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likeInfo[student][j] = parseInt(st.nextToken());
            }

            // 순서대로 책상에 앉음
            setStudent(student);
        } // end of init

        getSatisfaction();
        System.out.println(answer);
    } // end of main

    // 현재 학생 번호 받아서 자리에 착석
    public static void setStudent(int student) {

        // 비어있는 칸중에 좋아하는 학생이 가장 많은 칸 찾기
        int maxR = -1, maxC = -1;
        int maxAdjCnt = 0; // 해당 칸의 학생 수
        int maxEmptyCnt = 0;   // 해당 칸의 비어있는 칸 수

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 이미 학생이 앉아있으면 pass
                if(plain[r][c] != 0) continue;

                // 해당 좌표의 친한 학생 수, 비어있는 자리 수 조회
                int curAdjCnt = getAdjCount(student, r, c);
                int curEmptyCnt = getEmptyCnt(r, c);

                // 조건 1: 인접한 친한 학생 수가 더 많은 경우 선택
                if (maxAdjCnt < curAdjCnt) {
                    maxAdjCnt = curAdjCnt;
                    maxEmptyCnt = curEmptyCnt;
                    maxR = r;
                    maxC = c;
                }
                // 조건 1-1: 인접한 친한 학생 수가 같은 경우
                else if (maxAdjCnt == curAdjCnt) {
                    // 조건 2: 비어있는 칸이 더 많은 경우 선택
                    // 조건 2-1: 비어있는 칸이 같으면, for 탐색 우선순위에 따라 현재의 r, c 선택
                    if(maxEmptyCnt <= curEmptyCnt) {
                        maxR = r;
                        maxC = c;
                        maxEmptyCnt = curEmptyCnt;
                    }
                } // end of if-1
            } // end of for-c
        } // end of for-r

        plain[maxR][maxC] = student;
    } // end of func

    // 모든 자리가 결정 된 후 만족도 조회
    public static void getSatisfaction() {

        int[] score = {0, 1, 10, 100, 1000};

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int adjCnt = getAdjCount(plain[r][c], r, c);
                answer += score[adjCnt];
            }
        }
    } // end of func

    // 주변에 좋아하는 친구가 몇명인지 확인
    public static int getAdjCount(int stdNum, int r, int c) {

        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!isVisitable(nr, nc)) continue;

            int nearBy = plain[nr][nc];
            int[] info = likeInfo[stdNum];
            // 근처에 있는 학생이 좋아하는 학생인 경우
            for (int i = 0; i < 4; i++) {
                if(nearBy == info[i]) {
                    count += 1;
                    break;
                }
            }
        } // end of for

        return count;
    } // end of func

    // 주변에 비어있는 칸의 갯수 출력
    public static int getEmptyCnt(int r, int c) {

        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!isVisitable(nr, nc)) continue;

            // 학생이 없는 칸인 경우
            if (plain[nr][nc] == 0) {
                count += 1;
            }
        } // end of for

        return count;
    }

    public static boolean isVisitable(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}