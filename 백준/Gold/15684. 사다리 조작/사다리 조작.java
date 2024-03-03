
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H; // 세로 갯수, 가로 갯수, 높이
    static int[][] plain;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        H = stoi(st.nextToken());
        plain = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());   // (a, b)에서
            int b = stoi(st.nextToken());   // (a, b+1)로 이동
            plain[a][b] = 1;
            plain[a][b + 1] = -1;
        } // end of init

        for (int cnt = 0; cnt <= 3; cnt++) {
            drawLine(1, 0, cnt);
        }

        System.out.println(-1);
    } // end of main

    // curCnt: 현재까지 새로 그은 선의 갯수
    // maxCnt: 최대로 그을 수 있는 선의 갯수
    public static void drawLine(int curR, int curCnt, int maxCnt) {

        // basis: curCnt를 maxCnt만큼 그렸을 때, 각 C에 대해 시뮬레이션 시행
        if (curCnt == maxCnt) {

//            System.out.println("maxCnt: " + maxCnt);
//            print();

            for (int c = 1; c <= N; c++) {
                // 시뮬레이션 결과 위치가 다르면 종료
                if (!simulation(c)) {
                    return;
                }
            }
            System.out.println(maxCnt);
            System.exit(0);
            return;
        }

        // 가로선 두개가 연속되는지 확인 후
        // 그릴 수 있으면 그리고 백트래킹
        for (int r = curR; r <= H; r++) {
            for (int c = 1; c < N; c++) {

                if (plain[r][c] == 0 && plain[r][c + 1] == 0) {
                    plain[r][c] += 1;
                    plain[r][c + 1] -= 1;
                    drawLine(r, curCnt + 1, maxCnt);
                    plain[r][c] -= 1;
                    plain[r][c + 1] += 1;
                }
            }
        }
    } // end of func

    // 시뮬레이션 후 시작지점과 도착지점 같으면 true 리턴
    public static boolean simulation(int startC) {

        int r = 1, c = startC;
//        System.out.println("시작지점: (" + r + ", " + c + ")");

        while (r <= H) {

            // 오른쪽으로 이동
            if (c < N && plain[r][c] == 1) {
                c += 1;
            } 
            // 왼쪽으로 이동
            else if (1 < c && plain[r][c] == -1) {
                c -= 1;
            }
            // 아래로 이동
            r += 1;
        }

//        System.out.println("도착지점: (" + r + ", " + c + ")");
        return c == startC;
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void print() {
        for (int i = 1; i <= H; i++) {
            System.out.println(Arrays.toString(plain[i]));
        }
    }
}
