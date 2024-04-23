package sticker;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); //테스트 케이스
		
		for(int t = 0; t< T; t++) {
			
			int N = sc.nextInt(); // 열의 수
			int[][] stickers = new int[2][N]; // 스티커의 점수를 담을 배열
			int[][] dp = new int[2][N]; // 최대 점수 배열을 담을  dp 2차원 배열
			
			for (int r=0; r<2; r++) {  // 스티커 점수 입력받기
				for(int c =0; c<N; c++) {
					stickers[r][c] = sc.nextInt();
				}
			}
			
			// 1열과 2열의 최댓값은 정해져있다.
			// - 1열은 본인 자신
			dp[0][0] = stickers[0][0];
			dp[1][0] = stickers[1][0];
			
			// - 2열은 본인과 왼쪽 대각선
			if(N>1) {
				dp[0][1] = stickers[0][1] + stickers[1][0];
				dp[1][1] = stickers[1][1] + stickers[0][0];
			}
			
			// 3열(index: 2)부터 끝까지 dp 계산
			// dp 1행과 2행을 따로 계산한다
			// dp[i][j] 는 우선 자기자신인 stickers[i][j] 를 더하고, 
			// 바로 전 대각선의 값을 더하던지, 전전 대각선의 값을 더하던지 (2중 택1)
			// (전전전은 고려하지 않는게, 전전전은 전의 값을 더하면 그것이 최대...!)
			if(N>2) {
				for(int i = 2; i<N; i++) {
					// 1행
					dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
					// 2행
					dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
				}
			}
			
			int score = Math.max(dp[0][N-1], dp[1][N-1]);
			
			System.out.println(score);
		}
		
	}

}
