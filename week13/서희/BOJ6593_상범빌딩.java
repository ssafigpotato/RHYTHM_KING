package 서희;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ6593_상범빌딩 {
	
	static class Path {
		int l, r, c;
		
		Path(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int l = sc.nextInt();		// 상범 빌딩의 층 수
			int r = sc.nextInt();		// 상범 빌딩의 한 층의 행 개수
			int c = sc.nextInt();		// 상범 빌딩의 한 층의 열 개수
			
			// 반복문 종료 조건
			if(l == 0 && r == 0 && c == 0) break;
			
			String[][][] building = new String [l][r][c];	// 빌딩 정보 입력
			int[][][] visited = new int [l][r][c];			// 방문체크 및 걸린 시간을 저장하는 배열
			
			Path start = null;	// BFS 시작지점
			Path end = null;	// BFS 종료지점
			
			for(int i=0; i<l; i++) {
				for(int j=0; j<r; j++) {
					String[] temp = sc.next().split("");
					for(int z=0; z<c; z++) {
						building[i][j][z] = temp[z];
						
						// 시작지점 저장
						if(temp[z].equals("S")) {
							start = new Path(i, j, z);
							visited[i][j][z] = 1;
						} else if(temp[z].equals("E")) {
							end = new Path(i, j, z);
						}
					}
				}
				
				sc.nextLine();
				
			}
			
			// 델타배열
			int[] dc = {0, 0, 0, 0, -1, 1};
			int[] dr = {0, 0, -1, 1, 0, 0};
			int[] dl = {-1, 1, 0, 0, 0, 0};
			
			// BFS 탐색
			Queue<Path> queue = new LinkedList<Path>();		// 큐 생성
			queue.add(start);
			
			while(!queue.isEmpty()) {
				Path path = queue.poll();
				
				// 종료조건
				if(path.l == end.l && path.r == end.r && path.c == end.c) {
					System.out.printf("Escaped in %d minute(s).\n", visited[path.l][path.r][path.c]-1);
					break;
				}
				
				// 델타탐색
				for(int d=0; d<6; d++) {
					int nl = path.l + dl[d];
					int nr = path.r + dr[d];
					int nc = path.c + dc[d];
					
					// 범위 초과
					if(nl < 0 || nl >= l || nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
					
					// 이미 방문했던 위치 or 막혀있는 곳이라면 pass
					if(visited[nl][nr][nc] != 0 || building[nl][nr][nc].equals("#")) continue;
					
					visited[nl][nr][nc] = visited[path.l][path.r][path.c] + 1;
					queue.add(new Path(nl, nr, nc));
				}
			}
			
			// 탈출에 성공하지 못한 경우
			if(visited[end.l][end.r][end.c] == 0) {
				System.out.println("Trapped!");
			}
			
		}
		
		sc.close();
		
	}

}
