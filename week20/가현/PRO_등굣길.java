class Solution {
    
    static int[] dr = {0,-1}; // 오른쪽, 아래 순서
    static int[] dc = {1, 0};
    
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] dp = new int[n][m];
        
        dp[0][0] = 1;
        
        for( int[] puddle : puddles ){
            dp[puddle[1]-1][puddle[0]-1] = -1;
        }
        
        for(int r =0; r<n; r++){
            for(int c=0; c<m; c++){
                
                // 물에 잠긴 곳은 갈 방법이 없음
                if(dp[r][c] == -1 ){
                    dp[r][c] = 0;
                    continue; // !!!물웅덩이 넘어가기 주의
                }
                
                // IndexOutOfRange를 방지 하기 위해 세로/가로를 나누어서 계산
                if(r>0){
                    dp[r][c] += dp[r-1][c];
                    dp[r][c] %= 1000000007;
                } 
                
                if(c>0){
                    dp[r][c] += dp[r][c-1];
                    dp[r][c] %= 1000000007;
                }
                
            }
        }
        
        // 처음에는 여기서 % 연산을 했더니 효율성 테스트를 전부 실패함.
        int answer = dp[n-1][m-1];
        return answer;
    }
}