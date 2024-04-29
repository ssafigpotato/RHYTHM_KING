package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ21610_마법사상어와비바라기 {
	
	// 큐에 위치를 저장하기 위해 사용하는 클래스
	static class Path {
		int r, c ;
		
		public Path(int r, int c) {
			this.r = r ;
			this.c = c ;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in) ;
		
		int n = sc.nextInt();						// N * N 크기의 격자 
		int m = sc.nextInt();						// m번 이동 
		int[][] water = new int [n+1][n+1] ;		// 각 바구니에 저장된 물의 양 저장 
		
		// 각 바구니에 저장된 물의 양 입력
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				water[r][c] = sc.nextInt() ;
			}
		}
		
		Queue<Path> preCloud = new LinkedList<Path>() ;		// 비구름이 생긴 칸을 저장하기 위한 큐 
		Queue<Path> moveCloud = new LinkedList<Path>() ;	// 비구름이 이동한 칸을 저장하기 위한 큐
		
		// 시작할 때는 (N,1), (N,2), (N-1, 1), (N-1, 2)에 비구름 생성
		preCloud.add(new Path(n, 1)) ;
		preCloud.add(new Path(n, 2)) ;
		preCloud.add(new Path(n-1, 1)) ;
		preCloud.add(new Path(n-1, 2)) ;
		
		
		// 구름에 이동을 m번 명령
		for(int i=1; i<=m; i++) {
			int d = sc.nextInt();		// 방향
			int s = sc.nextInt();		// 거리 

			boolean[][] checkCloud = new boolean [n+1][n+1] ;	// 구름이 있던 칸 체크
			
			// 입력받은 방향으로 입력받은 거리만큼 구름 이동
			while(!preCloud.isEmpty()) {
				Path path = preCloud.poll() ;
				
				// 8개의 이동 방향 (1번부터 시작하기 위해 0번에는 임시 값 설정)
				int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1} ;
				int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1} ;
				
				// 입력받은 방향으로 입력받은 거리만큼 이동 계산
				int r = (path.r + (dr[d] * s)) % n ;
				if(r <= 0) r = n + r ;
				int c = (path.c + (dc[d] * s)) % n ;
				if(c <= 0) c = n + c ;
				
				water[r][c]++ ;						// 구름있는 칸에 비 1씩 내리기
				checkCloud[r][c] = true ;			// 구름이 사라진 칸은 다음에 구름이 생기지 않음
				moveCloud.add(new Path(r, c)) ;		// 이동한 구름 위치 저장
			}
			
			// 물이 증가한 칸에 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 물의 양 증가
			while(!moveCloud.isEmpty()) {
				Path path = moveCloud.poll() ;
				int cnt = 0 ;
				
				// 대각선 방향으로 거리가 1인 칸의 위치를 구하기 위한 델타배열
				int[] dr = {-1, -1, 1, 1} ;
				int[] dc = {-1, 1, -1, 1} ;
				
				for(int dIdx=0; dIdx<4; dIdx++) {
					int checkR = path.r + dr[dIdx] ;
					int checkC = path.c + dc[dIdx] ;
					
					if(checkR <= 0 || checkR > n || checkC <= 0 || checkC > n) continue ;
					
					if(water[checkR][checkC] > 0) cnt++ ;
				}
				
				water[path.r][path.c] += cnt ;
			}
			
			// 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양은 2감소
			for(int r=1; r<=n; r++) {
				for(int c=1; c<=n; c++) {
					// 바로 전 이동에서 구름이 사라진 칸은 이번에 구름이 생기지 않음
					if(water[r][c] >= 2 && !checkCloud[r][c]) {
						preCloud.add(new Path(r, c)) ;
						water[r][c] = (water[r][c] - 2 > 0) ? water[r][c] - 2 : 0 ;
					}
				}
			}
			
			
		}
		
		// 이동이 모두 끝났을 때, 바구니에 들어있는 물의 양의 합 계산
		int sum = 0 ;
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=n; c++) {
				sum += water[r][c] ;
			}
		}
		
		System.out.println(sum);
		
		sc.close();
		
	}

}
