import java.util.*;

class Solution {
    
    boolean solution(String s) {
        
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            
            char ch = s.charAt(i);
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