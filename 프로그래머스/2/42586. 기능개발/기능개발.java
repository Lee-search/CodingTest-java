import java.util.*;

class Solution {
    
    int remain, answer[];
    Queue<int[]> q;
    List<int[]> tmpList;
    List<Integer> aList;
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        remain = progresses.length; // 남은 작업 갯수
        q = new ArrayDeque<>();     // 작업 큐
        aList = new ArrayList<>();
        
        // 큐에 삽입
        for(int i = 0; i < progresses.length; i++) {
            q.offer(new int[] {i, progresses[i]});
        }
        
        while(!q.isEmpty()) {
            
            tmpList = new ArrayList<>();
            while(!q.isEmpty()) {

                // 큐에서 빼기
                int idx = q.peek()[0];
                int progress = q.poll()[1];

                // 작업 진도 갱신
                progress += speeds[idx];

                // 임시 리스트에 삽입
                tmpList.add(new int[] {idx, progress});
            }
            
            // for(int[] info : tmpList) {
            //     System.out.print(info[0] +"번 작업: " + info[1] + ", ");
            // }
            // System.out.println();

            int count = 0;
            boolean flag = false;
            for(int[] info : tmpList) {

                // 100퍼 됐으면
                if(!flag && info[1] >= 100) {
                    // 삭제 후 카운트 계산
                    count += 1;
                    continue;
                }
                // 100퍼 아니면 다시 큐에 삽입
                else {
                    flag = true;
                }
                
                q.offer(info);
            }

            // 완료된 작업이 있으면 정답 리스트에 추가
            if(count > 0) {
                aList.add(count);
            }
        }

        answer = new int[aList.size()];
        for(int i = 0; i < aList.size(); i++) {
            answer[i] = aList.get(i);
        }
        
        return answer;
    }
}