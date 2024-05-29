package sheep;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int R,C; // 행, 열
	static int sheepAlive, wolvesAlive; // 살아남은 양, 늑대
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static char[][] map;
	static boolean[][] visited;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int r=0; r<R; r++) {
			String line = sc.next();
			for(int c=0; c<C; c++) {
				map[r][c] = line.charAt(c);
				
				if(map[r][c]=='#') { //울타리에 방문처리
					visited[r][c] = true;
				}
			}
		}
		
		// 방문처리 안된 곳 = 울타리가 아닌 곳은 bfs 돌리기
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(!visited[r][c])
					bfs(r,c);
			}
		}
		
		System.out.println(sheepAlive + " " + wolvesAlive);
		
	}


	private static void bfs(int r, int c) {
		
		// 큐 생성 => 출발지: 방문 처리 + 큐 삽입
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r,c});
		visited[r][c] = true;
		
		// 영역 내 양과 늑대
		int sheep = 0;
		int wolf = 0;
		
		// 출발지의 양이나 늑대 고려
		if(map[r][c] == 'o') sheep++;
		if(map[r][c] == 'v') wolf++;
			
		
		while(!queue.isEmpty()) {
			
			int[] curr = queue.poll();
			
			for(int d =0; d<4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];
				
				if(nr>=0 && nc>=0 && nr<R && nc<C) {
					if(!visited[nr][nc]) {
						visited[nr][nc]= true;
						queue.add(new int[] {nr, nc});
						
						// 영역 내 양과 늑대 개체 수 계산
						if(map[nr][nc] == 'o') sheep++;
						else if(map[nr][nc] == 'v') wolf++;
					}
				}
			}
		}
		
		// 조건에 따라 영역 내 살아남은 양이나 늑대의 개체 수를 업데이트 한다.
		if(sheep>wolf) sheepAlive += sheep;
		else if(sheep<=wolf) wolvesAlive += wolf;
		
		
	}
}
