package boj_18291_비요뜨의징검다리건너기;

import java.util.Scanner;

public class Main {
	
	static int n;
	static long m = 1000000007;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();		// 테스트 케이스의 수
		
		for(int t=1; t<=T; t++) {
			n = sc.nextInt();
			
			long answer = (n == 1) ? 1 : divide(n-2);
			System.out.println(answer);
		}
		
		sc.close();
		
	}
	
	public static long divide(int num) {
		if(num == 0) return 1;
		long temp = divide(num / 2);
		return temp * temp * (num % 2 + 1) % m ;
	}

}
