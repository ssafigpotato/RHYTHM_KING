package knight;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 좌상단부터 시계방향
	static int[] dr = {-1,-2,-2,-1,1,2,2,1};
	static int[] dc = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t =0; t<T; t++) {
			int I = sc.nextInt(); // 체스판 한 변의 길이
			
			int[] start = new int[2]; // 나이트가 현재 있는 칸
			int[] end = new int[2]; // 나이트가 이동하려고 하는 칸
			
			// 각각 입력받기
			start[0] = sc.nextInt();
			start[1] = sc.nextInt();
			end[0] = sc.nextInt();
			end[1] = sc.nextInt();
			
			int ans = bfs(I,start, end);
			
			System.out.println(ans);
			
			
		}
	}

	private static int bfs(int I, int[] start, int[] end) {

		boolean[][] visited = new boolean[I][I];
		Queue<int[]> queue = new LinkedList<>();
		
		//현재 위치와 이동 횟수
		queue.add(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]] = true;
		
		
		while(!queue.isEmpty()) {
			
			// 현재를 poll 해온다
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			int move = curr[2];
			
			// end에 도달했다면 이동 횟수를 return
			if(r == end[0] && c == end[1]) {
				return move;
			}
			
			// 8가지 경우 델타 탐색
			for(int d = 0; d<8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위 확인 + 방문 여부 확인
				if(nr>=0 && nc>=0 && nr<I && nc<I && !visited[nr][nc]) {
					visited[nr][nc] = true; //방문처리
					queue.add(new int[] {nr, nc, move+1}); //이동횟수+1 해서 큐에 add
				}
			}
			
		}
		return 0;
	
	
	}

}
