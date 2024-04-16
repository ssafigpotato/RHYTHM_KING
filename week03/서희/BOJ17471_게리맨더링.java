package 서희;

import java.util.Scanner;

public class BOJ17471_게리맨더링 {

	static int N ;			// 구역의 개수
	static int[] person ;	// 각 구역의 인구수
	static int[][] adj ;	// 인접한 구역을 체크해둔 배열
	
	static int[] arr ;				// 각 구역에 배정된 선거구 저장
	static boolean[] visited ;		// 각 구역 방문체크
	static int min ;				// 두 선거구에 포함된 인구의 차이가 최소인 값
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		N = sc.nextInt();			// 구역의 개수
		person = new int [N+1];		// 각 구역의 인구수
		adj = new int [N+1][N+1];	// 인접한 구역을 체크해둔 배열
		arr = new int [N+1];		// 각 구역에 배정된 선거구 저장
		min = Integer.MAX_VALUE ;	// 두 선거구에 포함된 인구의 차이가 최소인 값
		
		// 각 구역의 인구수 입력
		for(int i=1; i<=N; i++) {
			person[i] = sc.nextInt();
		}
		
		// 인접한 구역 입력
		for(int i=1; i<=N; i++) {
			int n = sc.nextInt();		// 인접한 구역의 수
			for(int j=0; j<n; j++) {
				int area = sc.nextInt() ;
				adj[i][area] = adj[area][i] = 1 ;
			}
		}
		
		// 구역을 2개의 선거구로 나누기
		combi(1) ;
		
		// 두 선거구로 나눌 수 없는 경우
		if(min == Integer.MAX_VALUE) {
			min = -1 ;
		}
		
		System.out.println(min);
		
		sc.close();
		
	}

	/**
	 * 두개의 선거구로 구역을 나누는 메소드
	 */
	private static void combi(int idx) {
		if (idx > N) {
			checkConnect() ;
			return ;
		}
		
		// 해당 idx를 선거구 A에 넣는 경우
		arr[idx] = 1 ;
		combi(idx+1) ;
		
		// 해당 idx를 선거구 B에 넣는 경우
		arr[idx] = 2 ;
		combi(idx+1) ;
		
	}

	/**
	 * 두 선거구가 각각 이어져 있는지 확인하는 메소드
	 */
	private static void checkConnect() {
		visited = new boolean [N+1];	// 방문체크 
		
		
		int aStart = 0 ;	// 선거구 A가 시작하는 구역 번호
		int bStart = 0 ;	// 선거구 B가 시작하는 구역 번호
		
		// 각 선거구가 시작하는 구역번호 계산
		for(int i=1; i<=N; i++) {
			if (aStart == 0 && arr[i] == 1) {
				aStart = i ;
			} else if (bStart == 0 && arr[i] == 2) {
				bStart = i ;
			}
		}
		
		// DFS 진행하며 이어져있는 곳 모두 방문처리
		DFS(aStart, 1) ;
		DFS(bStart, 2) ;
		
		// 모든 곳이 다 방문처리 되어있다면 각 선거구가 연결되어있는 것
		boolean isConnect = true ;
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				isConnect = false ;
			}
		}
		
		// 각 선거구가 연결되어있을 경우 인구 차이 계산
		if(isConnect) {
			cntPerson() ;
		}
		
	}

	private static void DFS(int idx, int typeNum) {
		visited[idx] = true ;
		
		for(int i=1; i<=N; i++) {
			// 해당 선거구에 배정되지 않은 구역이거나 이미 방문한 구역이면 pass
			if (arr[i] != typeNum || visited[i]) continue ;
			
			if(adj[idx][i] == 1) {
				DFS(i, typeNum) ;
			}
		}
	}

	private static void cntPerson() {
		int aCnt = 0;		// 선거구A 인구수
		int bCnt = 0;		// 선거구B 인구수
		
		// 각 선거구의 인구 수 계산
		for(int i=1; i<=N; i++) {
			if (arr[i] == 1) {
				aCnt += person[i] ;
			} else if (arr[i] == 2) {
				bCnt += person[i] ;
			}
		}
		
		// 두 선거구의 인구 차이
		int gap = Math.abs(aCnt-bCnt) ;
		
		// 최솟값 갱신
		min = (gap < min) ? gap : min ;
	}

}
