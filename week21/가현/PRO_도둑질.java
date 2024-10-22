class Solution {
    public int solution(int[] money) {
        
        // 원형 주의
        // 1) 첫 번째 집을 터는 경우: 마지막 집을 털지 못함
        // 2) 첫 번째 집을 털지 않는 경우: 마지막 집을 털 수 있음
        
        int len = money.length;
        int[] dp1 = new int[len]; // (1) 첫 집 털었음
        int[] dp2 = new int[len]; // (2) 마지막 집 털었음
        
        // 초기 dp 세팅 (1번, 2번 까지의 최대)
        dp1[0] = money[0];
        dp1[1] = money[0];
        
        dp2[0] = 0;
        dp2[1] = money[1];
        
        // 지금 인덱스를 a라고 할 때,
        // a-1 까지의 합 VS. a-2까지의 합+지금 집
        for(int i=2; i<len; i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]);
        }
        
        // dp1의 경우 어차피 마지막 집 못 털음 -> 마지막 전 집인 len-2의 인덱스를 꺼내옴
        return Math.max(dp1[len-2], dp2[len-1]);
        
    }
} 