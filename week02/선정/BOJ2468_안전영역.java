package week02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// location클래스 하나 만들어주기
class Location{
	int r;
	int c;
	
	Location(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
public class BOJ2468_안전영역 {
	static int N;
	static int[][]arr;
	static int safecnt; 
	static boolean[][]visited;
	static int[]dr = {-1,0,1,0}; // 상우하좌
	static int[]dc = {0,1,0,-1};
	static int cnt;
	
	// 탈출 조건 메소드
	static boolean check() { // arr모든 지역이 물에 잠겼을 때.(잠기면 0으로갱신)
		for(int i = 0 ; i <N; i++) {
			for(int j = 0; j <N; j++) {
				if(arr[i][j] !=0) { // 하나라도 0이 아니면
					return false;
				}
			}
		} 
		return true; // arr모든 지역이 0이면 물에 모두 잠겼으므로 true반환
	}
	// BFS 메소드 
	static void BFS(int r, int c) {
		// 1. 지난 미로탐색에서 했던 것 처럼 Location타입을 가지는 큐를 선언 및 구현
		Queue<Location> que = new LinkedList<Location>();
		// 2. 큐에 현재 위치를 넣고 
		que.offer(new Location(r, c));
		// 방문처리
		visited[r][c] = true;
		
		// 3. 탐색 시작
		while(!que.isEmpty()) {
			// 큐에서 하나 뽑아서 검사 시작!
			Location curr = que.poll();
			// 그 친구의 주변을 사방탐색으로 검사하기
			for(int d = 0; d <4; d++) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				
				// 3가지 살펴보기 
				// 1) 범위 내인가? (이걸 통과하면 범위내라는뜻)
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue; // 건너뛰기
	
				// 2) 막힌길인가? (잠긴 지역이면 못감)
				if(arr[nr][nc] == 0) continue;
				
				// 3) 방문했는가? (이미 방문했으면 못감)
				if(visited[nr][nc] == true) continue;
				
				// 위 조건을 무사히 통과했다면 큐에 nr, nc 넣고 방문표시
				que.offer(new Location(nr,nc));
				visited[nr][nc] = true;
				
				
			}
		}// while
	}// BFS 메소드 끝
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
//		visited = new boolean[N][N];
		for(int i = 0 ; i <N; i++) {
			for(int j = 0; j <N; j++) {
				arr[i][j] = sc.nextInt();
			}
		} ////// 0. 입력받기
		
		// 1. 본격적인 탐색 시작
		int n = 0; // 비의 양 (지역높이가 1부터 시작하니까)
		// point1. 라고 생각했는데 비가 안 내리는 경우도 생각해줘야할듯 (비의 양에 대해서는 언급 x)
		// => 지역 높이는 1부터 가능하다 했는데, 아무것도 안 잠기는 경우도 있다 했으니까 n = 0이 더욱 맞는듯.
		// 그래서 n = 0으로 해줬더니 맞았음 
		int max = Integer.MIN_VALUE;
		while(true) {
			cnt = 0; // 비의 양에 따라 cnt가 달라지므로 여기에 초기화
			if(check())break; // 모두 물에 잠겼으면 탈출하기
			visited = new boolean[N][N]; // 비의 양에 따라 잠기는 곳 다시 확인해줘야하니까 여기에 초기화
			
			// 1-1. 비 내려서 잠긴 지역 표시해주기
			for(int i = 0 ; i <N; i++) {
				for(int j = 0; j <N; j++) {
					if(arr[i][j] <=n) { // 비의양보다 높이가 작거나 같으면
						arr[i][j] = 0; // 잠김 표시(0으로 갱신)
					}
				}
			} 
			
			// 1-2. arr[i][j] != 0 인 곳들을 탐색 해주기
			for(int i = 0 ; i <N; i++) {
				for(int j = 0; j <N; j++) {
					if(arr[i][j] != 0 && visited[i][j] == false) { 
					// visited[i][j]가 방문하지 않았으면!! 이걸 안해주면 0만 아니면 다 cnt++됨
						BFS(i,j);
						cnt++;
					}
				}
			} 
			if(max<cnt) {
				max = cnt;
			}
//			for(int i = 0 ; i <N; i++) {
//				for(int j = 0; j <N; j++) {
//					System.out.print(arr[i][j]+" ");
//				}System.out.println();
//			} // 확인
//			for(int i = 0 ; i <N; i++) {
//				for(int j = 0; j <N; j++) {
//					System.out.print(visited[i][j]+" ");
//				}System.out.println();
//			} // 확인
//			System.out.println(cnt);
			
			
			// n++해서 비가 더 올 때 지역 감사
			n++;
		}// while
	
		System.out.println(max);
	
	
	
	
	}
}
