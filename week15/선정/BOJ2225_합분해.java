package algo_0723;

import java.util.Scanner;

public class BOJ2225_합분해 {
	static int N, K;
	static int [][]dp;
	// 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		dp = new int[K+1][N+1];
		
		// dp[K][N] = dp[K-1][0] + dp[K-1][1] + … + dp[K-1][K]
		// => dp[K][N] = dp[K][N-1] + dp[K-1][N]
		
		// N = 0일 때는 될 수 있는 K가 0 하나뿐이므로 1로 초기화 
		for(int i = 1; i <K+1; i++) {
			dp[i][0] = 1;
		}
		
		
		for(int i = 1; i <K+1; i++) {
			for(int j = 1; j <N+1; j++) {
				dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000 ;
			}
		}
		
		// 주의! 
		// dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000000 ; 여기에 바로 %해주기
		
		// dp[i][j] = (dp[i][j-1] + dp[i-1][j]) 
		// System.out.println(dp[K][N] % 1000000000); 이렇게 하면 틀림
		// 중간 계산 과정에서 수가 매우 커져서 int 자료형의 범위를 초과
		// 모듈러 연산을 중간 계산마다 해주어야 메모리 오버플로우를 방지할 수 있기 때문
		
		System.out.println(dp[K][N] );
		
		
	}
}
