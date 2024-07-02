package daily_0702;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1932_정수삼각형 {
	static int n;
	static int [][]map;
	static int [][]dp;
	/**
	 * 주의점
	 * 1. dp를 일차원 배열로 받으면 위치(바로 위, 왼쪽 대각선 위)규칙을 찾기 어렵다.
	 * 2. dp를 굳이 String 타입으로 받거나, 모양을 맞출 필요 없다. 
	 * 3. '아래층으로 내려올 때, 아래층 수는 현재 층의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택'
	 *    이라는 지문을 
	 *    => '아래층 수는 대각선 오른쪽 아래 또는 바로 아래에 있는 것 중에서만 선택'으로 생각해야함 
	 * 4. 이것을 다시 거꾸로 생각해서
	 * 	  => '바로 위/ 왼쪽 대각선 위 중 큰 것을 선택'한다고 생각해야함
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int [n+1][n+1];
		dp = new int[n+1][n+1];
		for(int i = 1; i <n+1; i++) {
			for(int j = 1; j <i+1; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 1. 입력 끝
		
		// 1-1. map 입력 확인
//		for(int i = 0; i <n+1; i++) {
//			for(int j = 0; j <n+1; j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		}
		
		
		// 2. 점화식
		// dp의 일반항 = 바로 위/ 왼쪽 대각선 위 중 큰 것 + map일반항 
//		dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + map[i][j];
		for(int i = 1; i <n+1; i++) {
			for(int j = 1; j <n+1; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) +map[i][j];
			}
		}
		
		// 2-1. dp 확인 
//		System.out.println("dp 확인");
//		for(int i = 0; i <n+1; i++) {
//			for(int j = 0; j <n+1; j++) {
//				System.out.print(dp[i][j]+" ");
//			}System.out.println();
//		}
		
		// 3. 마지막 행에서 가장 큰 값을 출력
		int ans = 0; 
		for(int r = 1; r <n+1; r++) {
			if(ans < dp[n][r]) {
				ans = dp[n][r];
			}
		}
		System.out.println(ans);
	}// main
}
