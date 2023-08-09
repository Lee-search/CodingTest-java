import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, front, back;
    public static int[] queue;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        queue = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }
        } // end of for

        System.out.println(sb);
    } // end of main

    public static void push(int num) {
        queue[back++] = num;
    }

    public static int pop() {
        if(back() == -1) return -1;
        return queue[front++];
    }

    public static int size() {
        return back - front;
    }

    public static int empty() {
        if(size() == 0) return 1;
        return 0;
    }

    public static int front() {
        if(size() == 0)  return -1;
        return queue[front];
    }
    
    public static int back() {
    	if(size() == 0) return -1;
    	return queue[back - 1];
    }
} // end of class