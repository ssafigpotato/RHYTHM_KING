package BOJ2156_포도주시식;

import java.util.Scanner;

public class Main {
 
	public static void main(String[] args) {
 
		Scanner sc = new Scanner(System.in);
 
		int N = sc.nextInt();
 
		// wine: 각 잔에 들어있는 포도주의 양
		int[] wine = new int[N + 1];
		// dp: 해당 인덱스까지 마실 수 있는 최대 값
		int[] dp = new int[N + 1];
 
		// 포도주를 입력받는다
		for (int i = 1; i <= N; i++) {
			wine[i] = sc.nextInt();
		}
 
		// 첫번쨰 잔에서 최댓값은 '첫번째 와인의 양'
		dp[1] = wine[1];
        // 주어진 와인의 수가 : 한 잔인 경우가 존재할 수도 있음 -> 에러 발생 가능성
		// 위의 이유로 넣었으나, 실제로 if(N-1)이라는 조건을 빼고 제출해보니 -> 런타임에러
		if (N > 1) {
			dp[2] = wine[1] + wine[2];
		}
		
		/*
		 * 예를 들어 와인이 1, 2, 3, 4 총 네잔이 있다고 하자.
		 * 4번째 와인까지 최댓값을 구하기 위해서는
		 * -4번째(=마지막(현재)인덱스)를 마시는 경우에는: 
		 * 1+2+4 가 더 클지, 1+3+4가 클지
		 * - 그 전인 3번째 까지 마셨던 값(그 중 최댓값)이 클지
		 * 
		 * 현재 인덱스가 i라면
		 * Math.max를 통해[{i까지의 dp값}, {(i+(i-1)+(i-3)), (i+(i-2)+(i-3))}]
		 */
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
 
		}
        
		System.out.println(dp[N]);
	}
 
}
