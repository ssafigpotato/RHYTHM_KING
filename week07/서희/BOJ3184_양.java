package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3184_양 {
	
	static class Path {
		int r, c ;
		
		public Path(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int rSize = sc.nextInt();							// 행의 길이
		int cSize = sc.nextInt();							// 열의 길이
		String[][] arr = new String [rSize][cSize] ;		// 마당구조를 담고있는 배열
		boolean[][] visited = new boolean [rSize][cSize] ;	// 방문체크 배열
		
		// 마당구조 입력 ('.' : 비어있음, '#' : 울타리, 'o' : 양, 'v' : 늑대)
		for(int r=0; r<rSize; r++) {
			String[] tmp = sc.next().split("") ;
			for(int c=0; c<cSize; c++) {
				arr[r][c] = tmp[c] ;
			}
		}
		
		// 델타배열 (수평, 수직 이동)
		int[] dr = {-1, 1, 0, 0} ;
		int[] dc = {0, 0, -1, 1} ;
		
		// BFS 탐색을 위한 큐 생성
		Queue<Path> queue = new LinkedList<Path>() ;
		
		// 아침까지 살아있는 양과 늑대의 수
		int saveWolf = 0 ;
		int saveSheep = 0 ;
		
		for(int r=0; r<rSize; r++) {
			for(int c=0; c<cSize; c++) {
				
				// 해당 위치가 울타리이거나 이미 방문한 곳이면 pass
				if(arr[r][c].equals("#") || visited[r][c]) continue ;
				
				// 한 영역에서 BFS 탐색하며 늑대와 양의 수 구하기
				int wolf = 0 ;
				int sheep = 0 ;
				
				queue.add(new Path(r, c)) ;
				visited[r][c] = true ;
				
				while(!queue.isEmpty()) {
					Path path = queue.poll() ;
					
					if(arr[path.r][path.c].equals("o")) sheep++ ;
					else if(arr[path.r][path.c].equals("v")) wolf++ ;
					
					for(int d=0; d<4; d++) {
						int nr = path.r + dr[d] ;
						int nc = path.c + dc[d] ;
						
						// 인덱스 초과
						if(nr < 0 || nr >= rSize || nc < 0 || nc >= cSize) continue ;
						
						// 울타리이거나 이미 방문했던 곳이면 pass
						if(arr[nr][nc].equals("#") || visited[nr][nc]) continue ;
						
						queue.add(new Path(nr, nc)) ;
						visited[nr][nc] = true ;
					}
				}
				
				// BFS 탐색이 종료되었을 때 영역 안에 있는 늑대와 양의 수에 따라 살아남은 늑대와 양의 수 계산
				if(sheep > wolf) {
					saveSheep += sheep ;
				} else {
					saveWolf += wolf ;
				}
				
			}
		}
		
		System.out.println(saveSheep + " " + saveWolf);
		
		sc.close();
		
	}

}
