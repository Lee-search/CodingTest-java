package w0731;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution_D3_1289 {
     
    public static String make(int[] p) {
        String t = "";
        for(int i = 0; i < p.length; i++) {
            t += p[i];
        }
        return t;
    }
     
    public static void push(int t, int s, int[] p) {
        for(int i = s; i < p.length; i++) {
            p[i] = t;
        }
    }
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        String n = br.readLine();
        for(int test_case = 1; test_case <= Integer.parseInt(n); test_case++ ) {
            String mem = br.readLine();
            int[] origin = new int[mem.length()];
            int[] local = new int[mem.length()];
             
            // origin 초기화
            for(int i = 0; i < mem.length(); i++) {
                origin[i] = mem.charAt(i) - '0';
            }
             
            int count = 0;
            // 같아질 때 까지 반복
            while(!make(origin).equals(make(local))) {
                // 첫 문자부터 비교
                for(int i = 0; i < mem.length(); i++) {
                    if(origin[i] != local[i]) {
                        push(origin[i], i, local);
                        count += 1;
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + count);  
        }
    }
}