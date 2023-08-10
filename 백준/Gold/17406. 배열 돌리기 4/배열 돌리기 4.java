import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M, K; // 크기, 연산횟수
    static int[][] plain;
    static int[][] copy;
    static int[][] commands;
    static int[] numbers;
    static boolean[] isSelected;
    static int answer = Integer.MAX_VALUE; // 행별 합의 최소값 구하기
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        K = stoi(st.nextToken());
        plain = new int[N][M];
        isSelected = new boolean[K];
        numbers = new int[K];
        commands = new int[K][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                plain[i][j] = stoi(st.nextToken());
            }
        } // end of input
        
        // 명령어 받기
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            commands[i][0] = stoi(st.nextToken()) - 1;
            commands[i][1] = stoi(st.nextToken()) - 1;
            commands[i][2] = stoi(st.nextToken());
        }

        permutations(0);

        System.out.println(answer);

    } // end of main

    public static void permutations(int cnt) {
        
        // 다 돌았으면 값 구한 후 answer와 비교
        if(cnt == K) {
            copy = new int[N][M];   // 배열 복사 후 회전
            for(int i = 0; i < N; i++) copy[i] = plain[i].clone();

            for(int i = 0; i < K; i++) {
                int r = commands[numbers[i]][0];
                int c = commands[numbers[i]][1];
                int s = commands[numbers[i]][2];

                rotate(r, c, s);
            }
            for(int j = 0; j < N; j++) { // 행마다 돌며 합의 최소 구하기
                answer = Math.min(answer, Arrays.stream(copy[j]).sum());
            }
            
            return;
        }
        
        // 순열 하나 선택하고, 돌리기
        for(int i = 0; i < K; i++) {
            if(isSelected[i]) continue; // 중복 제거
            
            isSelected[i] = true;
            numbers[cnt] = i;
            permutations(cnt + 1);
            isSelected[i] = false;
        }
    }

    public static void rotate(int r, int c, int s) {

        int tmp = copy[r - s][c - s]; // 시작좌표 저장

        // (r + s, c - s) -> (r - s, c - s)
        for(int nr = r - s; nr < r + s; nr++) {
            copy[nr][c - s] = copy[nr + 1][c - s];
        }

        // (r + s, c + s) -> (r + s, c -s)
        for(int nc = c - s; nc < c + s; nc++) {
            copy[r + s][nc] = copy[r + s][nc + 1];
        }

        // (r - s, c + s) -> (r + s, c + s)
        for(int nr = r + s; nr > r - s; nr--) {
            copy[nr][c + s] = copy[nr - 1][c + s];
        }

        // (r - s, c - s) -> (r - s, c + s)
        for(int nc = c + s; nc > c - s; nc--) {
            copy[r - s][nc] = copy[r - s][nc - 1];
        }

        copy[r - s][c - s + 1] = tmp;

        if(r + s - 1 > r && c + s - 1 > c) {
            rotate(r, c, s - 1);
        }
    } // end of func

    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
} // end of class
