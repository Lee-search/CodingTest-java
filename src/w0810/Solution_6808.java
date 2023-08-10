package w0810;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808 {

    public static int answer_win, answer_lost;
    public static int[] cards_iy;    // 인영이 카드 순열
    public static int[] cards_ky;   // 규영이 카드 순열
    public static boolean[] isSelected;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream(new File("./src/w0810/input_2.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = stoi(br.readLine());

        for(int testCase = 1; testCase <= T; testCase++) {

            cards_iy = new int[9];
            cards_ky = new int[9];
            isSelected = new boolean[18 + 1];   // 0번 카드는 없음
            answer_win = 0;
            answer_lost = 0;
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 9; i++) {
                cards_iy[i] = stoi(st.nextToken());
                isSelected[cards_iy[i]] = true; // 인영이가 집은 카드 못집도록함
            }   // 인영이 카드 입력

            permutations(0);
            sb.append("#").append(testCase).append(" ")
                    .append(answer_win).append(" ").append(answer_lost).append("\n");
        } // end of tc

        System.out.println(sb);
    } // end of main

    public static void permutations(int cnt) {

        if(cnt == 9) { // 기저조건

            int score_iy = 0;   // 인영점수
            int score_ky = 0;   // 규영점수
            for(int i = 0; i < 9; i++) {
                if(cards_iy[i] > cards_ky[i]) {
                    score_iy += cards_iy[i] + cards_ky[i];
                }
                else {
                    score_ky += cards_iy[i] + cards_ky[i];
                }
            }

            if(score_ky > score_iy) answer_lost += 1;
            else answer_win += 1;

            return;
        }

        for(int i = 1; i <= 18; i++) {
            if(isSelected[i]) continue; // 집었으면 넘어감

            isSelected[i] = true;
            cards_ky[cnt] = i;
            permutations(cnt + 1);
            isSelected[i] = false;
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
} // end of class
