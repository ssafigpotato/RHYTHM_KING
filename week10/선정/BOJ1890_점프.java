package algo_0618;

import java.util.Scanner;

public class BOJ1890_점프 {
	static int N; // 게임 판 크기
	static int [][]map; // 게임 판
	static long [][] dp; 
	// 경로의 수가 2^63-1보다 작거나 같으므로 long 타입
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <N; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력 끝
		
		// dp에 이동경로의 수를 저장
		dp = new long[N][N];
		dp[0][0] = 1;

		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <N; j++) {
				int move = map[i][j];
				
				// 움직이는 거리 즉 map[i][j] = 0이면 건너뛰기
				if(move == 0) continue;
				
				if(move + i <N ) {
					dp[move+i][j] += dp[i][j];
				}
				if(move + j <N) {
					dp[i][move+j] += dp[i][j];
				}
			}
		}
	
		// 확인
//		for(int i = 0; i <N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(dp[i][j]+" ");
//			}System.out.println();
//		}
		
		System.out.println(dp[N-1][N-1]);
	
	
	
	}// main
}
