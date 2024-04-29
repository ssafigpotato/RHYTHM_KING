package week04;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BOJ2565_전깃줄2 {
	static int N; // 두 전봇대 사이의 전깃줄의 개수
	static int [][] loc; // 연결된 전깃줄의 위치 
	static Integer []dp;
	static int LISrecur(int N) {
		// 탐색하지 않았던 위치라면
		if(dp[N] == null) {
			dp[N] = 1; // 최솟값 1로 초기화 
			
			//A의 N번째와 이후의 전봇대 비교
			for(int i = N+1; i <dp.length; i++) {
				/*
				 *  A전봇대의 N번째 전선이 연결되어있는 B전봇대보다 A의 i번째
				 *  전봇대의 전선이 이어진 B전봇대가 뒤에 있을 경우 
				 *  전선을 설치할 수 있음 
				 */
				if(loc[N][1] < loc[i][1]) {
					// 연결 가능한 전선의 경우의 수 중 큰 값을 dp에 저장한다.
					dp[N] = Math.max(dp[N], LISrecur(i) + 1);
				}
				
			}
			
		}
		return dp[N];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 0. 입력받기 
		N = sc.nextInt();
		loc = new int[N][2];
		dp = new Integer[N];
		
		for(int i = 0; i <N; i++) { // N번 입력받기
			loc[i][0] = sc.nextInt();
			loc[i][1] = sc.nextInt();
		}// 입력 끝 
		
		// 정렬 전 확인
//		System.out.println("정렬 전 전깃줄의 위치=======");
//		for(int i = 0; i <N; i++) {
//			for(int j = 0; j <2; j++) {
//				System.out.print(loc[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 1. A전봇대를 기준으로 오름차순 정렬
		// 참고 사이트) https://charliezip.tistory.com/2
		// 1-1. 람다 함수로 정렬
//		Arrays.sort(loc, (o1, o2) -> o1[0] - o2[0]);
		
		// 1-2. Comparator로 정렬 
		// Comparator<int[]>를 구현하는 익명클래스 생성 후 사용
		// 배열의 각 첫 번째 요소를 비교해서
		// 반환값이 음수인 경우 o1 < o2이므로 그대로
		// 반환값이 양수인 경우 o1 > o2이므로 두 원소의 위치를 바꿈
		Arrays.sort(loc, new Comparator<int[]>(){
			@Override
			public int compare(int[]o1, int[]o2) {
				return o1[0]- o2[0];
			}
		});
	
//		System.out.println("정렬 후 전깃줄 위치======= ");
//		for(int i = 0; i <N; i++) {
//			for(int j = 0; j <2; j++) {
//				System.out.print(loc[i][j]+" ");
//			}
//			System.out.println();
//		}
	
		// 2. LIS - TOP-down 방식
		int max = 0;
		/*
		 *  i번째 A전봇대를 기준으로 연결가능한 개수 탐색
		 *  및 최댓값 찾기
		 */
		for(int i = 0; i < N; i++) {
			max = Math.max(LISrecur(i), max);
		}
		
		// 전선 개수 - 최댓값 
		System.out.println(N - max);
	
	}// main
}
