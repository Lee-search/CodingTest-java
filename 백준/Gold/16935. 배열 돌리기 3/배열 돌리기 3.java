import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    // r, c, calCnt
    static int N, M, R;
    static int[][] maps;

    /** initialize */
    public static void initial() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /** 상하 반전 */
    public static void cal1() {
        int[] temp;
        for (int i = 0; i < N / 2; i++) {
            temp = maps[i];
            maps[i] = maps[N - 1 - i];
            maps[N - 1 - i] = temp;
        }
    }

    /** 좌우 반전 */
    public static void cal2() {
        int temp;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M / 2; j++) {
                temp = maps[i][j];
                maps[i][j] = maps[i][M - 1 - j];
                maps[i][M - 1 - j] = temp;
            }
        }
    }

    /** 오른쪽 90도 회전 */
    public static void cal3() {
        int[][] nMaps = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nMaps[j][N - 1 - i] = maps[i][j];
            }
        }
        maps = nMaps;
        swapNM();
    }

    /** 왼쪽 90도 회전 */
    public static void cal4() {
        int[][] nMaps = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nMaps[M - j - 1][i] = maps[i][j];
            }
        }
        maps = nMaps;
        swapNM();
    }

    /** 회전시켰을 때 N, M 갱신 */
    public static void swapNM() {
        int temp = N;
        N = M;
        M = temp;
    }

    /** 4분할 시계 방향 돌리기 */
    public static void cal5() {
        int[][] nMaps = new int[N][M];
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                nMaps[i][M / 2 + j] = maps[i][j];
                nMaps[N / 2 + i][M / 2 + j] = maps[i][M / 2 + j];
                nMaps[N / 2 + i][j] = maps[N / 2 + i][M / 2 + j];
                nMaps[i][j] = maps[N / 2 + i][j];
            }
        }
        maps = nMaps;
    }

    /** 4분할 시계 반대방향 돌리기 */
    public static void cal6() {
        int[][] nMaps = new int[N][M];
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                nMaps[i][j] = maps[i][M / 2 + j];
                nMaps[i][M / 2 + j] = maps[N / 2 + i][M / 2 + j];
                nMaps[N / 2 + i][M / 2 + j] = maps[N / 2 + i][j];
                nMaps[N / 2 + i][j] = maps[i][j];
            }
        }
        maps = nMaps;
    }

    /** 자기 해당 계산 찾기 */
    public static void cal(int n) {
        switch (n) {
            case 1:
                cal1();
                return;
            case 2:
                cal2();
                return;
            case 3:
                cal3();
                return;
            case 4:
                cal4();
                return;
            case 5:
                cal5();
                return;
            case 6:
                cal6();
                return;
            default:
                return;
        }
    }

    /** map 출력 */
    public static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(maps[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        // 10 H N
        initial();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            cal(Integer.parseInt(st.nextToken()));
        }
        printMap();
    }
}
