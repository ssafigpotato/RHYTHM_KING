package plumtree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 자두가 떨어지는 시간
        int T = sc.nextInt();
        // 이동 횟수 제한
        int W = sc.nextInt();
        
        // 자두가 떨어지는 위치를 저장하는 배열
        int[] drops = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            drops[i] = sc.nextInt();
        }
        
        // DP 테이블 초기화
        // 인덱스와 위치/시간/횟수 맞추기 위해 +1 씩 해서 생성
        int[][][] dp = new int[3][T + 1][W + 1];
        
        // 초기 상태(=1초) 설정 (자두는 1번 나무에서 시작)
        if (drops[1] == 1) { // 자두가 1번에서 떨어진다면
            dp[1][1][0] = 1; // [1번 위치에서][1초 후에][0번 움직이고] = 1개를 받아 먹는다.
        } else { //자두가 2번에서 떨어진다면
            dp[2][1][1] = 1; // [2번 위치][1초 후][1번 움직이고] = 1개를 받아 먹는다.
        } // 나머지 경우는 어차피 자두를 받아먹지 못하므로 (또는 필요이상으로 많이 움직이므로) 계산할 필요없음. 
        
        
        // DP 진행
        for (int t = 2; t <= T; t++) { // 1초는 이미 계산 -> 2초부터 시작
        	// w <= Math.min(W, t-1) 을 통해 이동횟수가 늘어나는 것을 반영
            for (int w = 0; w <= Math.min(W, t-1); w++) { 
                // 자두가 1번 나무에서 떨어질 때
                if (drops[t] == 1) {
                    // 이동하지 않는 경우 -> 1에 위치한 경우: 같은 장소에서 1초전에 받아먹은 자두의 개수에 +1 해준다.
                    dp[1][t][w] = dp[1][t-1][w] + 1;
                    // 이동하는 경우 : (위의 코드와 같이) 1의 장소에 그대로 있는 경우 vs. 2번 장소에서 옮겨온 경우[2번위치에서][1초전에][이번이동아직카운트x]
                    if (w > 0) {
                        dp[1][t][w] = Math.max(dp[1][t][w], dp[2][t-1][w-1] + 1);
                    }
                    // 2번 나무에 있는 경우 (이동하지 않음) -> 2에 위치한 경우: 같은 장소(2)에서 1초전에 받아먹은 자두의 개수와 동일
                    dp[2][t][w] = dp[2][t-1][w];
                } 
                // 자두가 2번 나무에서 떨어질 때 (방법은 위와 같음)
                else {
                    // 이동하지 않는 경우
                    dp[1][t][w] = dp[1][t-1][w];
                    // 2번 나무에 있는 경우 (이동하지 않음)
                    dp[2][t][w] = dp[2][t-1][w] + 1;
                    // 이동하는 경우
                    if (w > 0) {
                        dp[2][t][w] = Math.max(dp[2][t][w], dp[1][t-1][w-1] + 1);
                    }
                }
            }
        }
        
        // 최대 자두 개수 찾기
        int maxPlums = 0;
        for (int w = 0; w <= W; w++) {
//            maxPlums = Math.max(maxPlums, dp[1][T][w]);
//            maxPlums = Math.max(maxPlums, dp[2][T][w]);
            maxPlums = Math.max(maxPlums, Math.max(dp[1][T][w], dp[2][T][w]));
        }
        
        // 결과 출력
        System.out.println(maxPlums);
        
        sc.close();
    }
}
