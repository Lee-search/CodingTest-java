package w0803;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_D2_2001 {
	public static void main(String[] args) throws Exception {
    	System.setIn(new FileInputStream(new File("./src/w0803/input.txt")));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
 
        int T = stoi(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
 
            st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int M = stoi(st.nextToken());
            int[][] plain = new int[N][N];
            int answer = 0;
 
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    plain[i][j] = stoi(st.nextToken());
                }
            } // end of input
 
            // r + M 이 맵 밖으로 나가면 안됨
            for(int r = 0; r < N - M + 1; r++) {
                for(int c = 0; c < N - M + 1; c++) {
                    int sum = 0;
                    for(int i = r; i < r + M; i++) {
                        for(int j = c; j < c + M; j++) {
                            sum += plain[i][j];
                        }
                    }
                    answer = Math.max(answer, sum);
                }
            } // end of for
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        } // end of tc
 
        System.out.print(sb.toString());
    } // end of main
 
    public static int stoi(String s) {
        return Integer.parseInt(s);
    } // end of stoi
} // end of class