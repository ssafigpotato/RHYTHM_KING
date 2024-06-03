package free2;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 남은 N일
		
		// 인덱스 0은 안쓸거예용
		int[] time = new int[N+1]; // 상담에 걸리는 시간
		int[] money = new int[N+1]; // 상담 후 받는 금액
		
		int[] dp = new int[N+2]; 
		// dp[i] 는 i번째 날 얻을 수 있는 최대 이익
		// [N+1]이 아닌 이유: 상담이 끝나는 날이 N+1일 때 배열 범위 초과
		
		// 입력받기 
		for(int i = 1; i<=N ; i++) {
			time[i] = sc.nextInt();
			money[i] = sc.nextInt();
		}
		
		
		//  DP ㄱ
		for(int i = 1; i<=N; i++) {
			
			// 자동으로 i번째 날의 이익을 다음(i+1) 날의 이익으로 갱신 (=상담을 진행하지 않은 경우)
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			
			// i+time[i] : 상담을 시작한 날 (i) 와 해당 상담을 완료하는 데 걸리는 날 (time[i])를 더한 값 => 상담이 끝나는 날이다
			// <= N+1 : 현재 상담이 주어진 기간 내에 끝나는 경우
			if(i+time[i] <= N+1) {
				dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i] + money[i]);
				// 내가 잘 몰랐던 부분;
				// dp[i+time[i]] 가 등호(=) 양 사이드 2번 나타나는 이유?
				// 주어진 조건에 따르면 i번째 날 상담이 끝나는 경우가 여러번 나타날 수 있음
				/*
				 * 예를 들면,
				 * time = [0, 3, 2, 1, 1, 2]
				 * money = [0, 10, 20, 10, 20, 30]
				 * 
				 * i = 1일 때:
				 * t[1] = 3이고 p[1] = 10
				 * 상담이 끝나는 날은 1 + 3 = 4
				 * dp[4] = Math.max(dp[4], dp[1] + 10)
				 * 
				 * i = 2일 때:
				 * t[2] = 2이고 p[2] = 20
				 * 상담이 끝나는 날은 2 + 2 = 4
				 * dp[4] = Math.max(dp[4], dp[2] + 20)

				 */
			}

		}
		
		// dp중 최댓값 구하기
		int maxProfit = 0;
		for(int i =0; i<= N+1; i++) {
			maxProfit = Math.max(maxProfit, dp[i]);
		}
		
		System.out.println(maxProfit);
	}

}
