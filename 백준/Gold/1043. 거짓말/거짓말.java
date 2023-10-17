
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, knownCnt, pCnt, answer;
    static int[] parents;
    static boolean[] known;
    static List<int[]> parties;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        parties = new ArrayList<>();
        
        // 1. 거짓말 아는 사람 저장
        st = new StringTokenizer(br.readLine());
        knownCnt = stoi(st.nextToken());    // 사람 수
        known = new boolean[N + 1];
        for (int i = 0; i < knownCnt; i++) {
            known[stoi(st.nextToken())] = true;
        }

        // 2. 파티 정보 저장 및 트리 생성
        make();
        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());
            pCnt = stoi(st.nextToken());
            // 파티에 한명만 참여한 경우
            if(pCnt == 1) {
                parties.add(new int[] {stoi(st.nextToken())});
            }
            // 여러명 참여하는 경우
            else {
                int[] tmp = new int[pCnt];
                for (int j = 0; j < pCnt; j++) {
                    tmp[j] = stoi(st.nextToken());
                    if(j > 0) {
                        union(tmp[0], tmp[j]);  // 연결 관계 생성
                    }
                }
                parties.add(tmp);
            } // end of if-else
        } // end of for

//        System.out.println(Arrays.toString(parents));

        // 3. 진실을 아는 사람과 같은 파티에 참여했는지 연결 관계 조회
        for(int i = 1; i <= N; i++) {
            if(known[i]) {
                int root = find(i);
                for(int j = 1; j <= N; j++) {
                    if(i == j) continue;
                    if(root == find(j)) {
                        known[j] = true;
                    }
                }
            }
        } // end of for

        // 4. 파티 참여자 수와 진실을 모르는 사람의 수가 같은 경우 조회
        for(int[] pInfo : parties) {
            int cnt = 0;
            for (int j : pInfo) {
                if (known[j] || known[parents[j]]) break;
                cnt += 1;
            }
            if(cnt == pInfo.length) {
                answer += 1;
            }
        } // end of for

        System.out.println(answer);
    } // end of main

    public static void make() {

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {

        if(a == parents[a]) return a;
        else return parents[a] = find(parents[a]);
    } // end of find

    public static void union(int a, int b) {

        int aRoot = find(a), bRoot = find(b);
        if(aRoot == bRoot) return;
        if(aRoot > bRoot) parents[aRoot] = bRoot;
        else parents[bRoot] = aRoot;
    } // end of union

    public static int stoi(String s){
        return Integer.parseInt(s);
    } // end of stoi
}
