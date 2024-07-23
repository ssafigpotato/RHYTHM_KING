package algo_0718;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1926_그림 {
	static int n, m; // 돠하지의 세로크기, 가로 크기
	static int [][] map;
	static boolean [][]visited;
	static int []dr = {-1,0,1,0};
	static int []dc = {0,1,0,-1};
	static int cnt; // 그림(덩어리)의 수
	static int max = 0; // 가장 넓은 그림 크기
	static class location{
		int r;
		int c;
		location(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static void bfs(int r, int c) {
		Queue<location> que = new LinkedList<>();
		visited[r][c] = true;
		que.offer(new location(r,c));
		int num = 0;
//		cnt++;
		
		while(!que.isEmpty()) {
			location curr = que.poll();
			// 큐에서 하나를 뽑을 때마다 num++
			num++;
			
			for(int d = 0; d <4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				if(nr <0 || nr >= n || nc < 0 || nc >=m) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] == 0) continue;
				
				que.offer(new location(nr,nc));
				visited[nr][nc] = true;
			}			
		}//while
		max = Math.max(max, num);
//		System.out.println(r+", "+c+"에서 max: "+max);
	}//bfs
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i <n; i++ ) {
			for(int j = 0; j <m; j++) {
				map[i][j] = sc.nextInt();
			}
		}// 입력
		
		for(int i = 0; i <n; i++) {
			for(int j = 0; j <m; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(i,j);
					cnt++;
				}
			}
		}

		System.out.println(cnt);
		System.out.println(max);
		
		
		
	}//main

}
