class Solution {
    
    int N, answer = 0;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        N = computers.length;   // 컴퓨터 갯수
        visited = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            
            if(visited[i]) continue;
            
            answer += 1;
            DFS(i, computers);
        }
        
        return answer;
    }
    
    void DFS(int node, int[][] computers) {
        
        visited[node] = true;
        for(int i = 0; i < N; i++) {
            
            // 자기자신 or 이미 네트워크를 통해 연결된 경우
            if(i == node || visited[i]) continue;
            // computers 에 연결관계가 정의되지 않은 경우
            if(computers[node][i] == 0) continue;
            
            DFS(i, computers);
        }
    }
}