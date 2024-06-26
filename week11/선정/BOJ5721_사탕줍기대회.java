package algo_0626;

import java.util.Scanner;

public class BOJ5721_사탕줍기대회 {
	static int M,N;
	static int []dp; // 각 행에서 선택할 수 있는 최대 사탕 개수 저장
	static int []num; // 사탕 개수를 저장
	/**
	 * (2,2)에서 사탕 10개를 줍는 경우 
	 * => 같은 행인 1(2,0), 10(2,4)는 먹을 수 있음
	 * 따라서 각 행에서 최대한 많이 사탕을 줍도록 해야함
	 * 어떤 행을 선택했다면 다음을 걸러서 선택할 수 있으므로 
	 * 점화식은 다음과 같음 
	 * dp[i] = Math.max(dp[i - 2] + dp[i], dp[i - 1])
	 * 
	 * 1. 각 행에서 최대 사탕 개수 계산
	 * 예) 첫 번째 행: [1, 8, 2, 1, 9]
	 * num[1] = 1
	 * num[2] = 8
	 * num[3] = max(1+2, 8) = 8
	 * num[4] = max(8+1, 8) = 9
	 * num[5] = max(8+9, 9) = 17
	 * 결과: dp[1] = 17
	 * ... 이런식으로 dp[5]까지 구함
	 * 
	 * 2. 행 단위 최대 사탕 개수 계산
	 * 첫 번째 행 (dp[1] = 17)
	 * 두 번째 행 (dp[2] = 12)
	 * ...
	 * dp[5] = max(dp[3] + dp[5], dp[4]) = max(38 + 16, 38) = 54
	 * 최종적으로 최대 사탕 개수는 54
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
  		while (true) {

  			M = sc.nextInt();
  			N = sc.nextInt();

  			if (M == 0 && N == 0) break;

  			// 각 행에서 선택할 수 있는 최대 사탕 개수 저장
  			dp = new int[M + 1];
  			
  			for (int i = 1; i <= M; i++) {
  				// 사탕 개수를 저장
  				num = new int[N + 1];
  				
  				for (int j = 1; j <= N; j++) {
  					num[j] = sc.nextInt();
  					
  					if (j >= 2) {
  						// j-2일 때와, j-1일 때 중 큰 값을 저장 
  						num[j] = Math.max(num[j - 2] + num[j], num[j - 1]);
  					}
  				}
  				// 해당 행에서 최대 개수 저장 
  				dp[i] = num[N];

  				// 행 선택
  				if (i >= 2) {
  					// 최대값 중 i-2일 때와 i-1일 때 중 큰 값을 저장 
  					dp[i] = Math.max(dp[i - 2] + dp[i], dp[i - 1]);
  				}
  			}
  			System.out.println(dp[M]);
  		}
  		
	}//main
}
