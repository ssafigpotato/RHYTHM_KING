package freshman;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 숫자의 개수
        int[] numbers = new int[N]; // 숫자 입력받기
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

	    // 상근이는 20이 넘는 수를 모른다
	    long[][] dp = new long[N][21]; 
	    // 첫번째: 본인을 더한 경우 1가지만 존재
	    dp[0][numbers[0]] = 1;
	
	    // n번째 숫자를 더하거나 뺀다
	    for (int i = 1; i < N - 1; i++) {
	    	// (수식) = j가 되는 경우의 수를 구할 것이다.
	        for (int j = 0; j <= 20; j++) {
	        	
	        	// 이전 단계에서 (수식)=j가 0이 아닌 경우 : 경우의 수가 있다!
	            if (dp[i - 1][j] != 0) {
	            	
	            	// 더하기 (20을 초과하면 안돼!)
	                if (j + numbers[i] <= 20) {
	                    dp[i][j + numbers[i]] += dp[i - 1][j];
	                }
	                // 빼기 (0보다 작으면 안돼!)
	                if (j - numbers[i] >= 0) {
	                    dp[i][j - numbers[i]] += dp[i - 1][j];
	                }
	            }
	        }
	    }
	
	    // 마지막 숫자와의 연산 결과가 타겟 값이 되는 경우의 수 출력
	    System.out.println(dp[N - 2][numbers[N - 1]]);
	}
}