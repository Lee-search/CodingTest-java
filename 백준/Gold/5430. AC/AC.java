import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    static int N;
    static Deque<Integer> deque;
    static boolean isErr, isReversed;
    static String command;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {

            command = br.readLine();
            N = stoi(br.readLine());
            deque = new ArrayDeque<>();
            isErr = false;
            isReversed = false;

            st = new StringTokenizer(br.readLine(), "[,]");
            for (int i = 0; i < N; i++) {
                deque.offerLast(stoi(st.nextToken()));
            } // end of init

            for(int i = 0; i < command.length() && !isErr; i++) {
                switch (command.charAt(i)) {
                case 'R':   // 짝수번 호출되면 굳이 리버스 안해도됨
                    isReversed = !isReversed;
                    break;
                case 'D':
                    if (deque.isEmpty()) {
                        isErr = true;
                        break;
                    }
                    if(isReversed) deque.pollLast();
                    else deque.pollFirst();
                    break;
                } // end of switch
            } // end of for

            if(isErr) sb.append("error\n");
            else {

                if(isReversed) reverse();
                sb.append(makeString()).append("\n");
            }
        } // end of tc

        System.out.print(sb);
    } // end of main

    public static void reverse() {

        Deque<Integer> tmp = new ArrayDeque<>();
        while (!deque.isEmpty()) {
            tmp.offerLast(deque.pollLast());
        }
        deque = tmp;
    }

    public static String makeString() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst());
            if(!deque.isEmpty()) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
}
