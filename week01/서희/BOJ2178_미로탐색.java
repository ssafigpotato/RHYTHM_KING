package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2178_미로탐색 {
	
	static class Path {
		int r, c ;
		
		Path(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int [N][M] ;					// 미로 배열
		boolean[][] visited = new boolean [N][M] ;		// 방문 체크 배열
		
		// 미로 정보 입력
		for(int r=0; r<N; r++) {
			String[] str = sc.next().split("") ;
			for(int c=0; c<M; c++) {
				arr[r][c] = Integer.parseInt(str[c]) ;
			}
		}
		
		// 델타배열
		int[] dr = {-1, 1, 0, 0} ;
		int[] dc = {0, 0, -1, 1} ;
		
		// 큐 생성
		Queue<Path> queue = new LinkedList<Path>() ;
		
		queue.add(new Path(0, 0)) ;			// 큐에 시작위치 넣기
		visited[0][0] = true ;				// 방문처리
		
		int answer = 0 ;
		
		while(!queue.isEmpty()) {
			// 큐에서 현재 위치 꺼내기
			Path now = queue.poll() ;
			int r = now.r ;
			int c = now.c;
			
			// 도착지점에 도착하면 반복종료
			if(r == (N-1) && c == (M-1)) {
				answer = arr[r][c] ;
				break ;
			}
			
			// 도착지점에 도착하지 않으면 델타탐색하며 최소 칸수 계산
			for(int d=0; d<4; d++) {
				int nr = r + dr[d] ;
				int nc = c + dc[d] ;
				
				// 배열 범위 벗어나면 pass
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue ;
				
				// 이미 방문한 위치거나 이동할 수 없는 곳이라면 pass
				if (visited[nr][nc] || arr[nr][nc] == 0) continue ;
				
				queue.add(new Path(nr, nc)) ;		// 큐에 해당 위치 넣기 (계속 탐색해야 하므로)
				visited[nr][nc] = true ;			// 방문처리
				arr[nr][nc] = arr[r][c] + 1 ;		// 몇 번 이동해야 arr[nr][nc]에 도착할 수 있는지 기록
			}
		}
		
		// 출력
		System.out.println(answer);
		
		sc.close();
		
	}

}
