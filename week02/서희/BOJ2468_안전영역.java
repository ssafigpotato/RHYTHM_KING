package 서희;

import java.util.Scanner;

public class BOJ2468_안전영역 {

	static int N ;
	static int[][] arr ;
	static boolean[][] visited ;
	
	// 델타배열
	static int[] dr = {-1, 1, 0, 0} ;
	static int[] dc = {0, 0, -1, 1} ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		N = sc.nextInt();			// 지역의 행과 열의 개수
		
		arr = new int [N][N] ;		// 지역 정보를 담고 있는 배열 
		
		int min = Integer.MAX_VALUE ;	// 높이의 최솟값 계산 				
		int max = 0 ;					// 높이의 최댓값 계산
		
		// 지역 정보 입력
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				int num = sc.nextInt() ;
				arr[r][c] = num ;
				
				// 높이 최댓값, 최솟값 비교해서 갱신
				max = (num > max) ? num : max ;
				min = (num < min) ? num : min ;
			}
		}
		
		int answer = 0 ;		// 물에 잠기지 않는 안전한 영역의 최대 개수
		
		for(int h=min-1; h<=max; h++) {
			int cnt = 0 ;						// 안전한 영역 계산
			visited = new boolean [N][N] ;		// 방문배열 초기화
			
			// 지역을 하나씩 순회하면서 물에 잠기지 않은 위치 찾기 
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if (arr[r][c] > h && !visited[r][c]) {
						cnt++ ;						// 안전한 영역 1 증가 
						visited[r][c] = true ;		// 방문처리
						search(r, c, h) ;			// 인접한 영역 조사
					}
				}
			}
			
			// 안전한 영역의 최대 개수 계산
			answer = (cnt > answer) ? cnt : answer ;
		}
		
		// 출력
		System.out.println(answer);
		
		sc.close();
		
	}

	/**
	 * 인접한 안전한 영역 조사
	 * @param r
	 * @param c
	 * @param h
	 */
	private static void search(int r, int c, int h) {
		// 델타탐색
		for(int d=0; d<4; d++) {
			int nr = r + dr[d] ;
			int nc = c + dc[d] ;
			
			// 배열 범위 초과하면 pass 
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue ;
			
			// 물에 잠기는 영역이거나 이미 방문한 장소면 pass 
			if (arr[nr][nc] <= h || visited[nr][nc]) continue ;
			
			visited[nr][nc] = true ;		// 방문처리
			search(nr, nc, h) ;				// 다시 인접한 안전 영역 조사
			
		}
		return ;
	}
	
}
