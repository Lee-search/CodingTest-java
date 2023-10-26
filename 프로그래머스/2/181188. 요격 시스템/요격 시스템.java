import java.util.*;

class Solution {
    
    class Missile implements Comparable<Missile> {
    int s, e;
    
    public Missile(int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Missile other) {
        if(this.e == other.e) return Integer.compare(this.s, other.s);
        else return Integer.compare(this.e, other.e);
    }
    
    @Override
    public String toString() {
        return "s: " + s + ", e: " + e;
    }
} // end of class
    
    public int solution(int[][] targets) {
        
        int answer = 1;
        int N = targets.length;
        Missile[] list = new Missile[N];
        
        // targets -> 미사일 객체로 변경
        for(int i = 0; i < N; i++) {
            list[i] = new Missile(targets[i][0], targets[i][1]);
        }
        
        Arrays.sort(list);
        
        // 초기 요격 미사일은 마지막 미사일을 무조건 명중시킨다.
        Missile m = new Missile(list[N - 1].s, list[N - 1].e);
        for(int i = N - 2; i >= 0; i--) {
            Missile cur = list[i];
            // 요격 미사일 범위 안에 있는 경우
            if(m.s < cur.e) {
                // 미사일 조정
                if(m.s < cur.s) m.s = cur.s;
                if(cur.e < m.e) m.e = cur.e;
            }
            // 새로운 미사일 편성
            else {
                answer += 1;
                m = new Missile(list[i].s, list[i].e);
            }
        }
        
        return answer;
    } // end of solution
}