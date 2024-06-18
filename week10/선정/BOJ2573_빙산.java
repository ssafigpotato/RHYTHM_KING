package algo_0618;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2573_빙산 {
	static int N, M;
	static int[][]map;
	// bfs 방문 표시
	static boolean[][]visited;
	// 이미 녹은 곳 표시
	static boolean[][]melted;
	// 사방 탐색 
	static int[]dr = {-1,0,1,0};
	static int[]dc = {0,1,0,-1};
	static int ans = 0;
	
	static class loc{
		int r;
		int c;
		loc(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	// 빙하 덩어리를 세기 위한 bfs
	static void bfs(int r, int c) {
		Queue<loc> que = new LinkedList<>();
		que.offer(new loc(r,c));
		visited[r][c] = true;
		
		while(!que.isEmpty()) {
			
			loc curr = que.poll();
			
			for(int d = 0; d <4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if(nr< 0 || nr >= N || nc<0 || nc>=M) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 0) continue;
				
				que.offer(new loc(nr,nc));
				visited[nr][nc] = true;
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int [N][M];
		for(int i = 0; i <N; i++) {
			for(int j = 0; j <M; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력 끝
		
		while(true) {
			ans++;
		
			// 1년이 지날 떄마다 새롭게 초기화
			visited = new boolean[N][M];
			melted = new boolean[N][M];
			
			// 빙하 녹이기 
			for(int i =0; i <N; i++) {
				for(int j = 0; j <M; j++) {

					if(map[i][j] > 0) {
						
						// 상하좌우 0 개수 
						int cnt = 0;
						for(int d = 0; d <4; d++) {
							int nr = i+dr[d];
							int nc = j+dc[d];
							
							if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
							if(melted[nr][nc] == true) continue;
							if(map[nr][nc] == 0) cnt++;	
						}
						
						// map[i][j]가 cnt보다 크거나 같을 때는 cnt를 뺀 값으로 갱신하고,
						// 작을 때는 0으로 갱신
						if(map[i][j] >= cnt) {						
							map[i][j] = map[i][j] - cnt;
						}else {
							map[i][j] = 0;
						}
						// 이미 녹은 곳을 표시해줘야 새로운 map[i][j]에 영향 주지 않음
						melted[i][j] = true;
						
					}
				}
			}// for문
			
			
			// 1년이 지나 녹은 상태에서 
			// bfs로 빙하 덩어리 개수 세기
			// parts: 빙하 덩어리 개수
			int parts= 0; 
			for(int i =0 ; i <N; i++) {
				for(int j = 0; j <M; j++) {
					if(map[i][j] != 0 && visited[i][j] == false) {
						
						bfs(i,j);
						parts++;
					}
				}
			}
			
			// 한번에 다 녹아서 셀 수 없을 때
			if (parts == 0) {
                ans = 0;
                break;
            }
			
			// 빙하 덩어리 수가 2개이상일 때
            if (parts >= 2) break;

		}//while
		
		System.out.println(ans);
		
		
	}//main
}
