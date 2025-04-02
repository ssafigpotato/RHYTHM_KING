package PRO389480;

import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        int INF = 1000000; // 충분히 큰 값으로 초기화

        int[][] dp = new int[itemCount + 1][n]; // A의 흔적을 최소화하는 DP 배열
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0; // 초기 상태 (아무것도 훔치지 않음)

        for (int i = 0; i < itemCount; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int a = 0; a < n; a++) {
                if (dp[i][a] == INF) continue; // 불가능한 상태는 스킵

                // A 도둑이 현재 물건을 훔치는 경우
                int newA = a + aTrace;
                if (newA < n) dp[i + 1][newA] = Math.min(dp[i + 1][newA], dp[i][a]);

                // B 도둑이 현재 물건을 훔치는 경우 (B의 흔적이 m 미만이어야 함)
                int newB = dp[i][a] + bTrace;
                if (newB < m) dp[i + 1][a] = Math.min(dp[i + 1][a], newB);
            }
        }

        int result = INF;
        for (int a = 0; a < n; a++) {
            result = Math.min(result, dp[itemCount][a]);
        }

        return result == INF ? -1 : result;
    }
}