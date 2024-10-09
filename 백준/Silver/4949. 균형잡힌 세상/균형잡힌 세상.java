import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {

            String line = br.readLine();
            if(line.equals(".")) break; // 종료조건

            boolean flag = false;
            Stack<String> stack = new Stack<>();

            for(int i = 0; i < line.length(); i++) {

                String str = line.substring(i, i + 1);
                // 여는 괄호이면 스택에 삽입
                if(str.equals("(") || str.equals("[")) {
                    stack.push(str);
                }
                // 닫는 괄호이면 스택에 있는 괄호와 비교
                else if(str.equals(")") || str.equals("]")) {

                    // 스택이 비어있는데 닫는 괄호 나옴
                    if(stack.isEmpty()) {
                        flag = true;
                        break;
                    }
                    else if(str.equals(")")  && stack.peek().equals("(")) {
                        stack.pop();
                    }
                    else if(str.equals("]")  && stack.peek().equals("[")) {
                        stack.pop();
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
            } // end of for

            // 모든 입력이 끝난 후, 문제가 발생했거나 stack 이 버었는지 확인
            if(flag || !stack.isEmpty()) {
                sb.append("no\n");
            }
            else {
                sb.append("yes\n");
            }
        } // end of while

        System.out.println(sb.toString());
    }
}