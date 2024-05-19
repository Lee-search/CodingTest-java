import java.util.*;

class Solution {

    char[] ch = {'A', 'E', 'I', 'O', 'U'};
    List<String> lines = new ArrayList<>();
    
    public int solution(String word) {
        
        dfs(0, "");
        
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).equals(word)) {
                return i + 1;
            }
        }
        
        return -1;
    }
    
    void dfs(int depth, String line) {
        
        if(depth == 5) {
            return;
        }
        
        for(int i = 0; i < ch.length; i++) {
            lines.add(line + ch[i]);       
            dfs(depth + 1, line + ch[i]);
        }
    }
}