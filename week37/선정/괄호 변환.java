import java.util.*;
class Solution {
    // 문제의 1234를 충실히 수행하면 된다!!! 
    
    static boolean isRight(String p){ // 올바른 문자열인지 체크 메소드 
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < p.length(); i++){
            if(!st.isEmpty() && st.peek() == '(' && p.charAt(i) == ')'){
                st.pop(); 
                // System.out.println(st);
            }else {
                st.add(p.charAt(i));
                // System.out.println(st);
            }
        }
        return (st.isEmpty()); 
    }
    static String[] split(String p){ // u,v를 분리하는 메소드 
        int l_cnt = 0, r_cnt = 0;
        String[] uv = new String[2];
        
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '(') l_cnt++;
            else r_cnt++;                 
            
            if(l_cnt == r_cnt){ // 처음으로 균형잡힌 순간!! u,v 분리 
                uv = new String[]{p.substring(0,i+1), p.substring(i+1)}; // 0~i까지 u, i+1~끝까지 v
                return uv; 
            }
        }
        
        uv = new String[]{p,""};
        return uv; 
    }
    static String backtracking(String p){
        if(p.isEmpty()) return ""; // 1. 입력이 빈문자열이면 그대로 반환
    
        // 2. u와 v를 나누고 
        String[]uv = split(p);
        String u = uv[0];
        String v = uv[1]; 
        
        // 3. u가 올바른문자열인지 아닌지에 따라서 로직
        if(isRight(u)){ // 올바른 문자열이라면
            return u + backtracking(v); // v 재귀 후 u에 이어붙임
        }else { // 아니라면 
            String str = "";
            str = "(" + backtracking(v) + ")"; // 문제의 4-1~4-3 수행
            
            for(int i = 1; i < u.length()-1; i++){ // 문제의 4-4 수행
                if(u.charAt(i) == '(') str += ')';
                else str += '(';
            }
            
            // System.out.println(str);
            return str; 
        }
    }
    public String solution(String p) {
       
        // System.out.println(isRight(p));
        // System.out.println(Arrays.toString(split(p)));
        
        String answer = "";
        answer = backtracking(p);
        return answer;
        
       
        
    }
}