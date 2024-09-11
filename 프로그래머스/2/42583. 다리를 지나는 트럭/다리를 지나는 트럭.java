import java.util.*;

class Solution {
    
    // 최대 트럭 대수, 최대 무게
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        int time = 0;
        int[] abilities = {weight, bridge_length};  // 적재가능한 무게, 차량 수 기록
        // 대기줄
        Queue<Integer> waiting = new ArrayDeque<>();
        for(int w : truck_weights) {
            waiting.offer(w);
        }
    
        // 다리
        Queue<int[]> bridge = new ArrayDeque<>();
        int count = 0;  // 다 건넌 차량 수
        
        while(count < truck_weights.length) {
            
            time += 1;  // 시간 경과
            // 다리 위에서 한칸 씩 이동
            int size = bridge.size();
            for(int i = 0; i < size; i++) {
                
                // 큐가 비어 있지 않은지 확인
                if (!bridge.isEmpty()) {
                    int w = bridge.peek()[0];       // 무게
                    int remain = bridge.poll()[1];  // 남은 이동 수

                    // 끝에 도달
                    if(remain == 0) {
                        // 다리의 적재가능 무게, 수 다시 복구
                        abilities[0] += w;  
                        abilities[1] += 1;
                        count += 1;
                    }
                    else {
                        bridge.offer(new int[] {w, remain - 1});
                    }
                }
            }
            
            // 탑승할 수 있는지 확인
            if(!waiting.isEmpty()) {
                int nextW = waiting.peek();
                if(abilities[0] >= nextW && abilities[1] > 0) {

                    waiting.poll();
                    bridge.offer(new int[] {nextW, bridge_length - 1});
                    abilities[0] -= nextW;
                    abilities[1] -= 1;
                }
            }
            
            // System.out.println("경과시간: " + time);
            // System.out.print("다리: ");
            // for(int[] info : bridge) {
            //     System.out.print(Arrays.toString(info));
            // }
            // System.out.println("\n대기줄: " + waiting);
        }
        
        return time;
    }
}