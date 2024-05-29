package algo_0529;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class loc{
	int r;
	int c;
	loc(int r, int c){
		this.r = r;
		this.c = c;
	}
}
public class BOJ3184_양 {
	static int R;
	static int C;
	static char[][] map;
	static boolean[][]visited;
	static int[]dr = {-1,0,1,0};
	static int[]dc = {0,1,0,-1};
	static int o_total=0;
	static int v_total=0;
	// . : 빈 필드, # : 울타리, o : 양, v : 늑대
	static void bfs(int r, int c) {
		// 1. loc타입의 큐를 선언하고 큐에 현재 위치 집어넣기
		Queue<loc> que = new LinkedList<loc>();
		que.offer(new loc(r,c));
		visited[r][c] = true;
		
		// 2. 영역내 양과 늑대 수 초기화
		int v_cnt = 0;
		int o_cnt = 0;
		// 3. 처음 위치(r,c)에서의 양과 늑대 수 체크
        if (map[r][c] == 'o') o_cnt++;
        if (map[r][c] == 'v') v_cnt++;
		
		
		// 4. while
		while(!que.isEmpty()) {
			// 4-1. 큐에서 하나를 뽑아서
			loc curr = que.poll();
			
			// 4-2. 사방탐색 시작(수평,수직으로만 움직일 수 있음)
			for(int d=0; d <4; d++) {
				int nr = dr[d]+curr.r;
				int nc = dc[d]+curr.c;
				
				// 탈출 조건
				// 1) 범위를 벗어나면 건너뛰기
				if (nr <0 || nr>=R || nc<0 || nc >=C)continue;
				// 2) 울타리가 나오면 건너뛰기
				if(map[nr][nc] == '#')continue;
				// 3) 이미 방문한 곳이면 건너뛰기
				if(visited[nr][nc] == true)continue;
				
				// 양과 늑대 수
				if(map[nr][nc] == 'o') o_cnt++;
				if(map[nr][nc] == 'v') v_cnt++;
				
				// 방문 체크 및 큐 추가 
				visited[nr][nc] = true;
				que.offer(new loc(nr,nc));
				
			}
		}//while
		
		// 5. 영역 내에서 양 수, 늑대 수 비교
		if(o_cnt > v_cnt) {
			// 양 수 > 늑대 수 이면 양 수만 반영
			o_total += o_cnt;
		}else {
			// 늑대 수 > 양 수 이면 늑대 수만 반영
			v_total += v_cnt;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i <R; i++) {
			String str = sc.next();
			for(int j = 0; j <C; j++) {
				map[i][j] = str.charAt(j); 
			}
		}// 입력 끝
	
		
		for(int i = 0; i <R; i++) {
			for(int j = 0; j <C; j++) {
				if(map[i][j] != '#' && visited[i][j] == false) {
					// . 없이 v나 o만 있는 경우도 카운트하기 위해서 map[i][j] != '#' 조건 설정
					bfs(i,j);
//					System.out.println(i+","+j+"에서 양은: "+o_total);
//					System.out.println("늑대는: "+v_total);
//					System.out.println("===========================");
				}
			}
		} 
		
		System.out.print(o_total +" "+v_total);
		
	}//main
}
