package 서희;

import java.util.Scanner;

public class BOJ14503_로봇청소기 {
	
	static int n ;
	static int m ;
	static int[][] room ;
	static int cnt ;
	
	// 델타배열 (북, 동, 남, 서)
	static int[] dr = {-1, 0, 1, 0} ;
	static int[] dc = {0, 1, 0, -1} ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		n = sc.nextInt();
		m = sc.nextInt();
		room = new int [n][m] ;		// n * m 크기의 방 
		
		int r = sc.nextInt(); 
		int c = sc.nextInt();		// 처음 로봇 청소기가 있는 칸의 좌표 (r, c) 
		
		int d = sc.nextInt();		// 로봇 청소기가 바라보는 방향
		
		// 방 정보 입력
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				room[i][j] = sc.nextInt();
			}
		}
		
		cnt = 1 ;			// 처음 빈칸은 청소되지 않은 상태이므로 +1

		clean(r, c, d) ;
		
		// 출력
		System.out.println(cnt);
		
		sc.close();
		
	}

	private static void clean(int r, int c, int d) {
		room[r][c] = -1 ;		// 청소했다는 표시로 값 변경
		
		int dir = d ;			// 현재 바라보고 있는 방향 복사
		
		for(int i=0; i<4; i++) {
			dir = (dir+3) % 4 ;			// 반시계 방향으로 회전
			int nr = r + dr[dir] ;
			int nc = c + dc[dir] ;
			
			// 배열 범위 초과할 경우 pass
			if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue ;
			
			// 해당 빈칸이 아직 청소되어 있지 않은 경우
			if(room[nr][nc] == 0) {
				cnt++ ;					// cnt +1 증가 
				clean(nr, nc, dir) ;	// clean 반복
				return;
			}
		}
		
		// 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
		dir = (d+2) % 4 ;			// 후진 방향으로 설정
		int nr = r + dr[dir] ;
		int nc = c + dc[dir] ;
		
		// 배열 범위 초과할 경우 pass
		if(nr < 0 || nr >= n || nc < 0 || nc >= m) return ;
		
		// 후진하고자 하는 위치가 벽이라면 작동을 멈춤
		if(room[nr][nc] == 1) return ;
		
		// 후진한 위치가 벽이 아니라면 clean 반복
		clean(nr, nc, d) ;
		
	}

}
