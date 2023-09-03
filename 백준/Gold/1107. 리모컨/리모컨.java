import java.util.Scanner;

public class Main {

    static int N, M, answer;
    static boolean[] isBroken;

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
        Scanner sc = new Scanner(System.in);

//        N = stoi(br.readLine());    // 만들고자 하는 수
//        M = stoi(br.readLine());    // 고장난 버튼 수
        N = sc.nextInt();
        M = sc.nextInt();
        answer = Math.abs(N - 100); // 100에서 N까지 +,-만 눌러서 도달하는 횟수
        isBroken = new boolean[10];

//        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
//            isBroken[stoi(st.nextToken())] = true;
            isBroken[sc.nextInt()] = true;
        } // end of init

        for (int i = 0; i < 1_000_000; i++){
            // N과 +, - 버튼을 조합해서 만들 수 있는지 확인
            if(isPossible(i)) {
                // N의 자릿수와 +버튼을 누른 횟수
                answer = Math.min(answer, itos(i).length() + Math.abs(N - i));
            }
        }

        System.out.println(answer);
    } // end of main

    public static boolean isPossible(int num) {
        // num을 만들 수 있으면 true, 하나라도 부숴진 버튼이 있으면 false 리턴
        String snum = itos(num);
        for (int i = 0; i < snum.length(); i++) {
            if(isBroken[snum.charAt(i) - '0']) return false;
        }
        return true;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi

    public static String itos(int n) {
        return Integer.toString(n);
    } // end of itos
}