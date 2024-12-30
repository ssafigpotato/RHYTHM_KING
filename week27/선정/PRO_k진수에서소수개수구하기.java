import java.util.*;

class Solution {
    public boolean isPrime(long num) {
        // 1) 0, 1은 소수가 아님 
        if (num < 2) return false;
        // 2) 2는 소수 
        if (num == 2) return true;

        // 3) 2부터 제곱근 num까지 돌면서 num이 해당 수로 나누어 떨어지면
        // 소수가 아님 
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
    public int solution(int n, int k) {
        // 1. n을 K진수로 변환 (String으로 받아야함)
        String str = Integer.toString(n, k);
        // System.out.println(n+" "+num);
        
        // 2. 0을 기준으로 split 
        String[] arr = str.split("0");
        // System.out.println(Arrays.toString(arr));
        
        // 3. 조건에 맞는지 체크 
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            // 3-1. 빈 문자열일 경우 continue
            // 이걸 하지 않으면 Exception in thread "main" java.lang.NumberFormatException: For input string: ""  오류 발생
            if(arr[i].equals("")) continue;
            
            // 3-2. String arr[i]을 int로 변경 
            // 이렇게 하면 int범위 초과로 런타임에러 발생! (1번,11번 테캐)
            // int num = Integer.parseInt(arr[i]);
            // 따라서 long타입으로 변경
            long num = Long.parseLong(arr[i]);
            
            // 3-3. 해당 수가 소수이면 ans++
            if(isPrime(num)) ans++;
        }
        
        
    
       
        return ans;
    }
}// class