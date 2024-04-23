package 서희;

import java.util.Scanner;

public class BOJ12865_평범한배낭 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int n = sc.nextInt();					// 물품의 수
		int k = sc.nextInt();					// 준서가 버틸 수 있는 무게
		int[][] dp = new int [n+1][k+1] ;		// dp 테이블
		
		int[] weightArr = new int [n+1] ;		// 물건의 무게 배열
		int[] valueArr = new int [n+1] ;		// 물건의 가치 배열
		
		// 물건의 무게와 가치 입력
		for(int i=1; i<=n; i++) {
			weightArr[i] = sc.nextInt();
			valueArr[i] = sc.nextInt();
		}
		
		// 고려하는 물건의 범위
		for(int i=1; i<=n; i++) {
			// 수용 가능한 무게 범위
			for(int j=1; j<=k; j++) {
				// 초기값은 이전 물건의 범위에서 구한 값으로 설정
				dp[i][j] = dp[i-1][j] ;
				
				// 새로 추가된 물건의 값이 j보다 큰 경우
				if(j-weightArr[i] >= 0) {
					// 초기값과 새로 추가된 물건의 가치를 더한 경우를 비교
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weightArr[i]]+valueArr[i]) ;
				}
			}
		}
		
		System.out.println(dp[n][k]);
		
		sc.close();
		
	}

}
