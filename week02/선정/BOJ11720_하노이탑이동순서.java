package week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ11720_하노이탑이동순서 {
	static int N;
	static StringBuilder sb;
	/**
	 * 하노이 함수는 3단계가 필요
	 * N=3일 경우
	 * 1) A에서 C를 거쳐 B에 1,2번 원반 옮기기 (n-1개)
	 * 2) 3번원반을 C에 옮기기
	 * 3) B의 1,2번 원반을 A를 걸쳐 C로 옮기기 
	 * */
	static void hanoi(int N, int A, int B, int C) { // 원판 개수, 시작지점, 중간지점, 도착지점
		if(N==0) return;
		// 1. 원판N-1이 A에서 C를 거쳐 B로 이동
		hanoi(N-1, A, C, B);
		// 2. 원판 N이 A에서 C로 이동
//		System.out.println(N+" : "+A+"->"+C);
//		System.out.println(A+" "+C);
		sb.append(A + " " + C + "\n");
		// 3. 원판N-1이 B에서 A를 거쳐 C로 이동
		hanoi(N-1, B, A, C);
	
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sb = new StringBuilder();
		
		// 최소 이동횟수는 2^N-1
		sb.append((int) (Math.pow(2, N)-1)).append('\n');
		hanoi(N,1,2,3);
		System.out.println(sb);
	
		
		
		
	}
}
