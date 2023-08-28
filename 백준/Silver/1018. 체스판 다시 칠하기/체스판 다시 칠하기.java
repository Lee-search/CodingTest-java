import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static final char W = 'W', B = 'B';
    static char[][] plain;
    static char[][] plainAtW, plainAtB;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = Integer.MAX_VALUE;
        plain = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                plain[i][j] = line.charAt(j);
            }
        } // end of init

        for(int r = 0; r < N - 8 + 1; r++) {
            for(int c = 0; c < M - 8 + 1; c++) {
                check(r, c);    // 해당 좌표에서 8 * 8 체크
            }
        }

        System.out.println(answer);
    } // end of main

    public static void check(int sr, int sc) {

        // 각각 W와 B로 시작하는 두 체스판 생성
        plainAtW = new char[8][8];
        plainAtB = new char[8][8];
        for(int i = 0; i < 8; i++) {

            plainAtW[i][0] = i % 2 == 0 ? W : B; // 0번째 칸부터 2칸마다 W 반복
            plainAtB[i][0] = i % 2 == 0 ? B : W; // 0번째 칸부터 2칸마다 B 반복
            for(int j = 1; j < 8; j++) {

                plainAtW[i][j] = plainAtW[i][j - 1] == W ? B : W;
                plainAtB[i][j] = plainAtB[i][j - 1] == B ? W : B;
            }
        }

        int cntAtW = 0, cntAtB = 0;
        for(int i = 0, r = sr; i < 8; i++, r++) {
            for(int j = 0, c = sc; j < 8; j++, c++) {
                // 서로 얼마나 다른지 카운트 누적
                if(plainAtW[i][j] != plain[r][c]) cntAtW += 1;
                if(plainAtB[i][j] != plain[r][c]) cntAtB += 1;
            }
        }

        answer = Math.min(answer, Math.min(cntAtW, cntAtB));
    } // end of func
}
