package 서희;

import java.util.Scanner;

public class BOJ17144_미세먼지안녕 {
	
	static int R ;
	static int C ;				// 집의 크기 = R*C인 격자판
	static int T ;				// 시간
	static int[][] room ;
	static int[][] copyRoom ;	// 방 정보 복사본
	
	static int acTop ;			// 공기청정기가 설치된 곳의 윗 행
	static int answer ;			// T초가 지난 후 방에 남아있는 미세먼지의 양
	
	// 델타배열
	static int[] dr = {-1, 1, 0, 0} ;
	static int[] dc = {0, 0, -1, 1} ;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		R = sc.nextInt();		
		C = sc.nextInt();
		T = sc.nextInt();
		
		acTop = 0 ;
		answer = 0 ;
		
		// 방 정보 입력
		room = new int [R][C];
		copyRoom = new int [R][C] ;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				int amount = sc.nextInt();
				room[r][c] = amount;
				
				if(amount == -1 && acTop == 0) acTop = r ;
			}
		}
		
		int curTime = 0 ;		// 현재 경과한 시간
		while(curTime < T) {
			curTime++ ;			// 시간 추가
			
			// 원본 복사
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					copyRoom[r][c] = room[r][c] ;
				}
			}
			
			spread() ;
		}
		
		// T초가 지난 후 방에 남아있는 미세먼지 양 계산
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(room[r][c] > 0) {
					answer += room[r][c] ;
				}
			}
		}
		
		System.out.println(answer);
		
		sc.close();
		
	}

	/**
	 * 확산되는 미세먼지 계산
	 */
	private static void spread() {
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				// 미세먼지가 있는 칸이라면
				if(room[r][c] > 0) {
					int spreadAmount = room[r][c] / 5 ;		// 확산되는 양
					int spreadTotal = 0 ;					// 총 확산된 양
					
					// 인접한 네 방향으로 확산
					for(int d=0; d<4; d++) {
						int nr = r + dr[d] ;
						int nc = c + dc[d] ;
						
						// 칸이 없을 경우 확산이 일어나지 않음
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue ;
						
						// 공기청정기가 있는 칸으로는 확산이 일어나지 않음
						if((nr == acTop && nc == 0) || (nr == acTop+1 && nc == 0)) continue ;
						
						// 확산됨
						copyRoom[nr][nc] += spreadAmount ;
						spreadTotal += spreadAmount ;
					}
					
					// 남은 미세먼지 양
					copyRoom[r][c] -= spreadTotal ;
					
					// 남은 미세먼지 양이 음수가 나올 순 없음
					if (copyRoom[r][c] < 0) {
						copyRoom[r][c] = 0 ;
					}
				}
			}
		}
		
		// 원본에 반영
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				room[r][c] = copyRoom[r][c] ;
			}
		}
		
		wind() ;
	}

	/**
	 * 공기청정기의 바람에 따라 미세먼지 이동 및 copyRoom에 저장된 미세먼지 양 원본에 반영
	 */
	private static void wind() {
		// 바람 델타배열 (앞에 4개 반시계방향, 뒤에 4개 시계방향) 
		int[] windR = {0, -1, 0, 1, 0, 1, 0, -1};
		int[] windC = {1, 0, -1, 0, 1, 0, -1, 0};
		
		// 위쪽 공기청정기 (반시계방향으로 순환)
		int r = acTop ;
		int c = 1 ;
		int d = 0 ;
		
		room[r][c] = 0 ;	// 바로 옆이 공기청정기 이므로 넘어오는 값이 없음
		
		while(true) {
			int nr = r + windR[d] ;
			int nc = c + windC[d] ;
			
			// 한바퀴 돌았음 -> 공기청정기로 들어간 미세먼지는 정화되므로 그냥 값이 사라짐
			if(nr == acTop && nc == 0) break ;
			
			// 끝 지점에 닿을 때마다 d 인덱스 이동
			if(nc >= C || nr < 0 || nc < 0 || nr > acTop) {
				d++ ;
				continue ;
			}
			
			room[nr][nc] = copyRoom[r][c] ;
			r = nr ; c = nc ;
		}
		
		// 아래쪽 공기청정기 (시계방향으로 순환)
		r = acTop + 1;
		c = 1 ;
		d = 4 ;
		
		room[r][c] = 0 ;	// 바로 옆이 공기청정기 이므로 넘어오는 값이 없음
		
		while(true) {
			int nr = r + windR[d] ;
			int nc = c + windC[d] ;
			
			// 한바퀴 돌았음 -> 공기청정기로 들어간 미세먼지는 정화되므로 그냥 값이 사라짐
			if(nr == acTop+1 && nc == 0) break ;
			
			// 끝 지점에 닿을 때마다 d 인덱스 이동
			if(nc >= C || nr >= R || nc < 0 || nr < acTop) {
				d++ ;
				continue ;
			}
			
			room[nr][nc] = copyRoom[r][c] ;
			r = nr ; c = nc ;
		}
	}

}
