package fire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	// 델타 배열 (시계 방향)
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t =0; t<T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); // 빌딩 지도의 너비
			int H = Integer.parseInt(st.nextToken()); // 빌딩 지도의 높이
			char[][] building = new char[H][W]; // 빌딩의 정보를 입력할 2차원 배열
			int startR =0; 
			int startC =0; //스타팅 포인트의 좌표
			
			// 빌딩의 정보를 입력받는다
			// 입력 받는 동시에 시작위치가 나오면 그곳을 start point로 지정한다.
			for(int r =0; r<H; r++) { 
				String line = br.readLine();
				for(int c=0; c<W; c++) {
					building[r][c] = line.charAt(c);
					if(building[r][c] == '@') {
						startR = r;
						startC = c;
					}
				}
			}
			
			// bfs 돌려!
			int escape = escapeTime(building, startR, startC);

			// 불가능이라면 impossible을, 가능이라면 시간을 출력한다.
			System.out.println( escape == -1 ? "IMPOSSIBLE" : escape );
		}
		
		br.close();
	}
	
	

	private static int escapeTime(char[][] building, int startR, int startC) {
		
		// 위의 H와 W랑 똑같음(static 처리 안하려고)
		int h = building.length;
		int w = building[0].length;
		
		Queue<int[]> sanggeun = new ArrayDeque<>(); // 상근이의 위치
		Queue<int[]> fire = new ArrayDeque<>(); // 불길
		boolean[][] visited = new boolean[h][w]; // 방문여부
		

		// ArrayDeque: 더블 엔디드 큐 구현체로, 큐와 스택의 기능을 모두 제공, FIFO와 LIFO 모두 지원
		/*
		 * addFirst(E e): 큐의 맨 앞에 요소를 추가합니다.
		 * addLast(E e): 큐의 맨 뒤에 요소를 추가합니다.
		 * removeFirst(): 큐의 맨 앞에 있는 요소를 제거하고 반환합니다.
		 * removeLast(): 큐의 맨 뒤에 있는 요소를 제거하고 반환합니다.
		 *	peekFirst(): 큐의 맨 앞에 있는 요소를 반환합니다. 요소는 제거되지 않습니다.
		 *	peekLast(): 큐의 맨 뒤에 있는 요소를 반환합니다. 요소는 제거되지 않습니다.
		 */
		
		// 빌딩을 순환하며
		for(int r=0; r< h; r++) {
			for(int c=0; c<w; c++) {
				// 빌딩에서 불을 찾았다면 fire 큐에 추가 (여러 개일 수도 있음)
				if(building[r][c]=='*') 
					fire.offer(new int[] {r,c});
				// 상근이라면, 방문처리 + 상근 큐에 추가 (r좌표, c좌표, 탈출에 걸린 시간)
				else if(building[r][c] =='@') {
					visited[r][c] = true;
					sanggeun.offer(new int[] {r, c, 0});
				}
			}
		}
		
		
		while(!sanggeun.isEmpty()) {
			
			// FIRE 
			int fireSize = fire.size();
			// 불 위치 큐에서 다 뽑아
			for(int i =0; i<fireSize; i++) {
				int[] curr = fire.poll();
				
				// 사방 탐색
				for(int d =0; d<4; d++) {
					int nr = curr[0] + dr[d];
					int nc = curr[1] + dc[d];
					
					// 빌딩 범위 내 확인
					if(nr>=0 && nr<h && nc>=0 && nc<w) {
						// .은 비어 있는 곳이므로
						if(building[nr][nc] == '.') {
							// -> 불 확산
							building[nr][nc] = '*';
							// 불 큐에 추가
							fire.offer(new int[] {nr, nc});
						}
					}
				}
			}
			
			// 상근이
			int sanggeunSize = sanggeun.size();
			// 상근 위치 다 poll
			// 좌표와 시간을 int 변수에 저장
			for(int i =0; i<sanggeunSize; i++) {
				int[] curr = sanggeun.poll();
				int r = curr[0];
				int c = curr[1];
				int time = curr[2];
				
				// 사방 탐색
				for(int d =0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위 내 있는 지 확인
					if(nr>=0 && nr<h && nc>=0 && nc<w) {
						// 방문하지 않는 곳 + 비어 있는 공간 -> 이동
						if(!visited[nr][nc] && building[nr][nc]=='.') {
							// 방문처리 + 상근 큐에 추가
							visited[nr][nc] = true;
							sanggeun.offer(new int[] {nr, nc, time+1});
						}
					}
				}
				
				// 벽에 도착했다면 시간을 1초 추가한 뒤 return 해서 답을 출력한다
				if(r==0 || r==h-1 || c==0 || c == w-1 ) 
					return time+1;
			}
		}
		
		// 탈출 실패시 -1 반환
		return -1;
	}

	
}
