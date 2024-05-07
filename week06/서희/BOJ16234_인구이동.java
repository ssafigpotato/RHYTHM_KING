package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ16234_인구이동 {

	static int n ;
	static int minGap ;
	static int maxGap ;
	static int[][] person ;
	static boolean move ;
	static int[][] changePerson ;
	
	static class Path {
		int r, c ;
		
		Path(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		n = sc.nextInt();				// n * n 크기의 땅
		minGap = sc.nextInt();
		maxGap = sc.nextInt();			// 두 나라의 인구 차이가 minGap 이상, maxGap 이하
		person = new int [n][n];		// 각 나라의 인구수를 저장한 배열
		
		// 각 나라의 인구수 입력
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				person[r][c] = sc.nextInt();
			}
		}
		
		int day = 0 ;		// 인구 이동이 일어난 날짜를 계산
		
		// 인구 이동 시작
		while(true) {
			// 국경선 열고, 인구이동
			open() ;
			
			// 인구 이동이 일어나지 않았다면 인구 이동 종료
			if(!move) break ;
			
			day++ ;			// 인구 이동이 일어난 날짜 + 1
		}
		
		System.out.println(day);
		
		sc.close();
		
	}

	/**
	 * 국경선을 공유하는 두 나라의 인구 차이가 minGap 이상, maxGap 이하라면 국경선 열기
	 */
	private static void open() {
		
		boolean[][] visited = new boolean [n][n] ;			// 방문체크를 위한 배열
		Queue<Path> bfsQueue = new LinkedList<Path>() ;		// BFS 탐색을 위한 큐
		move = false ;		// 인구 이동이 일어났는지 여부를 체크
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<n; c++) {
				
				// 이미 방문했던 위치라면 pass
				if(visited[r][c]) continue ;
				
				Queue<Path> calQueue = new LinkedList<Path>() ;		// 인구수 계산을 위한 큐
				
				// 방문했던 위치가 아니라면 큐에 넣고 BFS 탐색 시작
				bfsQueue.add(new Path(r, c)) ;
				calQueue.add(new Path(r, c)) ;
				visited[r][c] = true ;
				
				int pSum = person[r][c] ;
				
				int[] dr = {-1, 1, 0, 0} ;
				int[] dc = {0, 0, -1, 1} ;
				
				while(!bfsQueue.isEmpty()) {
					Path path = bfsQueue.poll() ;
					
					for(int d=0; d<4; d++) {
						int nr = path.r + dr[d] ;
						int nc = path.c + dc[d] ;
						
						// 배열 범위 초과
						if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue ;
						
						// 이미 방문했던 위치라면 pass
						if(visited[nr][nc]) continue ;
						
						// 두 나라의 인구 차이 계산
						int personGap = Math.abs(person[nr][nc] - person[path.r][path.c]) ;
						
						if(personGap >= minGap && personGap <= maxGap) {
							move = true ;
							visited[nr][nc] = true ;
							bfsQueue.add(new Path(nr, nc)) ;
							calQueue.add(new Path(nr, nc)) ;
							pSum += person[nr][nc] ;
						}
					}
				}
				
				
				// 인구수 변경
				if(calQueue.size() > 1) {
					calPerson(calQueue, pSum) ;
				}
				
			}
		}
	}

	/**
	 * 인구이동
	 */
	private static void calPerson(Queue<Path> calQueue, int pSum) {
		
		int newPersonNum = pSum / calQueue.size() ;
		
		while(!calQueue.isEmpty()) {
			Path path = calQueue.poll() ;
			
			person[path.r][path.c] = newPersonNum ;
		}
		
	}

}
