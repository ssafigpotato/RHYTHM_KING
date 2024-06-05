package 서희;

import java.util.Scanner;

public class BOJ14889_스타트와링크 {
	
	static int n ;			// 총 인원수
	static int[][] arr ;	// 능력치 배열
	static int[] start ;	// 스타트팀에 속한 사람들의 번호를 저장하는 배열
	static int[] link ;		// 링크팀에 속한 사람들의 번호를 저장하는 배열
	static int min ;		// 스타트팀과 링크팀의 능력치 차이의 최솟값
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		n = sc.nextInt() ;
		arr = new int [n][n] ;
		start = new int [n/2] ;
		link = new int [n/2] ;
		min = Integer.MAX_VALUE ;
		
		// 능력치 입력
		for(int p1=0; p1<n; p1++) {
			for(int p2=0; p2<n; p2++) {
				arr[p1][p2] = sc.nextInt();
			}
		}
		
		combi(0, 0, 0) ;
		
		System.out.println(min);
		
		sc.close();
		
	}

	/**
	 * n명의 사람들을 팀배정하는 메소드
	 * @param idx : 이번 차례에 팀배정되는 번호
	 * @param curStart : 현재까지의 스타트팀 인원
	 * @param curLink : 현재까지의 링크팀 인원
	 */
	private static void combi(int idx, int curStart, int curLink) {
		// n명의 사람들의 팀 배정이 모두 끝나면 팀별 능력치 계산
		if(idx >= n) {
			calAbility() ;
			return ;
		}
		
		// 스타트팀에 포함
		if(curStart < n/2) {
			start[curStart] = idx ;
			combi(idx+1, curStart+1, curLink) ;
		}
		
		// 링크팀에 포함
		if(curLink < n/2) {
			link[curLink] = idx ;
			combi(idx+1, curStart, curLink+1) ;
		}
	}

	/**
	 * 팀별 능력치를 계산하고, 능력치 차이의 최솟값을 갱신하는 메소드
	 */
	private static void calAbility() {
		int startSum = 0 ;
		int linkSum = 0;
		
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<n/2; j++) {
				startSum += arr[start[i]][start[j]] ;
				linkSum += arr[link[i]][link[j]] ;
			}
		}
		
		int gap = Math.abs(startSum - linkSum) ;
		min = (gap < min) ? gap : min ;
		
	}

}
