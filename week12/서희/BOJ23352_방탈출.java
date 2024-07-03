package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ23352_방탈출 {
	
	static int n;			// 지도의 세로 크기 
	static int m;			// 지도의 가로 크기 
	static int[][] map;		// 지도 (각 방의 숫자를 저장)
	
	static int[][] dMap;	// 이동 거리를 저장하고 있는 지도 배열
	static int d;			// 가장 먼 거리
	static int answer;		// 가장 긴 경로의 시작 방과 끝 방에 적힌 숫자의 합
	
	// 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// queue에 저장하기 위한 Path 클래스
	static class Path {
		int r, c ;
		
		public Path(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int [n][m];
		
		d = 0;
		answer = 0;
		
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				map[r][c] = sc.nextInt();
			}
		}
		
		// 방의 숫자가 0이 아니라면 bfs 탐색을 하며 거리 계산
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				if(map[r][c] != 0) {
					bfs(r, c);
				}
			}
		}
		
		System.out.println(answer);
		
		sc.close();
		
	}

	private static void bfs(int startR, int startC) {
		dMap = new int [n][m];
		dMap[startR][startC] = 1;
		
		Queue<Path> queue = new LinkedList<Path>();
		queue.add(new Path(startR, startC));
		
		int dMax = 1;		// 현재 r, c에서 이동할 수 있는 최대 거리
		
		while(!queue.isEmpty()) {
			Path path = queue.poll();
			
			// 상하좌우 4가지 방향으로 이동 가능
			for(int d=0; d<4; d++) {
				int nr = path.r + dr[d];
				int nc = path.c + dc[d];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				// 방문한 적이 없고 들어갈 수 있는 방이라면
				if(dMap[nr][nc] == 0 && map[nr][nc] != 0) {
					queue.add(new Path(nr, nc));			// queue에 넣기 
					dMap[nr][nc] = dMap[path.r][path.c]+1;	// 거리 계산
					dMax = Math.max(dMax, dMap[nr][nc]); 	// 최대 거리 갱신되는지 확인
				}
			}
		}
		
		// 이번에 계산된 dMax가 현재까지 계산된 경로 중 가장 긴 경로거나 길이가 같다면
		if(dMax >= d) {
			// 시작위치 값 + 끝 위치 값 계산
			int startValue = 0;
			int endValue = 0;
			
			int check = 0;
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					// 시작 위치
					if(dMap[r][c] == 1) {
						startValue = map[r][c];
						check++;
					}
					// 끝 위치
					if(dMap[r][c] == dMax) {
						endValue = map[r][c];
						check++;
					}
					// 빠른 종료 조건
					if(check == 2) break;
				}
			}
			
			int sum = startValue + endValue ;
			
			// 가장 긴 경로이거나, 만약 경로 길이가 같다면 시작 방과 끝 방에 적힌 숫자의 합이 더 클 때 답 갱신
			if(dMax > d || (dMax == d && sum > answer)) {
				answer = sum;
				d = dMax;
			}
		}
	}

}
