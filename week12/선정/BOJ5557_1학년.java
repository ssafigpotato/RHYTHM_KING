package daily_0703;

import java.util.Scanner;

public class BOJ5557_1학년 {
	static int N;	// 숫자의 개수
	static int []num; // N개의 숫자 배열(0~9이하 정수)
	static long [][]dp;
	/**
	 * 조건: 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하
	 * 		+ - 들어갈 수 있음. 
	 * 		연산 결과가 마지막 수가 나와야 함
	 * 		하나씩 더하거나 빼면서 중간 수가 0이상 20이하인지 체크
	 * dp[a][b]: a번째 수를 +나 -를 넣어서 b로 만들 수 있는 경우의 수  
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		dp = new long[N-1][21];
		// N-1인 이유: 0부터 N-2까지 총 N-1개의 수를 넣고 뺴고 연산 하면서 검사해야함
		// 21인 이유: 결과로 0~20 사이의 수를 만들어야 함
		for(int i = 0; i <N; i++) {
			num[i] = sc.nextInt();
		}// 1. 입력 끝
		
		// 2. 초항
		dp[0][num[0]] = 1;
		// 3. 일반항
		for(int i = 1; i <N-1; i++) { // N-1번 검사
			for(int j = 0; j <=20; j++) {
				if(j-num[i] >= 0) { // 뺐을 때 음수가 되면 안됨
					dp[i][j-num[i]] += dp[i-1][j];
				}
				if(j+num[i] <= 20) { // 더했을 때 20이 넘으면 안됨
					dp[i][j+num[i]] += dp[i-1][j];
				}
			}
		}
		// 확인 
//		for(int i = 0; i <N-1; i++) {
//			for(int j = 0; j <21; j++) {
//				System.out.printf("%5d", dp[i][j]);
//			}System.out.println();
//		}
		
		
		System.out.println(dp[N-2][num[N-1]]);

		
	}//main
}
