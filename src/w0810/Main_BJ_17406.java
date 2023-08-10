package w0810;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_17406 {
    
    static int N, M, K; // 크기, 연산횟수
    static int[][] plain;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        plain = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                plain[i][j] = stoi(st.nextToken());
            }
        } // end of input
        
        // 명령어 받자마자 회전 실행
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotate(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1, stoi(st.nextToken()));
//            for(int j = 0; j < N; j++) System.out.println(Arrays.toString(plain[j]));
        }

        int answer = Integer.MAX_VALUE; // 행별 합의 최소값 구하기
        for(int i = 0; i < N; i++) {
            answer = Math.min(answer, Arrays.stream(plain[i]).sum());
        }

        System.out.println(answer);

    } // end of main

    public static void rotate(int r, int c, int s) {

        int tmp = plain[r - s][c - s]; // 시작좌표 저장

        // (r + s, c - s) -> (r - s, c - s)
        for(int nr = r - s; nr < r + s; nr++) {
            plain[nr][c - s] = plain[nr + 1][c - s];
        }

        // (r + s, c + s) -> (r + s, c -s)
        for(int nc = c - s; nc < c + s; nc++) {
            plain[r + s][nc] = plain[r + s][nc + 1];
        }

        // (r - s, c + s) -> (r + s, c + s)
        for(int nr = r + s; nr > r - s; nr--) {
            plain[nr][c + s] = plain[nr - 1][c + s];
        }

        // (r - s, c - s) -> (r - s, c + s)
        for(int nc = c + s; nc > c - s; nc--) {
            plain[r - s][nc] = plain[r - s][nc - 1];
        }

        plain[r - s][c - s + 1] = tmp;

        if(r + s - 1 > r && c + s - 1 > c) {
            rotate(r, c, s - 1);
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
} // end of class
