import java.util.*;

class Solution {
    
    public int solution(int[] priorities, int location) {
        
        int count = 0;  // 작업이 끝날때마다 갯수 기록
        int[] remains = new int[10];    // 우선순위 작업의 갯수 기록
        Queue<int[]> q = new ArrayDeque<>();
        
        for(int i = 0; i < priorities.length; i++) {
            
            q.offer(new int[] {i, priorities[i]});  // idx, priority
            remains[priorities[i]] += 1;
        }
        
        while(!q.isEmpty()) {
            
            int idx = q.peek()[0];
            int priority = q.poll()[1];
            
            boolean flag = false;
            for(int p = priority + 1; p < 10; p++) {

                if(remains[p] > 0) {
                    flag = true;
                    break;
                }
            }
            
            // 큐에 더 우선순위 높은 작업이 남아있는 경우
            if(flag) {
                // 다시 큐에 삽입
                q.offer(new int[] {idx, priority});
                continue;
            }
            // 큐에 더 높은 작업이 없는 경우
            remains[priority] -= 1;
            count += 1;
            
            // 조건에 맞는지 확인
            if(idx == location) {
                break;
            }
        }
        
        return count;
    }
}