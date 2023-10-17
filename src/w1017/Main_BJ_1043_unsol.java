package w1017;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1043_unsol {

    static int N, M, tmp;
    static int[] parents;
    static boolean[] isKnow;
    static Queue<int[]> q;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        q = new ArrayDeque<>();
        
        // 거짓말 아는 사람 입력
        st = new StringTokenizer(br.readLine());
        tmp = stoi(st.nextToken());
        for(int i = 0; i < tmp; i++) 

        make();  // 부모 트리 생성
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            tmp = stoi(st.nextToken());
            // 파티에 한명만 참여한 경우
            if(T == 1) q.add(new int[] {stoi(st.nextToken())});
            // 여러명 참여하는 경우
            else {
                int[] tmp = new int[T];
                for (int j = 0; j < T; j++) {
                    tmp[j] = stoi(st.nextToken());
                }
                q.add(tmp);
                System.out.println("t");
            }
        } // end of for

        for(int[] info : q) {
            System.out.println(Arrays.toString(info));
        }
    } // end of main

    public static void make() {

        parents = new int[N + 1];
        for(int i = 1; i <= N; i++)
            parents[i] = i;
    }

    public static int find(int a) {

        if(a == parents[a]) return a;
        else return parents[a] = find(a);
    } // end of find

    public static void union(int a, int b) {

        int aRoot = find(a), bRoot = find(b);
        if(aRoot == bRoot) return;
        if(aRoot > bRoot) parents[a] = bRoot;
        else parents[a] = aRoot;
    } // end of union

    public static int stoi(String s){
        return Integer.parseInt(s);
    } // end of stoi
}
