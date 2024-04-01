package baekjoon_2178_미로탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 위치 클래스 만들기
class Location {
	int r; 
	int c;
	Location(int r, int c){
		this.r = r;
		this.c = c;
	}
}
public class Main {
	static int N; // N개의 행
	static int M; // M개의 열
	static int[][] maze; // 미로
	static boolean[][]visited; // 방문 여부 체크 배열
	static int[]dr = {-1,0,1,0}; // 사방 탐색 상우하좌
	static int[]dc = {0,1,0,-1};
	
	// BFS 메소드
	static void BFS(int r, int c) {
		// Location타입의 Queue 선언(링크드리스트로 구현) 
		Queue<Location> q = new LinkedList<Location>();
		// 큐에 입력받은 위치 넣어주기 
		q.offer(new Location(r,c));
		// 해당 위치는 방문처리하고 시작
		visited[r][c] = true;
		
		// 큐가 빌 때까지 계속~~
		while(!q.isEmpty()){
			// 큐에 넣어주었던 최초의 위치 꺼내서 현재 위치에 할당
			Location curr = q.poll();
			
			// 그녀석 주위의 상하좌우를 탐색해봅시다~
			for(int d = 0; d <4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				// 1. 범위 내에 있는가?
				if(nr <0 || nr>=N || nc<0 || nc >= M ) {
					continue;  // 범위에서 벗어나면 넘어가
				}
				// 2. 막힌 길인가?
				if(maze[nr][nc] == 0) {
					continue; // 막힌 길이면 넘어가
				}
				// 3. 방문여부?
				if(visited[nr][nc]) {
					continue; // 방문했으면 넘어가
				}
				
				// 위의 조건을 무사히 통과했으면(걸리지 않았으면) 
				// -> 갈 수 있는 길이면
				// queue에 삽입해주고 방문처리
				q.offer(new Location(nr,nc));
				visited[nr][nc] = true;
				
				// 해당하는 칸에 누적해서 1더하기 
				maze[nr][nc] = maze[curr.r][curr.c]+1;
				// 도착한 칸에 그 값이 지나야하는 최소 칸 수가 나올것임.
			
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		
		for(int i = 0; i <N; i++) {
			String str = sc.next();
			for(int j = 0; j <M; j++) {
				maze[i][j] = str.charAt(j) -'0';
			}
		}
		
		// 시작 위치인 0,0넣어주고 BFS돌리기
		BFS(0,0);
		
//		확인
//		for(int i = 0; i <N; i++) {
//			for(int j = 0; j <M; j++) {
//				System.out.print(maze[i][j] +"  ");
//			}System.out.println();
//		}
		
		// (N,M)좌표의 값 출력하기
		System.out.println(maze[N-1][M-1]);
		
		
		
	}
}
