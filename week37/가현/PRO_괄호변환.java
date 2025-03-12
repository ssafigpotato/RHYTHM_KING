class Solution {
    public String solution(String p) {
        
        // 1. 빈 문자열인 경우,
         if(p.isEmpty()){
             return "";
         }
        
        //2. 문자열 분리(u,v)
        int balance = 0; // () 개수를 세는 변수
        int index = 0; // u의 끝 위치를 찾기 위한 변수
        
        for(index=0; index<p.length(); index++){
            if(p.charAt(index) == '(') {
                balance++;
            }
            else {
                balance--;
            }
            if(balance==0){ // ),( 개수가 같아지는 순간 u,v를 나누는 위치 결정
                break;
            }
        }
        
        String u = p.substring(0, index+1);
        String v = p.substring(index+1);
        
        
        //3. u가 올바른 괄호 문자열인지 확인
        if(isCorrect(u)){
            return u + solution(v); // 이 soulution은 2번 줄의 solution
        }
        
        // 4. u가 올바른 괄호 문자열이 아니라면 이 과정 수행
        StringBuilder newString = new StringBuilder();
        newString.append('('); //4-1. 빈 문자열에 '(' 추가
        newString.append(solution(v)); //4-2. v를 재귀적으로 변환한 문자열을 추가
        newString.append(')'); //4-3. ')'를 추가
        
        //4-4.u의 첫번째와 마지막 문자를 제거한 후, 나머지 문자의 괄호 방향을 뒤집음
        for(int i=1; i<u.length()-1; i++){
            if(u.charAt(i) == '(') newString.append(')');
            else newString.append('(');  
        }
        
        //4-5. 변환된 문자열 반환
        return newString.toString();       

    }
    
    //올바른 괄호 문자열 확인 함수
    public boolean isCorrect(String s){
        int count = 0; //열린 괄호 개수 추적
        for(char c : s.toCharArray()) {
            if(c=='(') {
                count++;
            }
            else {
                count--;
            }
            if(count<0){
                return false;
            }
        }
        return count==0; //모든 괄호가 짝을 이루면 true 반환
    }
}