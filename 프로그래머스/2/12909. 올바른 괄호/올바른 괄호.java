import java.util.*;

class Solution {
    
    boolean solution(String s) {
        
        Queue<Character> q = new ArrayDeque<>();
        
        for(int i = 0; i < s.length(); i++) {
            q.offer(s.charAt(i));
        }
        
        int count = 0;
        while(!q.isEmpty()) {
            
            char ch = q.poll();
            if(ch == '(') {
                count += 1;
            }
            else {
                count -= 1;
            }
            
            if(count < 0) {
                return false;
            }
        }
        
        if(count != 0) {
            return false;
        }
        
        return true;
    }
}