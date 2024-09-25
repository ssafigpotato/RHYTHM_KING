package daily_0923;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ3055_탈출3 {
	//	비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시
	//  비버의 굴은 'D'로, 고슴도치의 위치는 'S'
	//	매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 
	//	물도 매 분마다 비어있는 칸으로 확장한다. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 
	//	물과 고슴도치는 돌을 통과할 수 없다. 
	//	또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
	
	// 물의 bfs, 고슴도치 bfs를 각각 check 
	// 다음에 물이 찰 예정인 곳도 이동불가
	// 고슴도치 nr,nc == 물 nr,nc면 불가
	// 고슴도치 r,c == 물 r,c 여도 당연 불가 ==> 물부터 먼저 이동시켜 줌 
	static class Location{ // 두더지 위치 
		int r;
		int c;
		int cnt;
		Location(int r, int c, int cnt){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	static class Water{ // 물 위치
		int r;
		int c;
		Water(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	static int R, C; // R행 C열
	static char [][] map;
	static boolean [][]visited;
	static int []dr = {-1,0,1,0};
	static int []dc = { 0,1,0,-1 }; 
	static Queue<Location> que = new LinkedList<>(); // 고슴도치 큐
	static Queue<Water> water = new LinkedList<>(); // 물 큐
	static int answer = Integer.MAX_VALUE;
	static void bfs() {	
		
		while(!que.isEmpty()) {
			// 1. 물부터 이동 시작 (물이 이동할 예정인 곳도 두더지가 갈 수 없으므로)
			int len = water.size();
			for(int i = 0; i <len; i++) {
				Water curr = water.poll();
			
				for(int d = 0; d <4; d++) {
					int nr = curr.r + dr[d];
					int nc = curr.c + dc[d];
					// 범위 내 이면서 빈 곳이면 물 이동
					if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc]=='.') {
						map[nr][nc] = '*';
						water.offer(new Water(nr,nc));
					}
				}
			}// 물 for 끝
		
			// 2. 고슴도치 이동 시작
			len = que.size();
			for(int i=0; i <len; i++) {
				Location dcurr = que.poll();
				for(int d = 0; d<4; d++) {
					int nr = dcurr.r + dr[d];
					int nc = dcurr.c + dc[d];
					int cnt = dcurr.cnt;
					if(nr <0 || nr >= R || nc <0 || nc >= C) continue;
					if(map[nr][nc] == 'D') {
						answer = Math.min(answer, cnt+1);
						return;
					}else if(map[nr][nc] == '.') {
						map[nr][nc] = 'S';
						que.offer(new Location(nr,nc,cnt+1));
					}
				}// 고슴도치 for 끝
			}
		
		}//while
	}// bfs 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		for(int i = 0; i <R; i++) {
			String str = sc.next();
			for(int j = 0; j <C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') {
					que.offer(new Location(i,j,0));
				}else if(map[i][j] == '*') {
					water.offer(new Water(i,j));
				}
			}
		}// 입력 끝
		
		bfs();
		System.out.println(answer == Integer.MAX_VALUE? "KAKTUS" : answer);
		
		
		
		
		
	}// main
}
