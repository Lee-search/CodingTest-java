package w1018;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_1504_unsol {

    static final int INF = 100_000_000;
    static int N, E;
    static int[][] plain;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        E = stoi(st.nextToken());
        plain = new int[N + 1][N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            plain[a][b] = plain[b][a] = c;
        }

        // 연결 간선이 없는 정점끼리는 INF로 초기화
        for (int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i != j && plain[i][j] == 0) plain[i][j] = INF;
            }
        } // end of init

//        for(int i = 0; i <= N; i++)
//            System.out.println(Arrays.toString(plain[i]));

    } // end of main

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
