package 서희;

import java.util.Scanner;

public class BOJ14502_연구소 {
	
	static int N ;				// 세로 크기 
	static int M ;				// 가로 크기 	
	static int[][] lab ;		// 연구소 
	static int[][] copyLab ;	// 연구소 복사본 
	static boolean[][] visited;	// 방문체크  
	static int max ;			// 안전 영역의 최대 크기
	
	// 델타배열
	static int[] dr = {-1, 1, 0, 0} ;
	static int[] dc = {0, 0, -1, 1} ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		// 변수 초기화
		N = sc.nextInt() ;
		M = sc.nextInt() ;
		lab = new int [N][M] ;
		copyLab = new int [N][M] ;
		max = 0;
		
		// 연구실 정보 입력 
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				lab[r][c] = sc.nextInt();
			}
		}
		
		// 3개의 벽 세우기
		build(0) ;
		
		System.out.println(max);
		
		sc.close();
		
	}

	/**
	 * 3개의 벽을 세우는 메소드 
	 * @param cnt
	 */
	private static void build(int cnt) {
		// 총 3개의 벽을 다 세우면 바이러스 퍼지는 메소드로 이동
		if (cnt >= 3) {
			spread() ;
			return ;
		}
		
		// 벽을 세울 수 있는 위치 찾기
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(lab[r][c] == 0) {
					lab[r][c] = 1 ;
					build(cnt+1) ;
					lab[r][c] = 0 ;
				}
			}
		}
	}

	/**
	 * 바이러스가 퍼지는 메소드
	 */
	private static void spread() {
		// lab 정보를 복사해서 작업
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				copyLab[r][c] = lab[r][c] ;
			}
		}

		visited = new boolean [N][M] ;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(copyLab[r][c] == 2 && !visited[r][c]) {
					DFS(r, c) ;
				}
			}
		}
		
		count() ;
	}

	private static void DFS(int r, int c) {
		visited[r][c] = true ;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d] ;
			int nc = c + dc[d] ;
			
			// 연구소 크기를 벗어나면 pass
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue ;
			
			// 이미 방문했거나 벽이라면 pass
			if(visited[nr][nc] || copyLab[nr][nc] == 1) continue ;
			
			copyLab[nr][nc] = 2 ;
			DFS(nr, nc) ;
		}
	}

	/**
	 * 안전 영역의 크기를 세는 메소드
	 */
	private static void count() {
		int cnt  = 0 ;
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if (copyLab[r][c] == 0) {
					cnt++ ;
				}
			}
		}
		
		// 최댓값 비교
		max = (cnt > max) ? cnt : max ;
	}

}
