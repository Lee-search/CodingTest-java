import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int n;	// n - 1일까지 근무
    public static int[][] workList;
    public static int answer = 0;	// 최대 급여

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        workList = new int [n][2];

        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
            workList[i][0] = Integer.parseInt(st.nextToken());
            workList[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }
    
    public static void dfs(int day, int total) {
    	if(day == n) {
    		answer = Math.max(answer, total);
    		return;
    	}
    	
    	if(day + workList[day][0] <= n) {
    		// 해당 일을 골랐을 때
    		dfs(day + workList[day][0], total + workList[day][1]);
    	}
    	// 해당 일을 고르지 않았을 때
    	dfs(day + 1, total);
    }
}
