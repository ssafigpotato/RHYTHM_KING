package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2589_보물섬 {
	
	static class Path {
		int r, c ;
		
		public Path(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int rSize = sc.nextInt();						// 보물 지도의 세로 크기
		int cSize = sc.nextInt();						// 보물 지도의 가로 크기 
		String[][] map = new String [rSize][cSize] ;	// 보물 지도 배열
		
		// L(육지)과 W(바다)로 표시된 보물 지도
		for(int r=0; r<rSize; r++) {
			String[] tmp = sc.next().split("") ;
			
			for(int c=0; c<cSize; c++) {
				map[r][c] = tmp[c] ;
			}
		}
		
		// 델타배열
		int[] dr = {-1, 1, 0, 0} ;
		int[] dc = {0, 0, -1, 1} ;
		
		int answer = Integer.MIN_VALUE ;		// 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간
		
		for(int r=0; r<rSize; r++) {
			for(int c=0; c<cSize; c++) {
				
				// 해당 위치가 바다(W)이면 pass
				if(map[r][c].equals("W")) continue ;
				
				// 해당 위치가 육지(L)라면 BFS 탐색하여, 가장 먼 육지까지의 길이 구하기
				Queue<Path> queue = new LinkedList<Path>() ;	// 큐 생성 
				int[][] visited = new int [rSize][cSize] ;		// 방문배열 
				
				queue.add(new Path(r, c)) ;
				visited[r][c] = 1 ;
				
				while(!queue.isEmpty()) {
					Path path = queue.poll() ;
					
					for(int d=0; d<4; d++) {
						int nr = path.r + dr[d] ;
						int nc = path.c + dc[d] ;
						
						// 인덱스 범위 초과
						if(nr < 0 || nr >= rSize || nc < 0 || nc >= cSize) continue ;
	
						// 해당 위치가 바다(W)이거나, 이미 방문한 곳이라면 pass
						if(map[nr][nc].equals("W") || visited[nr][nc] != 0) continue ;
						
						queue.add(new Path(nr, nc)) ;
						visited[nr][nc] = visited[path.r][path.c] + 1 ;
						answer = (visited[nr][nc] > answer) ? visited[nr][nc] : answer ;
					}
				}
				
			}
		}
		
		// 시작할 때 0이 아닌 1로 값을 설정했기 때문에 -1 
		System.out.println(answer-1);
		
		sc.close();
		
	}

}
