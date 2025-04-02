import java.util.*;

class Solution {
    
    static int[][] info;
    static int n;
    static int m;
    
    static int[][] dp;
    
    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        
        // 1. dp 초기화
        init();
        
        // 2. 도둑들이 물건을 훔칠 때 남기는 흔적 계산
        steal();
        
        // 3. A도둑이 남긴 흔적의 누적 개수의 최솟값 계산
        return answer();
    }
    
    /**
        DP 배열 초기화
    */
    public void init() {
        dp = new int [info.length + 1][m];
        
        for(int i=1; i<=info.length; i++) {
            Arrays.fill(dp[i], n);
        }
        
        dp[0][0] = 0;
    }
    
    /**
        도둑들이 물건을 훔칠 때 남기는 흔적 계산
    */
    public void steal() {
        for(int i=1; i<=info.length; i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0; j<m; j++) {
                // A도둑이 물건을 훔치는 경우
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                // B도둑이 물건을 훔치는 경우
                if(j+b < m) {
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
    }
    
    /**
        A도둑이 남긴 흔적의 누적 개수의 최솟값 계산
    */
    public int answer() {
        int min = n;
        
        for(int i=0; i<m; i++) {
            min = Math.min(min, dp[info.length][i]);
        }
        
        return min >= n ? -1 : min;
    }
}