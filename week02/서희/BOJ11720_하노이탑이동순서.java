package 서희;

import java.util.Scanner;

public class BOJ11720_하노이탑이동순서 {
	
	static StringBuilder sb ;
	static int cnt ;			// 이동횟수
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		sb = new StringBuilder() ;
		
		int N = sc.nextInt();
		cnt = 0 ;
		
		hanoi(N, 1, 2, 3) ;
		
		System.out.println(cnt);
		System.out.println(sb);
		
		sc.close();
		
	}

	private static void hanoi(int n, int start, int mid, int to) {
		cnt++ ;		// 메소드가 1번 실행될 때마다 이동횟수 1 증가
		
		// n=1이면 1에서 3으로 이동
		if(n == 1) {
			sb.append(start+" "+to+"\n") ;
			return ;
		}
		
		// n-1을 1에서 2로 이동
		hanoi(n-1, start, to, mid) ;
		
		// n을 1에서 3으로 이동
		sb.append(start+" "+to+"\n") ;
		
		// n-1을 2에서 3으로 이동
		hanoi(n-1, mid, start, to) ;
	}

}
