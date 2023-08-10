package w0810;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215 {

    static int N, L, Answer;
    static int[][] ingrd;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream(new File("./src/w0810/input_3.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {

            st = new StringTokenizer(br.readLine());
            N = stoi(st.nextToken());   // 재료 수
            L = stoi(st.nextToken()); // 칼로리 제한
            ingrd = new int[N][2];
            Answer = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                // 재료 받기
                ingrd[i][0] = stoi(st.nextToken()); // 점수
                ingrd[i][1] = stoi(st.nextToken()); // 칼로리
            }

            subset(0, 0, 0);

            sb.append("#").append(testCase).append(" ").append(Answer).append("\n");
        }
        System.out.println(sb);
    } // end of main

    static void subset(int cnt, int scoreSum, int calSum) {

        if(calSum > L) return; // 제한 칼로리 초과
        Answer = Math.max(Answer, scoreSum);
        if(cnt == N) return;

        subset(cnt + 1, scoreSum + ingrd[cnt][0], calSum + ingrd[cnt][1]);
        subset(cnt + 1, scoreSum, calSum);
    }

    static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
