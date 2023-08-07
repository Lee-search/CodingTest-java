import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 입력
 * 금액정보 1차원 배열
 * 이용계획 1차원 배열
 * - 1월 ~ 12월 각 달마다 이용권 정하고, 금액 추가 작업
 *      - 이용권 정해서 다음달로 전달
 *      - 정한 이용권에 따른 금액도 다음달로 전달
 * - 가저조건: 12월까지만 확인
 * - 출력 포멧 맞춰서 출력
 */
public class Solution {

    public static int[] tickets = new int[4];
    public static int[] plans = new int[12];
    public static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = stoi(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < 4; i++)  // 이용권 가격
                tickets[i] = stoi(st.nextToken());

            answer = tickets[3];	// 최소값의 기준을 연권으로 잡고 시작

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 12; i++) // 12개월 이용 계획
                plans[i] = stoi(st.nextToken());

            DFS(0, 0);
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    } // end of main

    /**
     * 
     * @param month : 이용 개월 수
     * @param sum : 이용 가격
     */

    public static void DFS(int month, int sum) {	// cnt: 0~11 + 1월

        if(month >= 12) {
            answer = Math.min(answer, sum);
            return;
        }

        // 1일권
        // 해당 월의 가격 저장
        DFS(month + 1, sum + (plans[month] * tickets[0]));

        // 1달권
        DFS(month + 1, sum + tickets[1]);

        // 3달권
        DFS(month + 3, sum + tickets[2]);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
