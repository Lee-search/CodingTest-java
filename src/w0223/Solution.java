package w0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] likeInfo;
    static int[] orderInfo;
    static int[][] plain;

    // 우선순위 순으로 북, 서, 동, 남 이동
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        likeInfo = new int[N * N][4];   // 좋아하는 사람 정보
        orderInfo = new int[N * N];     // 교실에 앉는 순서
        plain = new int[N][N];      // 교실

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            orderInfo[i] = stoi(st.nextToken());
            for (int j = 0; j < 4; j++) {
                likeInfo[orderInfo[i]][j] = stoi(st.nextToken());
            }
        } // end of init

        // 순서대로 책상에 앉음
        for (int student : orderInfo) {

            // 1. 비어있는 칸중에 좋아하는 학생이 가장 많은 칸
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!isEmpty(i, j)) {
                        getAdjCount(student, i, j);
                    }
                }
            }

            // 2. 1 중에서 비어있는 칸이 가장 많은 칸

            // 3. 2 중에서 위(행)에 있을 수록, 왼쪽(열)에 있을 수록 선택
        }


    } // end of main

    // 해당 칸이 비어있는 칸인지 확인
    public static boolean isEmpty(int r, int c) {
        return plain[r][c] == 0 ? true : false;
    }

    // 해당 칸의 주변에 좋아하는 친구가 몇명인지 확인
    public static int getAdjCount(int student, int r, int c) {

        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            int pos = plain[nr][nc];
            int[] info = likeInfo[student];
            // 해당 칸의 학생이 좋아하는 학생인 경우
            for (int i = 0; i < 4; i++) {
                if(info[i] ==pos) {
                    count += 1;
                    break;
                }
            }
        } // end of for

        return count;
    } // end of func

    // 두 좌표가 인접한 좌표인지 판단
    public static boolean adjacent(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2) == 1;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}