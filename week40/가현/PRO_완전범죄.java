import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        
        // 모든 물건을 B가 훔쳤을 때의 총 흔적 계산
        int totalBTraces = 0;
        for (int[] item : info) {
            totalBTraces += item[1];
        }
        
        // 빠른 반환: 모든 물건을 B가 훔쳐도 안전하다면 A는 0개 흔적
        if (totalBTraces < m) {
            return 0;
        }
        
        // DP[i][j] = i번째 물건까지 고려했을 때, B가 j개의 흔적을 가질 경우의 A의 최소 흔적
        int[][] dp = new int[itemCount + 1][totalBTraces + 1];
        
        // 초기화: 불가능한 상태는 큰 값으로 설정
        for (int i = 0; i <= itemCount; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2); // 오버플로우 방지를 위해 /2
        }
        
        // 초기 상태: 아무 물건도 고려하지 않았을 때 흔적은 0
        dp[0][0] = 0;
        
        // 모든 물건에 대해 처리
        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0]; // A가 i번째 물건을 훔칠 때 남기는 흔적
            int bTrace = info[i][1]; // B가 i번째 물건을 훔칠 때 남기는 흔적
            
            // 가능한 모든 B의 흔적 상태에 대해
            for (int j = 0; j <= totalBTraces; j++) {
                // 현재 상태가 유효한 경우에만 계산
                if (dp[i][j] < Integer.MAX_VALUE / 2) {
                    // 선택 1: A가 물건을 훔치는 경우
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + aTrace);
                    
                    // 선택 2: B가 물건을 훔치는 경우
                    if (j + bTrace <= totalBTraces) {
                        dp[i+1][j + bTrace] = Math.min(dp[i+1][j + bTrace], dp[i][j]);
                    }
                }
            }
        }
        
        // 가능한 모든 B의 흔적 상태 중 A의 최소 흔적 찾기
        int minATraces = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            if (dp[itemCount][j] < n) { // A가 안전한 경우만 고려
                minATraces = Math.min(minATraces, dp[itemCount][j]);
            }
        }
        
        return minATraces == Integer.MAX_VALUE ? -1 : minATraces;
    }
}