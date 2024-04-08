package 서희;

import java.util.Scanner;

public class BOJ10026_적록색약 {
	
	static int N ;			// 크기가 N * N인 그리드
	static String[][] arr ;	// 각 칸의 색깔 정보를 가지고 있는 배열
	
	static boolean[][] notPvisited ;		// 적록색약이 아닌 사람의 방문체크
	static boolean[][] rgPvisited ;			// 적록색약인 사람의 방문체크
	
	static int notCnt ;		// 적록색약이 아닌 사람이 본 구역 수
	static int rgCnt ;		// 적록색약인 사람이 본 구역 수
	
	// 델타배열
	static int[] dr = {-1, 1, 0, 0} ;
	static int[] dc = {0, 0, -1, 1} ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		N = sc.nextInt();
		arr = new String [N][N] ;
		notPvisited = new boolean [N][N] ;
		rgPvisited = new boolean [N][N] ;
		
		// 색깔 정보 입력
		for(int r=0; r<N; r++) {
			String[] line = sc.next().split("") ;
			for(int c=0; c<N; c++) {
				arr[r][c] = line[c] ;
			}
		}
		
		// 적록색약이 아닌 사람이 보는 구역 수 계산
		notCnt = 0 ;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!notPvisited[r][c]) {
					notCnt++ ;
					String color = arr[r][c] ;
					notPcheck(color, r, c) ;
				}
			}
		}
		
		// 적록색약인 사람이 보는 구역 수 계산
		rgCnt = 0 ;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!rgPvisited[r][c]) {
					rgCnt++ ;
					String color = arr[r][c] ;
					rgPcheck(color, r, c) ;
				}
			}
		}
		
		System.out.println(notCnt + " " + rgCnt);
		
		sc.close();
		
	}

	private static void notPcheck(String color, int r, int c) {
		notPvisited[r][c] = true ;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d] ;
			int nc = c + dc[d] ;
			
			if(indexCheck(nr, nc) && !notPvisited[nr][nc] && arr[nr][nc].equals(color)) {
				notPcheck(color, nr, nc) ;
			}
			
		}
	}

	private static void rgPcheck(String color, int r, int c) {
		rgPvisited[r][c] = true ;
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d] ;
			int nc = c + dc[d] ;
			
			if(!indexCheck(nr, nc) || rgPvisited[nr][nc]) continue ;
			
			if(color.equals("B")) {
				if(arr[nr][nc].equals("B")) {
					rgPcheck(color, nr, nc) ;
				}
			} else {
				if(!arr[nr][nc].equals("B")) {
					rgPcheck(color, nr, nc) ;
				}
			}
			
		}
	}

	private static boolean indexCheck(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= N) return false ;
		return true;
	}
	
	

}
