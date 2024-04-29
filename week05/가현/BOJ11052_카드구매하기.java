package card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); //카드의 개수
		
		// 카드팩의 가격 - 인덱스를 맞춰주기 위해
		int[] cardPack = new int[N+1]; 
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i =1;i<=N; i++) {
			cardPack[i] = Integer.parseInt(st.nextToken());
//			dp[i] = cardPack[i];  //여기서 dp배열 우선 동일하게 초기화시켜도 됨
		}
		
		
		
		// 카드를 위해 지불해야 하는 가격을 계산하는 메서드 - Math.max()
		// 비교해야 할 대상 - 본인(Pi)1팩만 사는 경우 vs 여러 팩 사는 경우
		/*
		 * 여러 팩을 사는 경우: 
		 * a+b=i가 되는 조건에서 a에서의 dp와 b에서의 dp를 더한 값
		*/
		// 우선 dp[i]에 한 팩만 사는 경우의 가격을 담아둔다
		//! 배열 마지막 인덱스는 N+1 이 아니라 N이다
		for(int i=1; i<=N; i++) {
			dp[i] = cardPack[i];
			
			// 잘못 생각했던 경우:
			// 여러 팩 사는 경우 i+j = N 이라 -> 범위를 j<=N-i라고 했지만 
			// 이렇게 하면 인덱스 에러!!!
			
			// 범위 내에 존재하려면: 
			// 인덱스는 0보다 커야 하므로:(i-j)>0  ---> j<i
			// 인덱스는 i보다 작거나 같아야 하므로: (i-j)<=i  ---> j>0 -> j=1부터 시작
			for(int j =1; j<i; j++) {
				// 아래의 2줄 모두 가능
//				dp[i] = Math.max(dp[i],dp[i-j]+dp[j]); 
				dp[i] = Math.max(dp[i], dp[i-j]+cardPack[j]);  
				
			}
		}
		
		System.out.println(dp[N]);
		
		
	}

}
