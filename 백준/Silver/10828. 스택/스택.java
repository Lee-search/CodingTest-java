import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, size;
    public static int[] stack;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        stack = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            if(command.equals("push")) push(Integer.parseInt(st.nextToken()));
            else if(command.equals("pop")) pop();
            else if(command.equals("size")) size();
            else if(command.equals("empty")) empty();
            else if(command.equals("top")) top();
        }

    } // end of main

    public static void push(int num) {
        stack[size++] = num;
    }

    public static void pop() {

        if(size == 0) {
            System.out.println(-1);
            return;
        }
        else System.out.println(stack[size - 1]);

        stack[size - 1] = 0;  // 초기화
        size -= 1;
    }

    public static void size() {
        System.out.println(size);
    }

    public static void empty() {
        if(size == 0) System.out.println(1);
        else System.out.println(0);
    }

    public static void top() {
        if(size == 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(stack[size - 1]);
    }
} // end of class
