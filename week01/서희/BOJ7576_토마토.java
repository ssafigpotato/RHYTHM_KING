package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ7576_토마토 {
	
	static class Coord {
		int r, c ;
		
		Coord(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int m = sc.nextInt();							// 상자의 가로 칸의 수 
		int n = sc.nextInt();							// 상자의 세로 칸의 수 
		int[][] arr = new int [n][m] ;					// 상자 배열

		// 큐 생성
		Queue<Coord> queue = new LinkedList<Coord>() ;
		
		int cnt = 0 ;			// 익은 토마토 수
		int total = m * n ;		// 토마토가 들어있는 수
		
		// 토마토 정보 입력 (1: 익은 토마토, 0: 익지 않은 토마토, -1: 토마토가 들어있지 않음)
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				int tomato = sc.nextInt() ;			// 토마토 상태 입력 
				arr[r][c] = tomato ;				// arr 배열에 삽입 
				if (tomato == 1) {
					cnt++ ;							// 익은 토마토라면 cnt(익은 토마토 수) + 1
					queue.add(new Coord(r, c)) ;	// 익은 토마토 위치 큐에 넣기
				} else if (tomato == -1) {
					// 토마토가 없다면 total(토마토가 들어있는 수) - 1
					total-- ;
				}
			}
		}
		
		int day = 0;		// 며칠 경과했는지 계산
		
		// 델타배열
		int[] dr = {-1, 1, 0, 0} ;
		int[] dc = {0, 0, -1, 1} ;
		
		while(!queue.isEmpty() && cnt != total) {
			// 큐에서 익은 토마토 위치 꺼내기
			Coord coord = queue.poll() ;
			int r = coord.r ;
			int c = coord.c ;
			
			// 익은 토마토와 인접한 위치의 안 익은 토마토 상태변경
			for(int d=0; d<4; d++) {
				int nr = r + dr[d] ;
				int nc  = c + dc[d] ;
				
				// 배열 크기를 넘어갈 경우 pass
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue ;
				
				// 이미 익은 토마토 pass 
				if(arr[nr][nc] != 0) continue ;
				
				arr[nr][nc] = arr[r][c] + 1 ;		// arr[r][c]의 값에 +1을 해서 arr[nr][nc]에 저장
				cnt++ ;								// 익은 토마토 개수 +1
				queue.add(new Coord (nr, nc)) ;		// 새롭게 익은 토마토 큐에 넣기

				// arr[nr][nc]-1의 값으로 day 값 갱신 (처음 익은 토마토의 값이 1이기 때문에 -1)
				day = arr[nr][nc] - 1 ;
			}
		}
		
		// 큐가 비었음에도 모든 토마토가 익지 않았다면 토마토가 모두 익지 못하는 상황
		if (cnt != total) {
			day = -1 ;
		}
		
		System.out.println(day);
		
		sc.close();
		
	}

}
