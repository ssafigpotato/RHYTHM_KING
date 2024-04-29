package week05;

import java.util.Scanner;

public class BOJ11053_가장긴증가하는부분수열 {
	static int N;
	static int []arr; // 숫자 배열
	static int []dp; // 각 숫자에서 수열의 길이 배열
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1. 입력
		N= sc.nextInt();
		arr = new int[N];
		dp = new int[N];
		for(int i = 0; i <N; i++) {
			arr[i] = sc.nextInt();
		}// 입력 끝
		
		// 2. 숫자 배열을 돌면서 수열의 길이 구하기
		for(int i = 0; i <N; i++) {
			// 모든 부분 수열은 길이가 1이상이므로 1로 초기화
			dp[i] = 1;
			// arr[i]의 왼쪽에 있는 수(arr[j])를 비교하며 작은 숫자가 있는지 확인
			for(int j = 0; j <i; j++) {
				// 현재 '나'보다 '작은 수'가 존재하고,
				// '내'가 다음으로 와서 수열을 만들었을 때
				// 기존 수열보다 더 긴 수열이 되는지 확인
				// (내가 다음으로 와서 만든 수열의 길이) > (현재 나의 수열 길이)
		        // 내가 다음으로 와서 만든 수열의 길이 = 해당하는 '작은수'의 수열의 길이 + 1 = dp[j] + 1
		        // 현재 '나'의 수열 길이 = dp[i]
		        // -> dp[j] + 1 > dp[i]
		        if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
		        	// 더 길어지면 나의 수열의 길이(dp[i]) 수정
		            dp[i] = dp[j] + 1;
				}
			}
			
		}
		
		// 3. 만들어진 수열의 길이 중 가장 긴 길이 찾기 
		int max = 0;
		for (int len : dp) {
			if (max < len) {
		    	max = len;
			}
		}
	
		// 4. 출력
		System.out.println(max);
		
	}
}
