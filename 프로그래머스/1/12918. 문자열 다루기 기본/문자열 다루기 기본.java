class Solution {
    
    public boolean solution(String s) {
        
        char[] chArr = s.toCharArray();
        
        if(chArr.length != 4 && chArr.length != 6) return false;
        
        for(char ch : chArr) {
            if(ch >= '0' && ch <= '9') continue;
            else return false;
        }
        
        return true;
    }
}