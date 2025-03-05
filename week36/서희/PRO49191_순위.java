class Solution {
    public int solution(int n, int[][] results) {
        int[][] floyd = new int [n+1][n+1];   
        
        // 승패결과 반영
        for(int[] result : results) {
            floyd[result[0]][result[1]] = 1;
            floyd[result[1]][result[0]] = -1;
        }
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    // i가 k를 이기고 k가 j를 이겼다면, i는 j를 이김
                    if(floyd[i][k] == 1 && floyd[k][j] == 1) {
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    
                    // i가 k에게 지고 k가 j에게 졌다면, i는 k에게 짐
                    if(floyd[i][k] == -1 && floyd[k][j] == -1) {
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }
        
        int answer = 0;
        
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) {
                if(floyd[i][j] != 0) cnt++;
            }
            
            // 각 선수 별로 n-1개의 승패를 알고있다면 순위를 확정할 수 있음
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
}
