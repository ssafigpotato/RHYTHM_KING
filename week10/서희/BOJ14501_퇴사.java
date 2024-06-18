package 서희;

import java.util.Scanner;

public class BOJ14501_퇴사 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();		// 남은 기간 (N+1일째 되는 날 퇴사)
		int[] tArr = new int [n];	// 걸리는 기간
		int[] pArr = new int [n];	// 받을 수 있는 금액
		
		// 입력
		for(int i=0; i<n; i++) {
			tArr[i] = sc.nextInt();
			pArr[i] = sc.nextInt();
		}
		
		int[] dp = new int [n+1] ;	// dp배열 
		
		for(int i=0; i<n; i++) {
			// '현재날짜 + 걸리는 기간 <= 전체 기간' 이라면 해당 날자에 번 돈을 계산 
			if(i+tArr[i] <= n) {
				dp[i+tArr[i]] = Math.max(dp[i+tArr[i]], dp[i]+pArr[i]);
			}

			// 현재까지의 값(dp[i])과 dp[i+1]의 값을 비교해서 최댓값으로 갱신
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
		
		System.out.println(dp[n]);
		
		sc.close();
		
	}

}
