import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static Stack<Character> stack;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int testCase = 0; testCase < N; testCase++) {    // 라인 입력

            String line = br.readLine();

            // 홀수개인 경우, 괄호가 닫힐 수 없음
            if(line.length() % 2 != 0) {
                sb.append("NO").append("\n");
                continue;
            }

            stack = new Stack<>();
            boolean flag = false;
            for(int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (ch == '(') stack.push(ch);
                else if (ch == ')') {

                    if(!stack.isEmpty() /*&& stack.peek() == '('*/) stack.pop();
                    else {
                        flag = true;
                        break;
                    }
                }
            } // end of for

            if(flag) sb.append("NO").append("\n");
            else if(stack.isEmpty()) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        } // end of tc

        System.out.println(sb);
    } // end of main
} // end of class
