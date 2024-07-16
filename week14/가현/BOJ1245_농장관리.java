package farm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    1. 인접한 곳보다 낮은 경우:
	- 방문 처리를 한다.
	- 산봉우리가 아님을 표시한다 (isPeak = false)

	2. 인접한 곳과 같은 높이인 경우:
	- 방문 처리를 한다.
	- 큐에 추가하여 추가 탐색한다.
	-> 이는 같은 높이의 연속된 지역을 하나의 산봉우리로 처리하기 위해서
	-> 중복으로 산봉우리를 세는 것을 방지한다.
	
	
	3. 인접한 곳보다 높은 경우:
	- 따로 처리할 필요가 없습니다.
	-> 2번에서 처리되었기 때문
 */

public class Main {
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,-1,-1,0,1,1,1,0};
	static int[] dc = {-1,0,1,1,1,0,-1,-1};
	
	
	// 좌표
	static class Coordinate {
		int r;
		int c;
		
		public Coordinate(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r=0; r<N; r++) { // 맵 입력
			for(int c=0; c<M; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		int peak = 0; // 산봉우리의 개수
		
		for(int r = 0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!visited[r][c] && isPeak(r,c)) { // 아직 방문 x + 산봉우리인지 확인
					peak++; // 산봉우리 개수 ++
				}
			}
		}
		
		System.out.println(peak);

		
	}

	
	
	// 산봉우리인지 아닌지 확인하는 메서드
	private static boolean isPeak(int r, int c) {
		
		Queue<Coordinate> queue = new LinkedList<>(); // 큐 생성
		queue.add(new Coordinate(r,c)); // 현재 좌표를 큐에 추가
		visited[r][c] = true; // 현재 좌표 방문 완료
		
		int height = map[r][c]; // 현재 좌표의 높이를 height에 저장
		boolean isPeak = true; // 일단 산봉우리라고 가정한다. (후에 인접한 곳에 더 높은 좌표가 있는 경우에만 false 처리)
		
		while(!queue.isEmpty()) {
			
			// 좌표를 뽑는다.
			Coordinate curr = queue.poll(); 
			int cr = curr.r;
			int cc = curr.c;
			
			// 인접한 곳 탐색
			for(int i =0; i<8; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr>=0 && nc>= 0 && nr<N && nc<M) { // 범위 확인
					
					// 인접한 곳이 현재 좌표의 높이보다 높다면 이곳은 산봉우리가 아니다!
					if(map[nr][nc] > height) {
						isPeak = false;
					}
					
					// 인접한 곳이 현재 좌표의 높이와 같고 + 아직 방문하지 않은 곳이라면
					// -> 방문처리 완료 + 큐에 새로운 좌표 추가
					else if(map[nr][nc] == height && !visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new Coordinate(nr, nc));
						
					}

				}
			}
			
		}
		
		return isPeak; // 산봉우리인지 아닌지 boolean 값 반환
	}

}
